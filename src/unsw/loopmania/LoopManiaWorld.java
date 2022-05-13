package unsw.loopmania;

import java.util.*;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;

/**
 * A backend world.
 * <p>
 * A world can contain many entities, each occupy a square. More than one entity
 * can occupy the same square.
 */
public class LoopManiaWorld {
    // TODO = add additional backend functionality

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;
    public static final int cardRow = 13;
    public static final int maxCardNum = 8;

    public void checkOnThrough() {
        List<Pair<Integer, Integer>> buildingPosList = new ArrayList<>();
        for (BaseBuilding b : buildings) {
            buildingPosList.add(new Pair<>(b.getX(), b.getY()));
        }
        int index = buildingPosList.indexOf(new Pair<>(GlobalConfig.character.getX(), GlobalConfig.character.getY()));
        if (index != -1) {
            buildings.get(index).onThrough(GlobalConfig.character, buildings.get(index));
        }

        for (BaseEnemy e : enemies) {
            index = buildingPosList.indexOf(new Pair<>(e.getX(), e.getY()));
            if (index != -1) {
                buildings.get(index).onThrough(e, buildings.get(index));
            }
        }
    }

    public Pair<BaseCard, BaseEquipment> getEnemyRewards() {
        BaseCard card = null;
        BaseEquipment equipment = null;
        List<String> cardNames = new ArrayList<>(cardHandlerMap.keySet());
        List<String> equipmentNames = new ArrayList<>(equipmentHandlerMap.keySet());
        Random random = new Random();
        card = addCard(cardNames.get(random.nextInt(cardNames.size())));
        equipment = addUnequippedEquipment(equipmentNames.get(random.nextInt(equipmentNames.size())));
        return new Pair<>(card, equipment);
    }

    public enum ChangeEquipmentType {
        equip, unEquip
    }

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private BaseBuilding heroCastle;

    // TODO = add more lists for other entities, for equipped inventory items,
    // etc...

    private List<BaseEnemy> enemies;

    private List<BaseCard> cards;

    private List<BaseEquipment> unequippedInventoryItems;

    private List<BaseEquipment> equippedInventoryItems;

    private List<BaseBuilding> buildings;

    private List<AlliedSoldier> alliedSoldiers;

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse
     * them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    private Map<String, IBuildingHandler> buildingHandlerMap;
    private Map<String, ICardHandler> cardHandlerMap;
    private Map<String, IEquipmentHandler> equipmentHandlerMap;
    private Map<String, IEnemyHandler> enemyHandlerMap;
    private IAlliedSoldierHandler alliedSoldierHandler;
    private ICharacterHandler characterHandler;

    private EnemyFactory enemyFactory;
    private BuildingFactory buildingFactory;
    private CardFactory cardFactory;
    private EquipmentFactory equipmentFactory;
    private AlliedSoldierFactory alliedSoldierFactory;
    private CharacterFactory characterFactory;

    private double damageScale;

    private SimpleIntegerProperty cycleNum;

    public int getCycleNum() {
        return cycleNum.get();
    }

    public List<AlliedSoldier> getAlliedSoldiers() {
        return alliedSoldiers;
    }

    public AlliedSoldierFactory getAlliedSoldierFactory() {
        return alliedSoldierFactory;
    }

    public BuildingFactory getBuildingFactory() {
        return buildingFactory;
    }

    public CharacterFactory getCharacterFactory() {
        return characterFactory;
    }

    public EnemyFactory getEnemyFactory() {
        return enemyFactory;
    }

    public EquipmentFactory getEquipmentFactory() {
        return equipmentFactory;
    }

    public CardFactory getCardFactory() {
        return cardFactory;
    }

    public List<Pair<Integer, Integer>> getOrderedPath() {
        return orderedPath;
    }

