package model.income;

public class Income {
    private IncomeType type;
    private double sum;

    public Income() { }

    public Income(IncomeType type, double sum) {
        this.type = type;
        this.sum = sum;
    }

    public IncomeType getType() {
        return type;
    }

    public void setType(IncomeType type) {
        this.type = type;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Income{" +
            "type=" + type +
            ", sum=" + sum +
            '}';
    }
}
