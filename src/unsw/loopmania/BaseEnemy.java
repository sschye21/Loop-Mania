package unsw.loopmania;

import java.util.Random;

public class BaseEnemy extends MovingEntity implements IEnemyHandler {
    public static class Statics {
        private final double attack;
        private final double defender;
        private final double fightRadius;
        private final double supportRadius;
        private final int gold;
        private final int exp;
        private final int HP;


        public Statics(double attack, double defender, double fightRadius, double supportRadius, int gold, int exp, int HP) {
            this.attack = attack;
            this.defender = defender;
            this.fightRadius = fightRadius;
            this.supportRadius = supportRadius;
            this.gold = gold;
            this.exp = exp;
            this.HP = HP;
        }

        public double getAttack() {
            return attack;
        }

        public double getFightRadius() {
            return fightRadius;
        }

        public double getSupportRadius() {
            return supportRadius;
        }

        public double getDefender() {
            return defender;
        }

        public int getGold() {
            return gold;
        }

        public int getExp() {
            return exp;
        }

        public int getHP() {
            return HP;
        }
    }

    private String type;
    private IEnemyHandler handler;
    private int HP;
    private double defender;

    /**
     * Constructor for BaseEnemy
     * @param position
     * @param type
     * @param handler
     */
    public BaseEnemy(PathPosition position, String type, IEnemyHandler handler) {
        super(position);
        this.type = type;
        this.handler = handler;
        this.HP = GlobalConfig.EnemyStatics.get(type).HP;
        this.defender = GlobalConfig.EnemyStatics.get(type).defender;
    }

    public int getGold() {
        return GlobalConfig.EnemyStatics.get(type).gold;
    }

    public int getExp() {
        return GlobalConfig.EnemyStatics.get(type).exp;
    }

    @Override
    public String getType() {
        return type;
    }
    
    // Attacks Character
    @Override
    public void onAttackCharacter() {
        handler.onAttackCharacter();
    }

    // Attack allied soldier
    @Override
    public void onAttackAlliedSoldier(AlliedSoldier soldier) {
        handler.onAttackAlliedSoldier(soldier);
    }

    // Damage taken
    public void onDamaged(double damage) {
        if (defender <= damage) {
            HP -= (damage - defender);
            if (HP <= 0) {
                destroy();
            }
        }
    }

    /**
     * move the enemy
     */
    public void move() {
        // TODO = modify this, since this implementation doesn't provide the expected enemy behaviour
        // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0) {
            moveUpPath();
        } else if (directionChoice == 1) {
            moveDownPath();
        }
    }
}
