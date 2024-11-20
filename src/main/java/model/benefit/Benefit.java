package model.benefit;

public class Benefit {
    BenefitType type;

    public Benefit() {
    }

    public Benefit(BenefitType type) {
        this.type = type;
    }

    public BenefitType getType() {
        return type;
    }

    public void setType(BenefitType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Benefit{" +
            "type=" + type +
            '}';
    }
}
