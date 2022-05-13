package unsw.loopmania;

public class BooleanGoal implements Expression {
    private boolean goal;

    public BooleanGoal(boolean goal) {
        this.goal = goal;
    }

    public boolean compute() {
        return goal;
    }
}
