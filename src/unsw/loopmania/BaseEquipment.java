package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class BaseEquipment extends StaticEntity implements IEquipmentHandler {
    private IEquipmentHandler handler;
    private String type;

    /**
     * Constructor for BaseEquipment
     * @param x
     * @param y
     * @param handler
     * @param type
     */
    public BaseEquipment(SimpleIntegerProperty x, SimpleIntegerProperty y, IEquipmentHandler handler, String type) {
        super(x, y);
        this.handler = handler;
        this.type = type;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    @Override
    public void onEquip() {
        handler.onEquip();
    }

    @Override
    public String getType() {
        return type;
    }

    // Unequip equipment selected
    @Override
    public void onUnEquip() {
        handler.onUnEquip();
    }

    @Override
    public void onBattle(BaseEnemy enemy) {
        handler.onBattle(enemy);
    }

    @Override
    public void onDead() {
        handler.onDead();
    }

    @Override
    public void onSell() {
        handler.onSell();
    }

    @Override
    public void onStartBattle(BaseEnemy enemy) {
        handler.onStartBattle(enemy);
    }

    @Override
    public void onEndBattle(BaseEnemy enemy) {
        handler.onEndBattle(enemy);
    }
}
