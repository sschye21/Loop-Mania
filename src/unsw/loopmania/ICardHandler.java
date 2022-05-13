package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public interface ICardHandler {
    BaseBuilding onSpawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y);
    boolean isValidSpawnPosition(int buildingNodeX, int buildingNodeY);
}
