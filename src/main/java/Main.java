import model.exemption.Exemption;
import model.income.Income;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Taxes Calculator ---");
        System.out.println();

        Income tempIncome = new Income();
        Exemption tempExemption = new Exemption();

        int opt = 1;
        Scanner scanner = new Scanner(System.in);

        while (opt != 0) {
            System.out.println(
                "1 - create income\n" +
                "2 - create exemption\n" +
                "3 - show all income records\n" +
                "4 - show all exemptions\n" +
                "5 - delete all income records\n" +
                "6 - delete all exemptions\n" +
                "7 - sort taxes by sum\n" +
                "0 - exit program"
            );

            System.out.print("\nenter option: ");
            opt = scanner.nextInt();

            switch (opt) {
                case 1 -> {
                    //
                }
                case 2 -> {
                    //
                }
                case 3 -> {
                    //
                }
                case 4 -> {
                    //
                }
                case 5 -> {
                    //
                }
                case 6 -> {
                    //
                }
                case 7 -> {
                    //
                }
                case 0 -> {
                    break;
                }
                default -> System.out.println("\n\ninvalid option!");
            }
        }
    }
}