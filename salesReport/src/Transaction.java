import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transaction {
    private Date date;
    private int sales;

    public Transaction(Date date, int sales) {
        this.date = date;
        this.sales = sales;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}
