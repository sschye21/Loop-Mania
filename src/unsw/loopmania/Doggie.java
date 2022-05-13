package unsw.loopmania;

public class Doggie implements IEnemyHandler {
    private double damageScale;

    public Doggie(double damageScale) {
        this.damageScale = damageScale;
    }

    @Override
    public void onAttackCharacter() {
        double attack = GlobalConfig.EnemyStatics.get(GlobalConfig.EnemyName.Doggie).getAttack();
        GlobalConfig.character.onDamaged(attack * damageScale);
    }

    @Override
    public void onAttackAlliedSoldier(AlliedSoldier soldier) {
        soldier.destroy();
    }
}

