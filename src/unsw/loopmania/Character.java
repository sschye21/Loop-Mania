package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements ICharacterHandler {
    private Attribute attackAttribute;
    private Attribute defenderAttribute;
    private BaseEquipment melee;
    private BaseEquipment armour;
    private BaseEquipment helmet;
    private BaseEquipment shield;
    private ICharacterHandler handler;
    private SimpleIntegerProperty HP;
    private SimpleIntegerProperty exp;
    private SimpleIntegerProperty gold;
    private SimpleIntegerProperty cycleNum;

    // classes
    public Character(PathPosition position) {
        super(position);
        attackAttribute = new Attribute(GlobalConfig.GameStatics.CharacterAttack);
        defenderAttribute = new Attribute(GlobalConfig.GameStatics.CharacterDefender);
        melee = null;
        armour = null;
        helmet = null;
        HP = new SimpleIntegerProperty(GlobalConfig.GameStatics.CharacterInitHP);
        this.exp = new SimpleIntegerProperty(0);
        this.gold = new SimpleIntegerProperty(0);
        this.cycleNum = new SimpleIntegerProperty(0);
    }

    public void setCycleNum(int cycleNum) {
        this.cycleNum.set(cycleNum);
    }

    public int getCycleNum() {
        return cycleNum.get();
    }

    public void setHandler(ICharacterHandler handler) {
        this.handler = handler;
    }

    // Boolean Evaluator if the Character has reached the goal to complete the game
    public boolean reachGoal() {
        GoalEvaluator goal = new GoalEvaluator(new AndExpression(new BooleanGoal(getExp() >= 100),
                new OrExpression(new BooleanGoal(getGold() >= 100), new BooleanGoal(getCycleNum() >= 50))));
        return goal.evaluate();
    }

    // Acquires Experience Points for Character
    public void acquireExp(int exp) {
        if (exp > 0) {
            this.exp.set(getExp() + exp);
        }

        if (reachGoal()) {
            destroy();
        }
    }

    // Acquires Gold for the Character
    public void acquireGold(int gold) {
        this.gold.set(getGold() + gold);
        if (reachGoal()) {
            destroy();
        }
    }

    // Acquires HP for Character
    public void acquireHP(int hp) {
        if (hp > 0) {
            this.HP.set(Math.min(GlobalConfig.GameStatics.CharacterInitHP, getHP() + hp));
        }
    }

    public int getHP() {
        return HP.get();
    }

    public SimpleIntegerProperty HPProperty() {
        return HP;
    }

    public int getExp() {
        return exp.get();
    }

    public SimpleIntegerProperty expProperty() {
        return exp;
    }

    public int getGold() {
        return gold.get();
    }

    public SimpleIntegerProperty goldProperty() {
        return gold;
    }

    // Adds Equipment for Character
    public BaseEquipment addEquipment(BaseEquipment equipment) {
        BaseEquipment oldEquipment = null;
        if (equipment == null) {
            return null;
        }
        if (GlobalConfig.MeleeEquipmentSet.contains(equipment.getType())) {
            oldEquipment = melee;
            melee = equipment;
        }
        if (GlobalConfig.ArmourEquipmentSet.contains(equipment.getType())) {
            oldEquipment = armour;
            armour = equipment;
        }
        if (GlobalConfig.HelmetEquipmentSet.contains(equipment.getType())) {
            oldEquipment = helmet;
            helmet = equipment;
        }
        if (GlobalConfig.ShieldEquipmentSet.contains(equipment.getType())) {
            oldEquipment = shield;
            shield = equipment;
        }
        if (oldEquipment != null) {
            oldEquipment.onUnEquip();
        }
        equipment.onEquip();
        return oldEquipment;
    }

    // Returns a list of equipment that is within the Characters inventory
    public List<BaseEquipment> getEquipmentList() {
        List<BaseEquipment> equipments = new ArrayList<>();
        if (melee != null) {
            equipments.add(melee);
        }
        if (armour != null) {
            equipments.add(armour);
        }
        if (shield != null) {
            equipments.add(shield);
        }
        if (helmet != null) {
            equipments.add(helmet);
        }
        return equipments;
    }

    // Damage taken by the Character
    public void onDamaged(double damage) {
        double defenderValue = defenderAttribute.calcValue();
        if (defenderValue < damage) {
            HP.set(getHP() - (int) (damage - defenderValue));
        }
        if (getHP() <= 0) {
            destroy();
        }
    }

    public Attribute getAttackAttribute() {
        return attackAttribute;
    }

    public Attribute getDefenderAttribute() {
        return defenderAttribute;
    }

    @Override
    public String getType() {
        return GlobalConfig.GameStatics.Character;
    }

    @Override
    public void onAttack(BaseEnemy enemy) {
        handler.onAttack(enemy);
    }
}
