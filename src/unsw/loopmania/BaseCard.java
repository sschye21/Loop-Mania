package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Card in the world
 * which doesn't move
 */
public class BaseCard extends StaticEntity implements ICardHandler {
    private ICardHandler handler;
    private String type;

    /**
     * Constructor for BaseCard
     * @param x
     * @param y
     * @param handler
     * @param type
     */
    public BaseCard(SimpleIntegerProperty x, SimpleIntegerProperty y, ICardHandler handler, String type) {
        super(x, y);
        this.handler = handler;
        this.type = type;
    }

    // Spawn Building
    @Override
    public BaseBuilding onSpawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return handler.onSpawnBuilding(x, y);
    }

    // Checks if spawn position is valid
    @Override
    public boolean isValidSpawnPosition(int buildingNodeX, int buildingNodeY) {
        return handler.isValidSpawnPosition(buildingNodeX, buildingNodeY);
    }

    @Override
    public String getType() {
        return type;
    }
}
