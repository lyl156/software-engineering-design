public abstract class Report {
    private String departmentName;
    private Store store;

    public Report(String departmentName, Store store) {
        this.departmentName = departmentName;
        this.store = store;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Print the data of the report.
     *
     * @param store
     *        the store which use the app to get this report
     *
     * @param departmentName
     *        the department of the store which the user want to get the report from
     */
    abstract void getData(Store store, String departmentName);
}
