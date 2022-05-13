package unsw.loopmania;

public class ElanMuske implements IEnemyHandler {
    private double damageScale;

    public ElanMuske(double damageScale) {
        this.damageScale = damageScale;
    }

    @Override
    public void onAttackCharacter() {
        double attack = GlobalConfig.EnemyStatics.get(GlobalConfig.EnemyName.ElanMuske).getAttack();
        GlobalConfig.character.onDamaged(attack * damageScale);
    }

    @Override
    public void onAttackAlliedSoldier(AlliedSoldier soldier) {
        soldier.destroy();
    }
}


