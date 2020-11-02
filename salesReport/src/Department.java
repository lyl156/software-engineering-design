import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Department {
    private String name;
    private HashMap<Date, ArrayList<Transaction>> transactions = new HashMap<>();

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add specify transaction to the department.
     *
     * @param transaction
     *        the {@code transaction} you want to add to this department
     */
    public void addTransaction(Transaction transaction) {
        Date curDate = transaction.getDate();
        if (!transactions.containsKey(curDate)) {
            transactions.put(curDate, new ArrayList<>());
        }

        if (isBeforeToday(transaction.getDate())) {
            List curList = transactions.get(curDate);
            curList.add(transaction);
        } else {
            System.out.printf("%tF", transaction.getDate());
            System.out.println(" is after today");
        }
    }

    public HashMap<Date, ArrayList<Transaction>> getTransactions() {
        return transactions;
    }

    /**
     * Check if the date is before today.
     *
     * @param date
     *        the date of the transaction which is intent to add to this department
     *
     * @return {@code true} if {@code date} is before today
     */
    private boolean isBeforeToday(Date date) {
        Date today = new Date();
        return today.after(date);
    }
}
