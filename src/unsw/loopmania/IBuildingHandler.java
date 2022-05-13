package unsw.loopmania;

public interface IBuildingHandler {
    BaseEnemy onCycle(int cycleNum);

    void onThrough(Entity entity, BaseBuilding building);

    void startBattle();

    void onAttack(BaseEnemy enemy);

    void endBattle();
}
