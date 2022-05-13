package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

public class Attribute extends BaseAttribute {
    private double base;
    private List<BaseAttribute> attributes;

    public Attribute(double base) {
        this.base = base;
        attributes = new ArrayList<>();
    }

    public void addAttribute(BaseAttribute attribute) {
        attributes.add(attribute);
    }

    public boolean removeAttribute(BaseAttribute attribute) {
        return attributes.remove(attribute);
    }

    public double calcValue() {
        double final_value = base;
        double mul = 0;
        for (BaseAttribute attribute : attributes) {
            final_value += attribute.getBaseValue();
            mul += attribute.getBaseScale();
        }
        return final_value * (1 + mul);
    }
}
