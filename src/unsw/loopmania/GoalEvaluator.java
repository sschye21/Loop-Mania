package unsw.loopmania;

public class GoalEvaluator {
    private Expression exp;

    public GoalEvaluator(Expression exp) {
        this.exp = exp;
    }

    public boolean evaluate() {
        return exp.compute();
    }
}
