import model.benefit.Benefit;
import model.benefit.BenefitType;
import model.income.Income;
import model.income.IncomeType;
import database.IncomeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IncomeRepository incomeRepository = new IncomeRepository();
    private static final List<Benefit> appliedBenefits = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("----- Taxes Calculator -----");
        System.out.println();

        int opt = 1;

        while (opt != 0) {
            showMenu();
            opt = scanner.nextInt();

            switch (opt) {
                case 1 -> addIncome();
                case 2 -> applyBenefit();
                case 3 -> showAllIncomes();
                case 4 -> showAllBenefits();
                case 5 -> removeAllIncomes();
                case 6 -> removeAllBenefits();
                case 7 -> sortTaxesBySum();
                case 0 -> {
                    break;
                }
                default -> System.out.println("\n\ninvalid option!");
            }
        }

        incomeRepository.close();
    }

    private static void showMenu() {
        System.out.println(
            "1 - add income\n" +
            "2 - apply benefit\n" +
            "3 - show all income records\n" +
            "4 - show all benefits\n" +
            "5 - remove all income records\n" +
            "6 - remove all benefits\n" +
            "7 - sort taxes by sum\n" +
            "0 - exit program"
        );

        System.out.print("\nenter option: ");
    }

    private static void addIncome() {
        Income income = new Income();

        System.out.println("\n--- income record creation ---");
        System.out.println("\navailable income types:\n" +
            "- MainJob\n" +
            "- SecondaryJob\n" +
            "- Royalty\n" +
            "- PropertySale\n" +
            "- Gift\n" +
            "- InternationalTransfer\n" +
            "- LotteryAward\n" +
            "- MaterialAid"
        );
        System.out.print("\nenter income type: ");
        income.setType(IncomeType.valueOf(scanner.next()));

        System.out.print("\nenter sum: ");
        income.setSum(scanner.nextDouble());

        incomeRepository.addRecord(income);
        System.out.println("\nnew income has been added: " + income);
    }

    private static void applyBenefit() {
        Benefit benefit;

        System.out.println("\n--- benefit record creation ---");
        System.out.println("\navailable benefit types:\n" +
            "- Child\n" +
            "- Veteran\n" +
            "- Disability"
        );
        System.out.print("\nenter benefit type: ");
        benefit = new Benefit(BenefitType.valueOf(scanner.next()));

        appliedBenefits.add(benefit);
        System.out.println("\nnew benefit has been added: " + benefit);
    }

    private static void showAllIncomes() {
        System.out.println(incomeRepository.getAllIncomes());
    }

    private static void showAllBenefits() {
        for (Benefit benefit : appliedBenefits) {
            System.out.println(benefit);
        }
    }

    private static void removeAllIncomes() {
        incomeRepository.removeAllIncomes();
    }

    private static void removeAllBenefits() {
        appliedBenefits.clear();
        System.out.println("\nthe benefits have been removed");
    }

    private static void sortTaxesBySum() {
        System.out.println(incomeRepository.getSortedBySum());
    }
}