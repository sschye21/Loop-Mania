package unsw.loopmania;


import org.javatuples.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GlobalConfig {
    // Building Names
    public interface BuildingName {
        String VampireCastle = "Vampire castle";
        String ZombiePit = "Zombie pit";
        String Tower = "Tower";
        String Village = "Village";
        String Barracks = "Barracks";
        String Trap = "Trap";
        String Campfire = "Campfire";
        String HeroCastle = "Hero's Castle";
        String HealthPotion = "Health potion";
        String Gold = "Gold";
    }

    public interface CardName {
        String VampireCastle = "Vampire castle card";
        String ZombiePit = "Zombie pit card";
        String Tower = "Tower card";
        String Village = "Village card";
        String Barracks = "Barracks card";
        String Trap = "Trap card";
        String Campfire = "Campfire card";
    }

    public interface EnemyName {
        String Zombie = "Zombie";
        String Slug = "Slug";
        String Vampire = "Vampire";
        String Doggie = "Doggie";
        String ElanMuske = "Elan Muske";
    }

    public interface EquipmentName {
        String Sword = "Sword";
        String Stake = "Stake";
        String Staff = "Staff";
        String Armour = "Armour";
        String Shield = "Shield";
        String Helmet = "Helmet";
        String TheOneRing = "The One Ring";
        String SwordSlot = "Sword slot";
        String HealthPotion = "Health Potion";
        String Heart = "Heart";
        String EmptySlot = "Empty slot";
        String HelmetSlot = "Helmet slot";
        String ShieldSlot = "Shield Slot";
        String DoggieCoin = "DoggieCoin";
        String Anduril ="Anduril";
        String TreeStump ="Tree Stump";
    }


    // Building Radius
    public static Map<String, Double> BuildingRadius;

    static {
        BuildingRadius = new HashMap<>();
        BuildingRadius.put(BuildingName.VampireCastle, (double) 0);
        BuildingRadius.put(BuildingName.ZombiePit, (double) 0);
        BuildingRadius.put(BuildingName.Tower, (double) 3);
        BuildingRadius.put(BuildingName.Village, (double) 0);
        BuildingRadius.put(BuildingName.Barracks, (double) 0);
        BuildingRadius.put(BuildingName.Trap, (double) 0);
        BuildingRadius.put(BuildingName.Campfire, (double) 5);
        BuildingRadius.put(BuildingName.HeroCastle, (double) 0);
        BuildingRadius.put(BuildingName.Gold, (double) 0);
        BuildingRadius.put(BuildingName.HealthPotion, (double) 0);
    }

    public static Map<String, BaseEnemy.Statics> EnemyStatics;

    static {
        EnemyStatics = new HashMap<>();
        EnemyStatics.put(EnemyName.Slug, new BaseEnemy.Statics(2, 0, 1, 1, 1, 1, 10));
        EnemyStatics.put(EnemyName.Zombie, new BaseEnemy.Statics(5, 2, 2, 2, 10, 10, 20));
        EnemyStatics.put(EnemyName.Vampire, new BaseEnemy.Statics(10, 5, 2, 3, 2, 3, 100));
        EnemyStatics.put(EnemyName.Doggie, new BaseEnemy.Statics(50, 20, 1, 1, 50, 20, 400));
        EnemyStatics.put(EnemyName.ElanMuske, new BaseEnemy.Statics(100, 30, 1, 1, 100, 40, 700));
    }

    public static Map<String, String> EntityImagePath;

    static {
        EntityImagePath = new HashMap<>();
        EntityImagePath.put(EquipmentName.Armour, "src/images/armour.png");
        EntityImagePath.put(GameStatics.Character, "src/images/human_new.png");
        EntityImagePath.put(BuildingName.Barracks, "src/images/barracks.png");
        EntityImagePath.put(CardName.Barracks, "src/images/barracks_card.png");
        EntityImagePath.put(EquipmentName.Sword, "src/images/basic_sword.png");
        EntityImagePath.put(BuildingName.HealthPotion, "src/images/brilliant_blue_new.png");
        EntityImagePath.put(BuildingName.Campfire, "src/images/campfire.png");
        EntityImagePath.put(CardName.Campfire, "src/images/campfire_card.png");
        EntityImagePath.put(EquipmentName.EmptySlot, "src/images/empty_slot.png");
        EntityImagePath.put(BuildingName.Gold, "src/images/gold_pile.png");
        EntityImagePath.put(EquipmentName.Helmet, "src/images/helmet.png");
        EntityImagePath.put(EquipmentName.HelmetSlot, "src/images/helmet_slot.png");
        EntityImagePath.put(BuildingName.HeroCastle, "src/images/heros_castle.png");
        EntityImagePath.put(EquipmentName.Shield, "src/images/shield.png");
        EntityImagePath.put(EquipmentName.ShieldSlot, "src/images/shield_unequipped.png");
        EntityImagePath.put(EnemyName.Slug, "src/images/slug.png");
        EntityImagePath.put(EquipmentName.Staff, "src/images/staff.png");
        EntityImagePath.put(EquipmentName.Stake, "src/images/stake.png");
        EntityImagePath.put(EquipmentName.Heart, "src/images/heart.png");
        EntityImagePath.put(EquipmentName.SwordSlot, "src/images/sword_unequipped.png");
        EntityImagePath.put(EquipmentName.TheOneRing, "src/images/the_one_ring.png");
        EntityImagePath.put(BuildingName.Tower, "src/images/tower.png");
        EntityImagePath.put(CardName.Tower, "src/images/tower_card.png");
        EntityImagePath.put(BuildingName.Trap, "src/images/trap.png");
        EntityImagePath.put(CardName.Trap, "src/images/trap_card.png");
        EntityImagePath.put(EnemyName.Vampire, "src/images/vampire.png");
        EntityImagePath.put(BuildingName.VampireCastle, "src/images/vampire_castle_building_purple_background.png");
        EntityImagePath.put(CardName.VampireCastle, "src/images/vampire_castle_card.png");
        EntityImagePath.put(BuildingName.Village, "src/images/village.png");
        EntityImagePath.put(CardName.Village, "src/images/village_card.png");
        EntityImagePath.put(EnemyName.Zombie, "src/images/zombie.png");
        EntityImagePath.put(BuildingName.ZombiePit, "src/images/zombie_pit.png");
        EntityImagePath.put(CardName.ZombiePit, "src/images/zombie_pit_card.png");
        EntityImagePath.put(EnemyName.Doggie, "src/images/doggie.png");
        EntityImagePath.put(EnemyName.ElanMuske, "src/images/ElanMuske.png");
        EntityImagePath.put(EquipmentName.DoggieCoin, "src/images/doggiecoin.png");
        EntityImagePath.put(EquipmentName.Anduril, "src/images/anduril_flame_of_the_west.png");
        EntityImagePath.put(EquipmentName.TreeStump, "src/images/tree_stump.png");
    }

    public static Set<String> MeleeEquipmentSet;
    public static Set<String> ArmourEquipmentSet;
    public static Set<String> ShieldEquipmentSet;
    public static Set<String> HelmetEquipmentSet;

    static {
        MeleeEquipmentSet = new HashSet<>();
        ArmourEquipmentSet = new HashSet<>();
        ShieldEquipmentSet = new HashSet<>();
        HelmetEquipmentSet = new HashSet<>();

        MeleeEquipmentSet.add(EquipmentName.Stake);
        MeleeEquipmentSet.add(EquipmentName.Staff);
        MeleeEquipmentSet.add(EquipmentName.Sword);

        ArmourEquipmentSet.add(EquipmentName.Armour);
        ShieldEquipmentSet.add(EquipmentName.Shield);
        HelmetEquipmentSet.add(EquipmentName.Helmet);
    }

    public static Map<String, Pair<Integer, Integer>> EquipmentPosMap;

    static {
        EquipmentPosMap = new HashMap<>();
        EquipmentPosMap.put(EquipmentName.Sword, new Pair<>(0, 1));
        EquipmentPosMap.put(EquipmentName.Stake, new Pair<>(0, 1));
        EquipmentPosMap.put(EquipmentName.Staff, new Pair<>(0, 1));
        EquipmentPosMap.put(EquipmentName.Anduril, new Pair<>(0, 1));
        EquipmentPosMap.put(EquipmentName.HealthPotion, new Pair<>(3, 0));
        EquipmentPosMap.put(EquipmentName.TheOneRing, new Pair<>(3, 1));
        EquipmentPosMap.put(EquipmentName.HelmetSlot, new Pair<>(1, 0));
        EquipmentPosMap.put(EquipmentName.Armour, new Pair<>(1, 1));
        EquipmentPosMap.put(EquipmentName.Shield, new Pair<>(2, 1));
        EquipmentPosMap.put(EquipmentName.TreeStump, new Pair<>(2, 1));

    }

    public interface GameStatics {
        String AlliedSoldier = "Allied soldier";
        String PathTile = "Path tile";
        String Character = "Character";
        double TowerDamage = 2;
        // Village's regaining health point
        int VillageHealth = 1;
        // Trap's damage point to enemy
        int TrapDamage = 5;
        // AlliedSoldier's initialize HP
        int AlliedSoldierInitHP = 5;
        // AlliedSoldier's defender, when a AlliedSoldier is attacked, the damage will be reduced by 1
        int AlliedSoldierAttack = 2;
        // Campire's damage scale number when a battle is occurring
        int CampireDamageScale = 2;

        int GoldNum = 10;
        int VampireCastleCycle = 5;
        int ZombiePitCycle = 1;

        double CharacterAttack = 15;
        double CharacterDefender = 1;

        double SwordAttack = 10;
        double StakeAttack = 5;
        double StaffAttack = 3;
        double ArmourScale = 2;
        double ShieldValue = 2;
        double HelmetScale = 1.3;
        int CharacterInitHP = 100;
    }

    public static Character character;

    public interface EquipmentAttribute {
        BaseAttribute SwordAttribute = new BaseAttribute(GlobalConfig.GameStatics.SwordAttack, 0);
        BaseAttribute StakeAttribute = new BaseAttribute(GameStatics.StakeAttack, 0);
        BaseAttribute StaffAttribute = new BaseAttribute(GameStatics.StaffAttack, 0);
        BaseAttribute ArmourAttribute = new BaseAttribute(0, GameStatics.ArmourScale);
        BaseAttribute ShieldAttribute = new BaseAttribute(GameStatics.ShieldValue, 0);
        BaseAttribute HelmetAttribute = new BaseAttribute(0, GameStatics.HelmetScale);
        BaseAttribute AndurilAttribute = new BaseAttribute(1000, 0);
        BaseAttribute TreeStumpAttribute = new BaseAttribute(1000, 0);
    }
}
