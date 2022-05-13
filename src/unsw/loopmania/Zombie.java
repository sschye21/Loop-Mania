package unsw.loopmania;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Zombie implements IEnemyHandler {
    private double damageScale;
    private List<AlliedSoldier> alliedSoldiers;

    public Zombie(double damageScale, List<AlliedSoldier> alliedSoldiers) {
        this.damageScale = damageScale;
        this.alliedSoldiers = alliedSoldiers;
    }

    @Override
    public void onAttackCharacter() {
        double attack = GlobalConfig.EnemyStatics.get(GlobalConfig.EnemyName.Zombie).getAttack();
        GlobalConfig.character.onDamaged(attack * damageScale);
    }

    @Override
    public void onAttackAlliedSoldier(AlliedSoldier soldier) {
        Random random = new Random();
        int r = random.nextInt(5);
        if (r == 1) {
            soldier.destroy();
            alliedSoldiers.remove(soldier);
        } else {
            double attack = GlobalConfig.EnemyStatics.get(GlobalConfig.EnemyName.Zombie).getAttack();
            soldier.onDamaged(attack * damageScale);
        }
    }
}

