package test;

import javafx.beans.property.SimpleIntegerProperty;
//import org.javatuples.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unsw.loopmania.*;

import java.io.FileNotFoundException;
//import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoopManiaWorldTest {
    private LoopManiaWorld world;
    private BuildingFactory buildingFactory;
    private EnemyFactory enemyFactory;
    private EquipmentFactory equipmentFactory;
    private CardFactory cardFactory;
    private AlliedSoldierFactory alliedSoldierFactory;
    //private List<Pair<Integer, Integer>> orderedPath;
    private BaseLoopManiaWorldLoader worldLoader;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        BaseLoopManiaWorldLoader worldLoader = new BaseLoopManiaWorldLoader("world_with_twists_and_turns.json");
        world = worldLoader.load();
        buildingFactory = world.getBuildingFactory();
        enemyFactory = world.getEnemyFactory();
        alliedSoldierFactory = world.getAlliedSoldierFactory();
        equipmentFactory = world.getEquipmentFactory();
        cardFactory = world.getCardFactory();
        //orderedPath = world.getOrderedPath();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCharacter() {
        BaseEnemy vampire = enemyFactory.createVampire(world.generatePossiblePosOnPath());
        GlobalConfig.character.onAttack(vampire);
    }

    @Test
    void testWorldCreate() throws FileNotFoundException {
        worldLoader = new BaseLoopManiaWorldLoader("world_with_twists_and_turns.json");
        world = worldLoader.load();
        world.possiblySpawnEnemies();
        BaseEnemy vampire = enemyFactory.createVampire(world.generatePossiblePosOnPath());
        world.addEntity(vampire);
        world.runBattles();
        world.convertCardToBuildingByCoordinates(0, 0, 1, 1);
        world.changeEquipment(0, 0, 1, 1, LoopManiaWorld.ChangeEquipmentType.equip);
        world.changeEquipment(0, 0, 1, 1, LoopManiaWorld.ChangeEquipmentType.unEquip);
    }

    @Test
    void testAlliedSoldier() {
        AlliedSoldier soldier = alliedSoldierFactory.createAlliedSoldier();
        BaseEnemy vampire = enemyFactory.createVampire(world.generatePossiblePosOnPath());
        assertEquals(soldier.getType(), GlobalConfig.GameStatics.AlliedSoldier);
        soldier.onDamaged(1);
        soldier.onAttack(vampire);
        assertEquals(GlobalConfig.GameStatics.AlliedSoldierInitHP - 1, soldier.getHP());
    }

    @Test
    void testSlug() {
        AlliedSoldier soldier = alliedSoldierFactory.createAlliedSoldier();
        BaseEnemy slug = enemyFactory.createSlug(world.generatePossiblePosOnPath());
        slug.onDamaged(10);
        slug.onAttackAlliedSoldier(soldier);
        slug.onAttackCharacter();
    }

    @Test
    void testVampire() {
        AlliedSoldier soldier = alliedSoldierFactory.createAlliedSoldier();
        BaseEnemy vampire = enemyFactory.createVampire(world.generatePossiblePosOnPath());
        vampire.onDamaged(10);
        vampire.onAttackAlliedSoldier(soldier);
        vampire.onAttackCharacter();
    }

    @Test
    void testDoggie() {
        AlliedSoldier soldier = alliedSoldierFactory.createAlliedSoldier();
        BaseEnemy doggie = enemyFactory.createDoggie(world.generatePossiblePosOnPath());
        doggie.onDamaged(10);
        doggie.onAttackAlliedSoldier(soldier);
        doggie.onAttackCharacter();
        assertFalse(soldier.shouldExist().get());
    }

    @Test
    void testElanMuske() {
        AlliedSoldier soldier = alliedSoldierFactory.createAlliedSoldier();
        BaseEnemy muske = enemyFactory.createElanMuske(world.generatePossiblePosOnPath());
        muske.onDamaged(10);
        muske.onAttackAlliedSoldier(soldier);
        muske.onAttackCharacter();
        assertFalse(soldier.shouldExist().get());
    }

    @Test
    void testZombie() {
        AlliedSoldier soldier = alliedSoldierFactory.createAlliedSoldier();
        BaseEnemy zombie = enemyFactory.createZombie(world.generatePossiblePosOnPath());
        zombie.onDamaged(10);
        zombie.onAttackAlliedSoldier(soldier);
        zombie.onAttackCharacter();
    }

    @Test
    void testVampireCastle() {
        BaseBuilding vampireCastle = buildingFactory.createVampireCastle(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertNull(vampireCastle.onCycle(1));
        assertNotNull(vampireCastle.onCycle(5));
        assertEquals(vampireCastle.onCycle(5).getType(), GlobalConfig.EnemyName.Vampire);
        world.addEntity(vampireCastle);
    }

    @Test
    void testZombiePit() {
        BaseBuilding zombiePit = buildingFactory.createZombiePit(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertNull(zombiePit.onCycle(0));
        assertNotNull(zombiePit.onCycle(1));
        assertEquals(zombiePit.onCycle(1).getType(), GlobalConfig.EnemyName.Zombie);
    }

    @Test
    void testTower() {
        BaseBuilding tower = buildingFactory.createTower(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        BaseEnemy zombie = enemyFactory.createZombie(world.generatePossiblePosOnPath());
        tower.onAttack(zombie);
    }

    @Test
    void testTrap() {
        BaseBuilding trap = buildingFactory.createTrap(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        BaseEnemy zombie = enemyFactory.createZombie(world.generatePossiblePosOnPath());
        trap.onThrough(zombie, trap);
    }

    @Test
    void testCampfire() {
        BaseBuilding campfire = buildingFactory.createCampfire(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        campfire.startBattle();
        campfire.endBattle();
    }

    @Test
    void testHeroCastle() {
        BaseBuilding heroCastle = buildingFactory.createHeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        heroCastle.onThrough(GlobalConfig.character, heroCastle);
        assertEquals(1, world.getCycleNum());
        assertEquals(1, world.cycleNumProperty().get());
    }

    @Test
    void testGold() {
        BaseBuilding gold = buildingFactory.createGold(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        gold.onThrough(GlobalConfig.character, gold);
        assertEquals(GlobalConfig.GameStatics.GoldNum, GlobalConfig.character.getGold());
    }

    @Test
    void testHealthPotion() {
        BaseBuilding healthPotion = buildingFactory.createHealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        GlobalConfig.character.onDamaged(10);
        assertTrue(GlobalConfig.character.getHP() < GlobalConfig.GameStatics.CharacterInitHP);
        healthPotion.onThrough(GlobalConfig.character, healthPotion);
        assertEquals(GlobalConfig.GameStatics.CharacterInitHP, GlobalConfig.character.getHP());
    }

    @Test
    void testVillage() {
        BaseBuilding village = buildingFactory.createVillage(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        GlobalConfig.character.onDamaged(10);
        assertTrue(GlobalConfig.character.getHP() < GlobalConfig.GameStatics.CharacterInitHP);
        int hp = GlobalConfig.character.getHP();
        village.onThrough(GlobalConfig.character, village);
        assertEquals(hp + 1, GlobalConfig.character.getHP());
    }

    @Test
    void testBarracks() {
        BaseBuilding barracks = buildingFactory.createBarracks(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        int size = world.getAlliedSoldiers().size();
        barracks.onThrough(GlobalConfig.character, barracks);
        assertEquals(size + 1, world.getAlliedSoldiers().size());
    }

    @Test
    void testSword() {
        BaseEquipment sword = equipmentFactory.createSword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        sword.onEquip();
        sword.onUnEquip();
        world.addUnequippedEquipment(sword.getType());
    }

    @Test
    void testCheckOnThrough() {
        world.checkOnThrough();
    }

    @Test
    void testVampireCastleCard() {
        BaseCard card = cardFactory.createVampireCastleCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertNotNull(card);
        assertEquals(GlobalConfig.CardName.VampireCastle, card.getType());
        world.addCard(card.getType());
    }

    @Test
    void testZombiePitCard() {
        BaseCard card = cardFactory.createZombiePitCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertNotNull(card);
        assertEquals(GlobalConfig.CardName.ZombiePit, card.getType());
        world.addEntity(card);
    }

    @Test
    void testTowerCard() {
        BaseCard card = cardFactory.createTowerCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertNotNull(card);
        assertEquals(GlobalConfig.CardName.Tower, card.getType());
    }

    @Test
    void testVillageCard() {
        BaseCard card = cardFactory.createVillageCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertNotNull(card);
        assertEquals(GlobalConfig.CardName.Village, card.getType());
    }

    @Test
    void testBarracksCard() {
        BaseCard card = cardFactory.createBarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertNotNull(card);
        assertEquals(GlobalConfig.CardName.Barracks, card.getType());
    }

    @Test
    void testTrapCard() {
        BaseCard card = cardFactory.createTrapCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertNotNull(card);
        assertEquals(GlobalConfig.CardName.Trap, card.getType());
    }

    @Test
    void testCampfireCard() {
        BaseCard card = cardFactory.createCampfireCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertNotNull(card);
        assertEquals(GlobalConfig.CardName.Campfire, card.getType());
    }

    @Test
    void testStake() {
        BaseEnemy vampire = enemyFactory.createVampire(world.generatePossiblePosOnPath());
        BaseEquipment stake = equipmentFactory.createStake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        stake.onEquip();
        stake.onBattle(vampire);
        stake.onUnEquip();
    }

    @Test
    void testDoggieCoin(){
        int money = GlobalConfig.character.getGold();
        BaseEquipment doggieCoin = equipmentFactory.createDoggieCoin(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        doggieCoin.onSell();
        assertTrue(money< GlobalConfig.character.getGold());
    }

    @Test
    void testAnduril() {
        BaseEnemy doggie = enemyFactory.createDoggie(world.generatePossiblePosOnPath());
        BaseEquipment anduril = equipmentFactory.createAnduril(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        anduril.onEquip();
        anduril.onStartBattle(doggie);
        anduril.onBattle(doggie);
        anduril.onEndBattle(doggie);
        anduril.onUnEquip();
    }

    @Test
    void testTreeStump() {
        BaseEnemy muske = enemyFactory.createElanMuske(world.generatePossiblePosOnPath());
        BaseEquipment treeStump = equipmentFactory.createTreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        treeStump.onEquip();
        treeStump.onStartBattle(muske);
        treeStump.onBattle(muske);
        treeStump.onEndBattle(muske);
        treeStump.onUnEquip();
    }


    @Test
    void testStaff() {
        BaseEnemy vampire = enemyFactory.createVampire(world.generatePossiblePosOnPath());
        BaseEquipment staff = equipmentFactory.createStaff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        staff.onEquip();
        staff.onStartBattle(vampire);
        staff.onBattle(vampire);
        staff.onEndBattle(vampire);
        staff.onUnEquip();
    }

    @Test
    void testHelmet() {
        BaseEnemy vampire = enemyFactory.createVampire(world.generatePossiblePosOnPath());
        BaseEquipment helmet = equipmentFactory.createHelmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        helmet.onEquip();
        helmet.onBattle(vampire);
        helmet.onUnEquip();
    }

    @Test
    void testArmour() {
        enemyFactory.createVampire(world.generatePossiblePosOnPath());
        BaseEquipment armour = equipmentFactory.createArmour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        armour.onEquip();
        armour.onUnEquip();
    }

    @Test
    void testShield() {
        BaseEnemy vampire = enemyFactory.createVampire(world.generatePossiblePosOnPath());
        BaseEquipment shield = equipmentFactory.createShield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield.onEquip();
        shield.onStartBattle(vampire);
        shield.onEndBattle(vampire);
        shield.onUnEquip();
    }

    @Test
    void testGame() {
        for (int i = 0; i < 100; i++) {
            world.runTickMoves();
        }
    }

    @Test
    void testTheOneRing() {
        BaseEquipment theOneRing = equipmentFactory.createTheOneRing(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        theOneRing.onDead();
    }

    @Test
    void isOnPath() {
        assertFalse(world.isOnPath(-1, -1));
        assertTrue(world.isOnPath(7, 6));
    }

    @Test
    void isAdjacentToPath() {
        assertFalse(world.isAdjacentToPath(-1, -1));
        assertFalse(world.isAdjacentToPath(7, 6));
        assertTrue(world.isAdjacentToPath(3, 2));
    }

    @Test 
    void testShopArmour() {
        ShopArmour shop = new ShopArmour();
        assertFalse(shop.buy());
        GlobalConfig.character.acquireGold(3);
        assertTrue(shop.buy());
        shop.sell();
        assertEquals(GlobalConfig.character.getGold(), 1);
    }

    @Test 
    void testShopHelmet() {
        ShopHelmet shop = new ShopHelmet();
        assertFalse(shop.buy());
        GlobalConfig.character.acquireGold(1);
        assertTrue(shop.buy());
        shop.sell();
        assertEquals(GlobalConfig.character.getGold(), 0);
    }

    @Test 
    void testShopShield() {
        ShopShield shop = new ShopShield();
        assertFalse(shop.buy());
        GlobalConfig.character.acquireGold(3);
        assertTrue(shop.buy());
        shop.sell();
        assertEquals(GlobalConfig.character.getGold(), 1);
    }

    @Test 
    void testShopStake() {
        ShopStake shop = new ShopStake();
        assertFalse(shop.buy());
        GlobalConfig.character.acquireGold(4);
        assertTrue(shop.buy());
        shop.sell();
        assertEquals(GlobalConfig.character.getGold(), 2);
    }

    @Test 
    void testShopSword() {
        ShopSword shop = new ShopSword();
        assertFalse(shop.buy());
        GlobalConfig.character.acquireGold(5);
        assertTrue(shop.buy());
        shop.sell();
        assertEquals(GlobalConfig.character.getGold(), 3);
    }

    @Test 
    void testShopStaff() {
        ShopStaff shop = new ShopStaff();
        assertFalse(shop.buy());
        GlobalConfig.character.acquireGold(4);
        assertTrue(shop.buy());
        shop.sell();
        assertEquals(GlobalConfig.character.getGold(), 2);
    }

    @Test 
    void testShopPotion() {
        ShopPotion shop = new ShopPotion();
        assertFalse(shop.buy());
        GlobalConfig.character.acquireGold(8);
        assertTrue(shop.buy());
        shop.sell();
        assertEquals(GlobalConfig.character.getGold(), 0);
    }
}
