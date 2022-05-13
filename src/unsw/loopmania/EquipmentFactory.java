package unsw.loopmania;

import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;

public class EquipmentFactory {

    private Map<String, IEquipmentHandler> equipmentHandlerMap;

    public EquipmentFactory(Map<String, IEquipmentHandler> equipmentHandlerMap) {
        this.equipmentHandlerMap = equipmentHandlerMap;
    }

    public BaseEquipment createEquipment(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createSword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.Sword;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createStake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.Stake;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createStaff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.Staff;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createArmour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.Armour;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createShield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.Shield;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createHelmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.Helmet;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.TheOneRing;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createDoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.DoggieCoin;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createAnduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.Anduril;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }

    public BaseEquipment createTreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String type = GlobalConfig.EquipmentName.TreeStump;
        return new BaseEquipment(x, y, equipmentHandlerMap.get(type), type);
    }
}
