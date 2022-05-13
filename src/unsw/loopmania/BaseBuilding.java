package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class BaseBuilding extends StaticEntity implements IBuildingHandler {
    private IBuildingHandler handler;
    private String type;

    /**
     * Constructor for BaseBuilding
     * @param x
     * @param y
     * @param handler
     * @param type
     */
    public BaseBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y, IBuildingHandler handler, String type) {
        super(x, y);
        this.handler = handler;
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public BaseEnemy onCycle(int cycleNum) {
        return handler.onCycle(cycleNum);
    }

    @Override
    public void onThrough(Entity entity, BaseBuilding building) {
        handler.onThrough(entity, building);
    }

    @Override
    public void startBattle() {
        handler.startBattle();
    }

    @Override
    public void onAttack(BaseEnemy enemy) {
        handler.onAttack(enemy);
    }

    @Override
    public void endBattle() {
        handler.endBattle();
    }
}
