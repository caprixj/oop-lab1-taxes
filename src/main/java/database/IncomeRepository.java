package database;

import model.income.Income;
import model.income.IncomeType;
import model.tax.Tax;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IncomeRepository {

    private final ConnectionManager conManager;
    private static final String createTable = "CREATE TABLE IF NOT EXISTS income (id INTEGER PRIMARY KEY, incomeType TEXT, sum REAL)";

    public IncomeRepository() {
        conManager = new ConnectionManager();
    }

    public void addRecord(@NotNull Income incomeRecord) {
        String insert = "INSERT INTO income (incomeType, sum) VALUES "
            + "('" + incomeRecord.getType() + "', "
            + incomeRecord.getSum() +")";

        executeUpdate(insert);
    }

    public String getAllIncomes() {
        StringBuilder result = new StringBuilder();

        String command = "SELECT * FROM income";
        ResultSet incomeSet = executeQuery(command);

        try {
            while (true) {
                assert incomeSet != null;
                if (!incomeSet.next()) break;

                result.append(incomeSet.getInt(1))
                    .append(" ").append(incomeSet.getString(2))
                    .append(" ").append(incomeSet.getDouble(3))
                    .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public void removeAllIncomes() {
        String command = "DELETE FROM income";
        executeUpdate(command);
    }

    public void close() {
        conManager.closeConnection();
    }

    public String getSortedBySum() {
        String command = "SELECT * FROM income";
        ResultSet incomeSet = executeQuery(command);

        List<Tax> taxes = new ArrayList<Tax>();

        try {
            while (true) {
                assert incomeSet != null;
                if (!incomeSet.next()) break;

                Income income = new Income(
                    IncomeType.valueOf(incomeSet.getString(2)),
                    incomeSet.getDouble(3)
                );

                taxes.addAll(Tax.getTaxesByIncome(income));
            }

            incomeSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        taxes.sort(Comparator.comparingDouble(Tax::getTaxationSum).reversed());

        StringBuilder result = new StringBuilder();
        for (Tax tax : taxes) {
            result.append(tax.toString())
                .append("\n");
        }

        return result.toString();
    }

    private @Nullable ResultSet executeQuery(String command) {
        Connection con = conManager.getConnection();

        if (con == null) {
            System.out.println("connection failed");
            return null;
        }

        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(createTable);
            return statement.executeQuery(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void executeUpdate(String command) {
        Connection con = conManager.getConnection();

        if (con == null) {
            System.out.println("connection failed");
            return;
        }

        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(createTable);
            statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
