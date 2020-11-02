import java.util.*;

public class YTDSalesChart extends Report{
    public YTDSalesChart(String departmentName, Store store) {
        super(departmentName, store);
    }

    /**
     * Print the year-to-date sales for the selected department by month.
     * @param store
     *        the store which use the app to get this report
     *
     * @param departmentName
     *        the department of the store which the user want to get the report from
     */
    @Override
    void getData(Store store, String departmentName) {
        Department department = store.getDepartment(departmentName);
        if (department == null) return;

        HashMap<Date, ArrayList<Transaction>> transactions = department.getTransactions();

        int[] chart = new int[12];
        setChart(chart, transactions);
        int i = 1;
        System.out.println(departmentName +" department in " + store.getName() + " store:" + " YTDSalesChart");
        for (Integer monthlyChart : chart) {
            System.out.println(i + " month has " + monthlyChart + " sales");
            i++;
        }
    }

    void setChart(int[] chart, HashMap<Date, ArrayList<Transaction>> transactions) {
        for (Map.Entry<Date, ArrayList<Transaction>> entry : transactions.entrySet()) {
            Date date1 = entry.getKey();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            Date date2 = new Date(System.currentTimeMillis());
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);

            //if not the same year
            if (cal.get(Calendar.YEAR) != cal2.get(Calendar.YEAR))
                continue;

            if (cal.get(Calendar.MONTH) == 0) {
                chart[0] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 1) {
                chart[1] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 2) {
                chart[2] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 3) {
                chart[3] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 4) {
                chart[4] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 5) {
                chart[5] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 6) {
                chart[6] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 7) {
                chart[7] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 8) {
                chart[8] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 9) {
                chart[9] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 10) {
                chart[10] += getSum(entry.getValue());
            } else if (cal.get(Calendar.MONTH) == 11) {
                chart[11] += getSum(entry.getValue());
            }
        }
    }
    int getSum(ArrayList<Transaction> transactions) {
        int sum = 0;
        for (Transaction transaction : transactions) {
            sum += transaction.getSales();
        }
        return sum;
    }
}
