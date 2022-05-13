package unsw.loopmania;

import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;

public class BuildingFactory {

    private Map<String, IBuildingHandler> buildingHandlerMap;

    /**
     * Constructor for BuildingFactory
     * @param buildingHandlerMap
     */
    public BuildingFactory(Map<String, IBuildingHandler> buildingHandlerMap) {
        this.buildingHandlerMap = buildingHandlerMap;
    }

    // Creates new Building
    public BaseBuilding createBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Creates new Vampire Castle
    public BaseBuilding createVampireCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.VampireCastle;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Creates Zombie Pit Building
    public BaseBuilding createZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.ZombiePit;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Create Village
    public BaseBuilding createVillage(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.Village;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Creates Barracks
    public BaseBuilding createBarracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.Barracks;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Creates Trap
    public BaseBuilding createTrap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.Trap;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Creates Tower
    public BaseBuilding createTower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.Tower;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Creates Campfire
    public BaseBuilding createCampfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.Campfire;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Creates Hero Castle
    public BaseBuilding createHeroCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.HeroCastle;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Creates Gold
    public BaseBuilding createGold(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.Gold;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }

    // Creates Health Potion
    public BaseBuilding createHealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.BuildingName.HealthPotion;
        return new BaseBuilding(x, y, buildingHandlerMap.get(type), type);
    }
}