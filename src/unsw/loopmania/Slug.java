package unsw.loopmania;

public class Slug implements IEnemyHandler {
    private double damageScale;

    public Slug(double damageScale) {
        this.damageScale = damageScale;
    }

    @Override
    public void onAttackCharacter() {
        double attack = GlobalConfig.EnemyStatics.get(GlobalConfig.EnemyName.Slug).getAttack();
        GlobalConfig.character.onDamaged(attack * damageScale);
    }

    @Override
    public void onAttackAlliedSoldier(AlliedSoldier soldier) {
        double attack = GlobalConfig.EnemyStatics.get(GlobalConfig.EnemyName.Slug).getAttack();
        soldier.onDamaged(attack * damageScale);
    }
}
