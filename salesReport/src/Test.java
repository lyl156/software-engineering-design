import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {
    public static void main(String[] args) throws Exception{
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date   date       = simpleDateFormat.parse ( "2012-1-31" );
////        System.out.println(date);
//
//        Date date1 = new Date();
////        System.out.printf("%tF", date1);
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);

          //main test
//        // create store and department
//        Store store = new Store("myStore");
//        Department myDepartment = new Department("myDepartment");
//        Department hisDepartment = new Department("hisDepartment");
//        store.addDepartment(myDepartment);
//        store.addDepartment(hisDepartment);
//
//        //create date and transaction
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date   date2       = simpleDateFormat.parse ( "2019-5-31" );
//        Transaction t1 = new Transaction(new Date(), 100);
//        Transaction t2 = new Transaction(date2, 200);
//
//        Date   date3       = simpleDateFormat.parse ( "2020-5-31" );
//        Transaction t3 = new Transaction(date3, 555);
//        hisDepartment.addTransaction(t3);
//
//        //add transaction
//        myDepartment.addTransaction(t1);
//        myDepartment.addTransaction(t2);
//
//        //use app
//        SalesReportApp app = new SalesReportApp();
//        store.useApp(app, "myDepartment", "YTDSalesChart");

        //use file to start app
        String path = System.getProperty("user.dir");
        FileReader fr = new FileReader(path + "/" + args[0]);
        BufferedReader br = new BufferedReader(fr);
        handle(br);
        br.close();
        fr.close();
    }
    public static void handle(BufferedReader br) throws Exception {
        String inputLine = br.readLine();
        String[] inputs = inputLine.trim().split(" ");

        Store store = new Store(inputs[0]);
        for (int i = 1; i < inputs.length; i++) {
            String departmentName = inputs[i];
            Department department = new Department(departmentName);
            store.addDepartment(department);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        while ((inputLine = br.readLine()) != null) {
            String i[] = inputLine.trim().split(" ");
            if (i[1].equals("YTDSalesChart") || i[1].equals("MonthlyReport")) {
                System.out.println("\n----------------------App start---------------------\n");
                SalesReportApp app = new SalesReportApp();
                Report report = app.getReport(i[0], i[1], store);
                report.getData(store, i[0]);
            } else {
                try {
                    //check if date is valid
                    Date date = simpleDateFormat.parse(i[1]);
                    int sales = Integer.parseInt(i[2]);
                    Transaction transaction = new Transaction(date, sales);
                    Department department = store.getDepartment(i[0]);
                    department.addTransaction(transaction);
                } catch (ParseException e) {
                    System.out.println(i[1]+ " is given wrong date");
                }
            }
        }
    }
}
