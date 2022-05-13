package unsw.loopmania;

import java.util.Random;

public class Vampire implements IEnemyHandler {
    private double damageScale;

    public Vampire(double damageScale) {
        this.damageScale = damageScale;
    }

    @Override
    public void onAttackCharacter() {
        Random random = new Random();
        double attack = GlobalConfig.EnemyStatics.get(GlobalConfig.EnemyName.Vampire).getAttack();
        int r = random.nextInt(5);
        if (r == 1) {
            attack *= 2;
        }
        GlobalConfig.character.onDamaged(attack * damageScale);
    }

    @Override
    public void onAttackAlliedSoldier(AlliedSoldier soldier) {
        Random random = new Random();
        double attack = GlobalConfig.EnemyStatics.get(GlobalConfig.EnemyName.Vampire).getAttack();
        int r = random.nextInt(5);
        if (r == 1) {
            attack *= 2;
        }
        soldier.onDamaged(attack * damageScale);
    }
}
