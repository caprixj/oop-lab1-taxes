package model.tax;

import model.income.Income;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tax {
    private TaxType type;
    private double associatedIncomeSum;

    public Tax() {
    }

    public Tax(TaxType type, double associatedIncomeSum) {
        this.type = type;
        this.associatedIncomeSum = associatedIncomeSum;
    }

    public static List<Tax> getTaxesByIncome(@NotNull Income income) {
        List<TaxType> taxTypes = new ArrayList<>();
        List<Tax> taxes = new ArrayList<>();

        switch (income.getType()) {
            case MainJob, SecondaryJob -> taxTypes.addAll(List.of(TaxType.PDFO, TaxType.Military));
            case Royalty               -> taxTypes.add(TaxType.Royalty);
            case PropertySale          -> taxTypes.addAll(List.of(TaxType.PDFO, TaxType.PropertySale));
            case Gift                  -> taxTypes.addAll(List.of(TaxType.PDFO, TaxType.Gift));
            case InternationalTransfer -> taxTypes.add(TaxType.InternationalTransfer);
            case LotteryAward          -> taxTypes.add(TaxType.PDFO);
            case MaterialAid           -> { /* no taxes */ }
        }

        for (TaxType tt : taxTypes) {
            taxes.add(new Tax(tt, income.getSum()));
        }

        return taxes;
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
        double rate = switch (this.type) {
            case PDFO                  -> 0.18;
            case Military              -> 0.05;
            case Royalty               -> 0.2;
            case PropertySale          -> 0.1;
            case Gift                  -> 0.11;
            case InternationalTransfer -> 0.03;
        };

        return rate * this.associatedIncomeSum;
    }

    @Override
    public String toString() {
        return "Tax{" +
            "type=" + type +
            ", sum=" + getTaxationSum() +
            '}';
    }
}
