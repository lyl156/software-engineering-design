import java.util.*;

public class MonthlyReport extends Report {
    public MonthlyReport(String departmentName, Store store) {
        super(departmentName, store);
    }

    /**
     *  Print every transaction in the same month as now.
     *
     * @param store
     *        the store which use the app to get this report
     * @param departmentName
     *        the department of the store which the user want to get the report from
     */
    @Override
    void getData(Store store, String departmentName) {
        Department department = store.getDepartment(departmentName);
        if (department == null) return;

        Date date2 = new Date(System.currentTimeMillis());
        HashMap<Date, ArrayList<Transaction>> transactions = department.getTransactions();
        System.out.println(departmentName +" department in " + store.getName() + " store:" + " MonthlyReport");
        for (Map.Entry<Date, ArrayList<Transaction>> entry : transactions.entrySet()) {
            Date date1 = entry.getKey();
            if (checkIfSameMonth(date1, date2)) {
                for (Transaction transaction : entry.getValue()) {
                    System.out.printf("%tF", date1);
                    System.out.println(" has transaction sales " + transaction.getSales());
                }
            }
        }
    }

    public boolean checkIfSameMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
            return true;
        } else {
            return false;
        }
    }
}
