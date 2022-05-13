package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class AlliedSoldier extends StaticEntity implements IAlliedSoldierHandler {
    private int HP;
    private IAlliedSoldierHandler handler;

    /**
     * Constructor for AlliedSoldier
     * @param handler
     */
    public AlliedSoldier(IAlliedSoldierHandler handler) {
        super(new SimpleIntegerProperty(-1), new SimpleIntegerProperty(-1));
        this.HP = GlobalConfig.GameStatics.AlliedSoldierInitHP;
        this.handler = handler;
    }

    public int getHP() {
        return HP;
    }

    /**
     * Attacks enemy
     */
    @Override
    public void onAttack(BaseEnemy enemy) {
        handler.onAttack(enemy);
    }

    @Override
    public String getType() {
        return GlobalConfig.GameStatics.AlliedSoldier;
    }

    /**
     * Allied Soldier Health damage taken
     * @param damage
     */
    public void onDamaged(double damage) {
        HP -= (int) damage;
        if (HP <= 0) {
            destroy();
        }
    }
}