    /**
     * create the world (constructor)
     *
     * @param width       width of world in number of cells
     * @param height      height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing
     *                    position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        GlobalConfig.character = null;
        enemies = new ArrayList<>();
        cards = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildings = new ArrayList<>();
        alliedSoldiers = new ArrayList<>();
        cycleNum = new SimpleIntegerProperty(0);
        damageScale = 1;

        alliedSoldiers = new ArrayList<>();
        equippedInventoryItems = new ArrayList<>();

        initBuildingHandler();
        initCardHandler();
        initEquipmentHandler();
        initEnemyHandler();
        initAlliedSoldierHandler();
        initCharacterHandler();

        buildingFactory = new BuildingFactory(buildingHandlerMap);
        enemyFactory = new EnemyFactory(enemyHandlerMap, GlobalConfig.character, enemies);
        cardFactory = new CardFactory(cardHandlerMap);
        equipmentFactory = new EquipmentFactory(equipmentHandlerMap);
        alliedSoldierFactory = new AlliedSoldierFactory(alliedSoldierHandler, alliedSoldiers);
        characterFactory = new CharacterFactory();
    }

    private void initCharacterHandler() {
        characterHandler = enemy -> {
            List<BaseEquipment> equipments = GlobalConfig.character.getEquipmentList();
            for (BaseEquipment equipment : equipments) {
                equipment.onBattle(enemy);
            }
            double attack = GlobalConfig.character.getAttackAttribute().calcValue();
            enemy.onDamaged(scaleBattleDamage(attack));
        };
    }

    private void initAlliedSoldierHandler() {
        alliedSoldierHandler = enemy -> {
            double attack = GlobalConfig.GameStatics.AlliedSoldierAttack;
            enemy.onDamaged(scaleBattleDamage(attack));
        };
    }

    private double scaleBattleDamage(double damage) {
        return damage * damageScale;
    }

    private void initEnemyHandler() {
        enemyHandlerMap = new HashMap<>();
        enemyHandlerMap.put(GlobalConfig.EnemyName.Slug, new Slug(damageScale));
        enemyHandlerMap.put(GlobalConfig.EnemyName.Zombie, new Zombie(damageScale, alliedSoldiers));
        enemyHandlerMap.put(GlobalConfig.EnemyName.Vampire, new Vampire(damageScale));
        enemyHandlerMap.put(GlobalConfig.EnemyName.Doggie, new Doggie(damageScale));
        enemyHandlerMap.put(GlobalConfig.EnemyName.ElanMuske, new ElanMuske(damageScale));
    }

    private void initEquipmentHandler() {
        equipmentHandlerMap = new HashMap<>();
        equipmentHandlerMap.put(GlobalConfig.EquipmentName.Sword, new BaseEquipmentHandler() {
            @Override
            public void onEquip() {
                GlobalConfig.character.getAttackAttribute()
                        .addAttribute(GlobalConfig.EquipmentAttribute.SwordAttribute);
            }

            @Override
            public void onUnEquip() {
                GlobalConfig.character.getAttackAttribute()
                        .removeAttribute(GlobalConfig.EquipmentAttribute.SwordAttribute);
            }
        });

        equipmentHandlerMap.put(GlobalConfig.EquipmentName.Stake, new BaseEquipmentHandler() {
            @Override
            public void onEquip() {
                GlobalConfig.character.getAttackAttribute()
                        .addAttribute(GlobalConfig.EquipmentAttribute.StakeAttribute);

            }

            @Override
            public void onUnEquip() {
                GlobalConfig.character.getAttackAttribute()
                        .removeAttribute(GlobalConfig.EquipmentAttribute.StakeAttribute);
            }

            @Override
            public void onBattle(BaseEnemy enemy) {
                if (enemy.getType().equals(GlobalConfig.EnemyName.Vampire)) {

                }
            }
        });

        equipmentHandlerMap.put(GlobalConfig.EquipmentName.Staff, new BaseEquipmentHandler() {
            @Override
            public void onEquip() {
                GlobalConfig.character.getAttackAttribute()
                        .addAttribute(GlobalConfig.EquipmentAttribute.StaffAttribute);
            }

            @Override
            public void onUnEquip() {
                GlobalConfig.character.getAttackAttribute()
                        .removeAttribute(GlobalConfig.EquipmentAttribute.StaffAttribute);
            }

            @Override
            public void onBattle(BaseEnemy enemy) {
            }
        });

        equipmentHandlerMap.put(GlobalConfig.EquipmentName.Armour, new BaseEquipmentHandler() {
            @Override
            public void onEquip() {
                GlobalConfig.character.getDefenderAttribute()
                        .addAttribute(GlobalConfig.EquipmentAttribute.ArmourAttribute);
            }

            @Override
            public void onUnEquip() {
                GlobalConfig.character.getDefenderAttribute()
                        .removeAttribute(GlobalConfig.EquipmentAttribute.ArmourAttribute);
            }
        });

        equipmentHandlerMap.put(GlobalConfig.EquipmentName.Shield, new BaseEquipmentHandler() {
            @Override
            public void onEquip() {
                GlobalConfig.character.getDefenderAttribute()
                        .addAttribute(GlobalConfig.EquipmentAttribute.ShieldAttribute);
            }

            @Override
            public void onUnEquip() {
                GlobalConfig.character.getDefenderAttribute()
                        .removeAttribute(GlobalConfig.EquipmentAttribute.ShieldAttribute);
            }

            @Override
            public void onStartBattle(BaseEnemy enemy) {
            }

            @Override
            public void onEndBattle(BaseEnemy enemy) {
            }
        });

        equipmentHandlerMap.put(GlobalConfig.EquipmentName.Helmet, new BaseEquipmentHandler() {
            @Override
            public void onEquip() {
                GlobalConfig.character.getDefenderAttribute()
                        .addAttribute(GlobalConfig.EquipmentAttribute.HelmetAttribute);

            }

            @Override
            public void onUnEquip() {
                GlobalConfig.character.getDefenderAttribute()
                        .removeAttribute(GlobalConfig.EquipmentAttribute.HelmetAttribute);
            }

            @Override
            public void onBattle(BaseEnemy enemy) {
            }
        });

        equipmentHandlerMap.put(GlobalConfig.EquipmentName.TheOneRing, new BaseEquipmentHandler() {
            @Override
            public void onDead() {
            }
        });

        equipmentHandlerMap.put(GlobalConfig.EquipmentName.DoggieCoin, new BaseEquipmentHandler() {
            @Override
            public void onSell() {
                Random random = new Random();
                GlobalConfig.character.acquireGold(random.nextInt(100000));
            }
        });

        equipmentHandlerMap.put(GlobalConfig.EquipmentName.TreeStump, new BaseEquipmentHandler() {
            @Override
            public void onEquip() {
                GlobalConfig.character.getDefenderAttribute()
                        .addAttribute(GlobalConfig.EquipmentAttribute.TreeStumpAttribute);
            }

            @Override
            public void onUnEquip() {
                GlobalConfig.character.getDefenderAttribute()
                        .removeAttribute(GlobalConfig.EquipmentAttribute.TreeStumpAttribute);
            }
        });

        equipmentHandlerMap.put(GlobalConfig.EquipmentName.Anduril, new BaseEquipmentHandler() {
            @Override
            public void onUnEquip() {
                GlobalConfig.character.getAttackAttribute()
                        .addAttribute(GlobalConfig.EquipmentAttribute.AndurilAttribute);

            }

            @Override
            public void onEquip() {
                GlobalConfig.character.getAttackAttribute()
                        .removeAttribute(GlobalConfig.EquipmentAttribute.AndurilAttribute);
            }
        });
    }

    private void initCardHandler() {
        cardHandlerMap = new HashMap<>();
        cardHandlerMap.put(GlobalConfig.CardName.Barracks, new ICardHandler() {
            @Override
            public BaseBuilding onSpawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
                return buildingFactory.createBarracks(x, y);
            }

            @Override
            public boolean isValidSpawnPosition(int buildingNodeX, int buildingNodeY) {
                return isOnPath(buildingNodeX, buildingNodeY);
            }
        });

        cardHandlerMap.put(GlobalConfig.CardName.Campfire, new ICardHandler() {
            @Override
            public BaseBuilding onSpawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
                return buildingFactory.createCampfire(x, y);
            }

            @Override
            public boolean isValidSpawnPosition(int buildingNodeX, int buildingNodeY) {
                return !isOnPath(buildingNodeX, buildingNodeY);
            }
        });

        cardHandlerMap.put(GlobalConfig.CardName.VampireCastle, new ICardHandler() {
            @Override
            public BaseBuilding onSpawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
                return buildingFactory.createVampireCastle(x, y);
            }

            @Override
            public boolean isValidSpawnPosition(int buildingNodeX, int buildingNodeY) {
                return isAdjacentToPath(buildingNodeX, buildingNodeY);
            }
        });

        cardHandlerMap.put(GlobalConfig.CardName.ZombiePit, new ICardHandler() {
            @Override
            public BaseBuilding onSpawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
                return buildingFactory.createZombiePit(x, y);
            }

            @Override
            public boolean isValidSpawnPosition(int buildingNodeX, int buildingNodeY) {
                return isAdjacentToPath(buildingNodeX, buildingNodeY);
            }
        });

        cardHandlerMap.put(GlobalConfig.CardName.Tower, new ICardHandler() {
            @Override
            public BaseBuilding onSpawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
                return buildingFactory.createTower(x, y);
            }

            @Override
            public boolean isValidSpawnPosition(int buildingNodeX, int buildingNodeY) {
                return isAdjacentToPath(buildingNodeX, buildingNodeY);
            }
        });

        cardHandlerMap.put(GlobalConfig.CardName.Village, new ICardHandler() {
            @Override
            public BaseBuilding onSpawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
                return buildingFactory.createVillage(x, y);
            }

            @Override
            public boolean isValidSpawnPosition(int buildingNodeX, int buildingNodeY) {
                return isOnPath(buildingNodeX, buildingNodeY);
            }
        });

        cardHandlerMap.put(GlobalConfig.CardName.Trap, new ICardHandler() {
            @Override
            public BaseBuilding onSpawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
                return buildingFactory.createTrap(x, y);
            }

            @Override
            public boolean isValidSpawnPosition(int buildingNodeX, int buildingNodeY) {
                return isOnPath(buildingNodeX, buildingNodeY);
            }
        });
    }

    public boolean isOnPath(int buildingNodeX, int buildingNodeY) {
        return orderedPath.contains(new Pair<>(buildingNodeX, buildingNodeY));
    }

    public boolean isAdjacentToPath(int x, int y) {
        if (isOnPath(x, y)) {
            return false;
        }
        for (Pair<Integer, Integer> pair : orderedPath) {
            if (pair.getValue0() == x && Math.abs(pair.getValue1() - y) == 1
                    || Math.abs(pair.getValue0() - x) == 1 && pair.getValue1() == y) {
                return true;
            }
        }
        return false;
    }

    private void initBuildingHandler() {
        buildingHandlerMap = new HashMap<>();
        buildingHandlerMap.put(GlobalConfig.BuildingName.Barracks, new BaseBuildingHandler() {
            @Override
            public void onThrough(Entity entity, BaseBuilding building) {
                alliedSoldiers.add(alliedSoldierFactory.createAlliedSoldier());
            }
        });

        buildingHandlerMap.put(GlobalConfig.BuildingName.Campfire, new BaseBuildingHandler() {
            @Override
            public void startBattle() {
                damageScale += 1;
            }

            @Override
            public void endBattle() {
                damageScale -= 1;
            }
        });

        buildingHandlerMap.put(GlobalConfig.BuildingName.Tower, new BaseBuildingHandler() {
            @Override
            public void onAttack(BaseEnemy enemy) {
                double attack = GlobalConfig.GameStatics.TowerDamage;
                GlobalConfig.character.onDamaged(scaleBattleDamage(attack));
            }
        });

        buildingHandlerMap.put(GlobalConfig.BuildingName.Village, new BaseBuildingHandler() {
            @Override
            public void onThrough(Entity entity, BaseBuilding building) {
                if (entity instanceof unsw.loopmania.Character) {
                    GlobalConfig.character.acquireHP(GlobalConfig.GameStatics.VillageHealth);
                }
            }
        });

        buildingHandlerMap.put(GlobalConfig.BuildingName.Trap, new BaseBuildingHandler() {
            @Override
            public void onThrough(Entity entity, BaseBuilding building) {
                if (entity instanceof BaseEnemy) {
                    BaseEnemy enemy = (BaseEnemy) entity;
                    enemy.onDamaged(GlobalConfig.GameStatics.TrapDamage);
                    building.destroy();
                    buildings.remove(building);
                }
            }
        });

        buildingHandlerMap.put(GlobalConfig.BuildingName.HeroCastle, new BaseBuildingHandler() {
            @Override
            public void onThrough(Entity entity, BaseBuilding building) {
                if (entity instanceof unsw.loopmania.Character) {
                    cycleNum.set(getCycleNum() + 1);
                    GlobalConfig.character.setCycleNum(getCycleNum());
                    entity.shopNow(true);
                }
            }

            @Override
            public BaseEnemy onCycle(int cycleNum) {
                if (cycleNum % 40 == 0) {
                    PathPosition position = generatePossiblePosOnPath();
                    return getEnemyFactory().createElanMuske(position);
                } else if (cycleNum % 20 == 0) {
                    PathPosition position = generatePossiblePosOnPath();
                    return getEnemyFactory().createDoggie(position);
                }
                return null;
            }
        });
        buildingHandlerMap.put(GlobalConfig.BuildingName.Gold, new BaseBuildingHandler() {
            @Override
            public void onThrough(Entity entity, BaseBuilding building) {
                if (entity instanceof unsw.loopmania.Character) {
                    GlobalConfig.character.acquireGold(GlobalConfig.GameStatics.GoldNum);
                    building.destroy();
                    buildings.remove(building);
                }
            }
        });
        buildingHandlerMap.put(GlobalConfig.BuildingName.HealthPotion, new BaseBuildingHandler() {
            @Override
            public void onThrough(Entity entity, BaseBuilding building) {
                if (entity instanceof unsw.loopmania.Character) {
                    GlobalConfig.character.acquireHP(GlobalConfig.GameStatics.CharacterInitHP);
                    building.destroy();
                    buildings.remove(building);
                }
            }
        });

        buildingHandlerMap.put(GlobalConfig.BuildingName.VampireCastle, new BaseBuildingHandler() {
            @Override
            public BaseEnemy onCycle(int cycleNum) {
                if (cycleNum % GlobalConfig.GameStatics.VampireCastleCycle == 0 && cycleNum > 0) {
                    PathPosition position = generatePossiblePosOnPath();
                    return getEnemyFactory().createVampire(position);
                }
                return null;
            }
        });

        buildingHandlerMap.put(GlobalConfig.BuildingName.ZombiePit, new BaseBuildingHandler() {
            @Override
            public BaseEnemy onCycle(int cycleNum) {
                if (cycleNum > 0) {
                    PathPosition position = generatePossiblePosOnPath();
                    return getEnemyFactory().createZombie(position);
                }
                return null;
            }
        });

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity
     * out of the file
     */
    public void setCharacter() {
        GlobalConfig.character.setHandler(characterHandler);
        System.out.println("Set character");
    }

