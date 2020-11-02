public class SalesReportApp {
    public Report getReport(String departmentName, String reportType, Store store) throws Exception{
        Class<?> clz = Class.forName(reportType);
        Class[] cArg = new Class[2];
        cArg[0] = String.class;
        cArg[1] = Store.class;
        return (Report) clz.getDeclaredConstructor(cArg).newInstance(departmentName, store);
    }
}
