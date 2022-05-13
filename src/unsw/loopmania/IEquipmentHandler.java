package unsw.loopmania;

public interface IEquipmentHandler {
    void onEquip();

    void onUnEquip();

    void onBattle(BaseEnemy enemy);

    void onStartBattle(BaseEnemy enemy);

    void onEndBattle(BaseEnemy enemy);

    void onDead();

    void onSell();
}