    // /**
    // * add a generic entity (without it's own dedicated method for adding to the
    // world)
    // *
    // * @param entity
    // */
    // public void addEntity(Entity entity) {
    // // for adding non-specific entities (ones without another dedicated list)
    // nonSpecifiedEntities.add(entity);
    // }

    public void addEntity(BaseEnemy enemy) {
        enemies.add(enemy);
    }

    public void addEntity(BaseBuilding building) {
        buildings.add(building);
    }

    public void addEntity(BaseCard card) {
        cards.add(card);
    }

    public void addEntity(BaseEquipment equipment) {
        System.out.printf("Add %s (%d, %d)\n", equipment.getType(), equipment.getX(), equipment.getY());
        unequippedInventoryItems.add(equipment);
    }

    public List<BaseCard> getCards() {
        return cards;
    }

    public List<BaseBuilding> getBuildings() {
        return buildings;
    }

    public List<BaseEnemy> getEnemies() {
        return enemies;
    }

    public List<BaseEquipment> getUnequippedInventoryItems() {
        return unequippedInventoryItems;
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     *
     * @return list of the enemies to be displayed on screen
     */
    public List<BaseEnemy> possiblySpawnEnemies() {
        // TODO = expand this very basic version
        PathPosition pos = possiblyGetBaseEnemySpawnPosition();
        if (pos == null) {
            return null;
        }
        List<BaseEnemy> spawningEnemies = new ArrayList<>();
        BaseEnemy enemy = enemyFactory.createSlug(pos);
        enemies.add(enemy);
        spawningEnemies.add(enemy);
        return spawningEnemies;
    }

    public SimpleIntegerProperty cycleNumProperty() {
        return cycleNum;
    }

    /**
     * run the expected battles in the world, based on current world state
     *
     * @return list of enemies which have been killed
     */
    public void runBattles() {

        List<BaseEnemy> activeEnemies = new ArrayList<>();
        Random rand = new Random();

        boolean fightStart = false;
        for (BaseEnemy e : enemies) {
            if (isInFightRadius(e)) {
                fightStart = true;
                break;
            }
        }
        if (!fightStart) {
            return;
        }

        for (BaseEnemy e : enemies) {
            if (isInSupportRadius(e)) {
                activeEnemies.add(e);
            }
        }

        for (BaseBuilding b : buildings) {
            b.startBattle();
        }

        while (!activeEnemies.isEmpty() && GlobalConfig.character.isExist()) {
            // System.out.println("Fight start");
            for (BaseEnemy e : activeEnemies) {
                if (!alliedSoldiers.isEmpty()) {
                    // enemy attack random allied solders
                    e.onAttackAlliedSoldier(alliedSoldiers.get(rand.nextInt(alliedSoldiers.size())));
                    // System.out.printf("%s attack allied soldier\n", e.getType());
                } else {
                    // Then attack character
                    e.onAttackCharacter();
                    // System.out.printf("%s attack character\n", e.getType());
                }
            }
            for (BaseBuilding b : buildings) {
                BaseEnemy e = activeEnemies.get(rand.nextInt(activeEnemies.size()));
                if (isInBuildingRadius(b, e)) {
                    b.onAttack(e);
                    // System.out.printf("%s attack %s", b.getType(), e.getType());
                }
            }
            for (AlliedSoldier s : alliedSoldiers) {
                BaseEnemy e = activeEnemies.get(rand.nextInt(activeEnemies.size()));
                s.onAttack(e);
                // System.out.printf("Allied solder attack %s\n", e.getType());
            }
            BaseEnemy e = activeEnemies.get(rand.nextInt(activeEnemies.size()));
            // System.out.printf("Character attack %s\n", e.getType());
            GlobalConfig.character.onAttack(e);
            if (!e.isExist()) {
                activeEnemies.remove(e);
            }
        }
        for (BaseBuilding b : buildings) {
            b.endBattle();
        }
        // TODO kill enemy by listener
        // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from
        // the enemies list
        // if we killEnemy in prior loop, we get
        // java.util.ConcurrentModificationException
        // due to mutating list we're iterating over
        // killEnemy(e);
    }

    private boolean isInFightRadius(BaseEnemy enemy) {
        double fightRadius = GlobalConfig.EnemyStatics.get(enemy.getType()).getFightRadius();
        return Math.pow(GlobalConfig.character.getX() - enemy.getX(), 2)
                + Math.pow(GlobalConfig.character.getY() - enemy.getY(), 2) <= Math.pow(fightRadius, 2);
    }

    private boolean isInSupportRadius(BaseEnemy enemy) {
        double supportRadius = GlobalConfig.EnemyStatics.get(enemy.getType()).getSupportRadius();
        return Math.pow(GlobalConfig.character.getX() - enemy.getX(), 2)
                + Math.pow(GlobalConfig.character.getY() - enemy.getY(), 2) <= Math.pow(supportRadius, 2);
    }

    private boolean isInBuildingRadius(BaseBuilding building, BaseEnemy enemy) {
        double buildingRadius = GlobalConfig.BuildingRadius.get(building.getType());
        return Math.pow(enemy.getX() - building.getX(), 2) + Math.pow(enemy.getY() - building.getY(), 2) <= Math
                .pow(buildingRadius, 2);
    }

    // /**
    // * spawn a card in the world and return the card entity
    // *
    // * @return a card to be spawned in the controller as a JavaFX node
    // */
    // public BaseCard loadCard(String type) {
    // // if adding more cards than have, remove the first card...
    // if (cards.size() >= getWidth()) {
    // // TODO = give some cash/experience/item rewards for the discarding of the
    // oldest card
    // getCardRewards();
    // removeCard(0);
    // }
    // BaseCard card = cardFactory.createCard(new
    // SimpleIntegerProperty(cards.size()), new SimpleIntegerProperty(0), type);
    // cards.add(card);
    // return card;
    // }

    // private void getCardRewards() {
    //
    // }

    // /**
    // * remove card at a particular index of cards (position in gridpane of
    // unplayed cards)
    // *
    // * @param index the index of the card, from 0 to length-1
    // */
    // private void removeCard(int index) {
    // BaseCard c = this.cards.get(index);
    // int x = c.getX();
    // c.destroy();
    // this.cards.remove(index);
    // shiftCardsDownFromXCoordinate(x);
    // }

    /**
     * spawn a equipment in the world and return the sword entity
     *
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public BaseEquipment addUnequippedEquipment(String type) {
        // TODO = expand this - we would like to be able to add multiple types of items,
        // apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        BaseEquipment equipment = equipmentFactory.createEquipment(
                new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()), type);
        unequippedInventoryItems.add(equipment);
        return equipment;
    }

    public BaseCard addCard(String type) {
        // TODO = expand this - we would like to be able to add multiple types of items,
        // apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForCard();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForCard();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        BaseCard card = cardFactory.createCard(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()), type);
        cards.add(card);
        return card;
    }

    /**
     * remove an item by x,y coordinates
     *
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y) {
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        assert item != null;
        removeUnequippedInventoryItem(item);
    }

    /**
     * run moves which occur with every tick without needing to spawn anything
     * immediately
     */
    public void runTickMoves() {
        GlobalConfig.character.moveDownPath();
        moveBasicEnemies();
    }

    /**
     * remove an item from the unequipped inventory
     *
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item) {
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates assumes that no 2
     * unequipped inventory items share x and y coordinates
     *
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y) {
        for (Entity e : unequippedInventoryItems) {
            if ((e.getX() == x) && (e.getY() == y)) {
                // System.out.printf("Find %s\n", e.getType());
                return e;
            }
        }
        return null;
    }

    private BaseCard getCardByCoordinates(int x, int y) {
        for (BaseCard c : cards) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list
     * (this is ordered based on age in the starter code)
     *
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index) {
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    private void removeItemByPositionInCard(int index) {
        Entity item = cards.get(index);
        item.destroy();
        cards.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the
     * unequipped inventory
     *
     * @return x, y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem() {
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available
        // slot defined by looking row by row
        for (int y = 0; y < unequippedInventoryHeight; y++) {
            for (int x = 0; x < unequippedInventoryWidth; x++) {
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null) {
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    private Pair<Integer, Integer> getFirstAvailableSlotForCard() {
        int y = cardRow;
        for (int x = 0; x < maxCardNum; x++) {
            if (getCardByCoordinates(x, y) == null) {
                return new Pair<>(x, y);
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     *
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x) {
        for (BaseCard c : cards) {
            if (c.getX() >= x) {
                c.x().set(c.getX() - 1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveBasicEnemies() {
        for (BaseEnemy e : enemies) {
            e.move();
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     *
     * @return null if random choice is that wont be spawning an enemy or it isn't
     *         possible, or random coordinate pair if should go ahead
     */
    private PathPosition possiblyGetBaseEnemySpawnPosition() {
        // TODO = modify this

        // has a chance spawning a basic enemy on a tile the character isn't on or
        // immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        if ((choice == 0) && (enemies.size() < 2)) {
            return generatePossiblePosOnPath();
        }
        return null;
    }

