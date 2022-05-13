package unsw.loopmania;

public class BaseAttribute {
    private double baseValue;
    private double baseScale;

    public BaseAttribute(double baseValue, double baseScale) {
        this.baseValue = baseValue;
        this.baseScale = baseScale;
    }

    protected BaseAttribute() {
        this(0, 0);
    }

    public double getBaseValue() {
        return baseValue;
    }

    public double getBaseScale() {
        return baseScale;
    }
}
