package unsw.loopmania;

public class AndExpression implements Expression {
    private Expression e1; 
    private Expression e2;

    public AndExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public boolean compute() {
        return e1.compute() && e2.compute();
    }
}
