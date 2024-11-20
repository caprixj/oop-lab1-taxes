package model.tax;

public class Tax {
    private TaxType type;
    private double associatedIncomeSum;

    public Tax() {
    }

    public Tax(TaxType type, double associatedIncomeSum) {
        this.type = type;
        this.associatedIncomeSum = associatedIncomeSum;
    }

    public TaxType getType() {
        return type;
    }

    public void setType(TaxType type) {
        this.type = type;
    }

    public double getAssociatedIncomeSum() {
        return associatedIncomeSum;
    }

    public void setAssociatedIncomeSum(double associatedIncomeSum) {
        this.associatedIncomeSum = associatedIncomeSum;
    }

    public double getTaxationSum() {

    }
}