    public PathPosition generatePossiblePosOnPath() {
        Random rand = new Random();
        List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
        int indexPosition = orderedPath
                .indexOf(new Pair<Integer, Integer>(GlobalConfig.character.getX(), GlobalConfig.character.getY()));
        // inclusive start and exclusive end of range of positions not allowed
        int startNotAllowed = (indexPosition - 2 + orderedPath.size()) % orderedPath.size();
        int endNotAllowed = (indexPosition + 3) % orderedPath.size();
        // note terminating condition has to be != rather than < since wrap around...
        for (int i = endNotAllowed; i != startNotAllowed; i = (i + 1) % orderedPath.size()) {
            orderedPathSpawnCandidates.add(orderedPath.get(i));
        }

        // choose random choice
        Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates
                .get(rand.nextInt(orderedPathSpawnCandidates.size()));
        int indexInPath = orderedPath.indexOf(spawnPosition);
        return new PathPosition(indexInPath, orderedPath);
    }

    // /**
    // * remove a card by its x, y coordinates
    // *
    // * @param cardNodeX x index from 0 to width-1 of card to be removed
    // * @param cardNodeY y index from 0 to height-1 of card to be removed
    // * @param buildingNodeX x index from 0 to width-1 of building to be added
    // * @param buildingNodeY y index from 0 to height-1 of building to be added
    // */
    // public VampireCastle convertCardToBuildingByCoordinates(int cardNodeX, int
    // cardNodeY, int buildingNodeX, int buildingNodeY) {
    // // start by getting card
    // BaseCard baseCard = null;
    // for (BaseCard c : baseCardEntities) {
    // if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) {
    // baseCard = c;
    // break;
    // }
    // }
    //
    // //TODO now spawn building
    //// VampireCastle newBuilding = new VampireCastle(new
    // SimpleIntegerProperty(buildingNodeX), new
    // SimpleIntegerProperty(buildingNodeY));
    //// buildingEntities.add(newBuilding);
    //
    // // destroy the card
    // baseCard.destroy();
    // baseCardEntities.remove(baseCard);
    // shiftCardsDownFromXCoordinate(cardNodeX);
    //
    // return null;
    // }

