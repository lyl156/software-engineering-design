import java.util.HashMap;

public class Store {
    private String name;
    private HashMap<String, Department> departments = new HashMap<>();

    public Store(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Add the department to the store.
     *
     * @param department
     *        The department object to be added in this store
     *
     * @return {@code true} if there is no department which has the same name as {@code department},
     *         false if there is the same department before
     */
    boolean addDepartment(Department department) {
        if (departments.containsKey(department.getName())) {
            System.out.println("this department has already added.");
            return false;
        }
        departments.put(department.getName(), department);
        System.out.println("Store " + this.getName() + " add " + department.getName() + " department ");
        return true;
    }

    /**
     * Get the specific department.
     *
     * @param name
     *        the name of the department that you want to get
     *
     * @return {@code department} that has the same you specify
     */
    public Department getDepartment(String name) {
        if (departments.containsKey(name)) {
            return departments.get(name);
        } else {
            return null;
        }
    }

    /**
     * Use the {@code SalesReportApp} to get the different report.
     *
     * @param salesReportApp
     *        the {@code SalesReportApp} that you want to use
     *
     * @param departmentName
     *        the department you want to get report from
     *
     * @param reportType
     *        the report type you want
     *
     * @throws Exception
     */
    public void useApp(SalesReportApp salesReportApp, String departmentName, String reportType) throws Exception {
        Report report = salesReportApp.getReport(departmentName, reportType, this);

        report.getData(this, departmentName);
        //println(report.string);
    }
}