    /**
     * remove a card by its x, y coordinates
     *
     * @param cardNodeX     x index from 0 to width-1 of card to be removed
     * @param cardNodeY     y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public BaseBuilding convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX,
            int buildingNodeY) {
        // start by getting card
        BaseCard baseCard = null;
        for (BaseCard c : cards) {
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) {
                baseCard = c;
                break;
            }
        }

        if (baseCard == null) {
            return null;
        }
        if (!baseCard.isValidSpawnPosition(buildingNodeX, buildingNodeY)) {
            return null;
        }
        BaseBuilding newBuilding = baseCard.onSpawnBuilding(new SimpleIntegerProperty(buildingNodeX),
                new SimpleIntegerProperty(buildingNodeY));
        buildings.add(newBuilding);

        // destroy the card
        baseCard.destroy();
        cards.remove(baseCard);
        shiftCardsDownFromXCoordinate(cardNodeX);

        return newBuilding;
    }

    private boolean isValidEquipPos(String type, int x, int y) {
        return new Pair<Integer, Integer>(x, y).equals(GlobalConfig.EquipmentPosMap.get(type));
    }

    public BaseEquipment changeEquipment(int fromX, int fromY, int toX, int toY, ChangeEquipmentType type) {
        BaseEquipment equipment = null;
        BaseEquipment newEquipment = null;
        if (type == ChangeEquipmentType.equip) {
            for (BaseEquipment e : unequippedInventoryItems) {
                if (e.getX() == fromX && e.getY() == fromY) {
                    equipment = e;
                    break;
                }
            }
            if (equipment == null) {
                return null;
            }
            if (!isValidEquipPos(equipment.getType(), toX, toY)) {
                System.out.println("Invalid position of equipment");
                return null;
            }
            unequippedInventoryItems.remove(equipment);
        } else {
            for (BaseEquipment e : equippedInventoryItems) {
                if (e.getX() == fromX && e.getY() == fromY) {
                    equipment = e;
                    break;
                }
            }
            if (equipment == null) {
                return null;
            }
            equippedInventoryItems.remove(equipment);
        }
        newEquipment = equipmentFactory.createEquipment(new SimpleIntegerProperty(toX), new SimpleIntegerProperty(toY),
                equipment.getType());
        if (type == ChangeEquipmentType.equip) {
            equippedInventoryItems.add(newEquipment);
        } else {
            unequippedInventoryItems.add(newEquipment);
        }
        return newEquipment;
    }
}
