package model;

import java.io.Serializable;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ @NamedQuery(name = "Employee.findAll", query = "select o from Employee o") })
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {
    private static final long serialVersionUID = 4317572485269735233L;
    @Column(name = "COMMISSION_PCT")
    private Integer commissionPct;
    @Column(nullable = false, unique = true, length = 25)
    private String email;
    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false)
    private Integer employeeId;
    @Column(name = "FIRST_NAME", length = 20)
    private String firstName;
    @Temporal(TemporalType.DATE)
    @Column(name = "HIRE_DATE", nullable = false)
    private Date hireDate;
    @Column(name = "JOB_ID", nullable = false, length = 10)
    private String jobId;
    @Column(name = "LAST_NAME", nullable = false, length = 25)
    private String lastName;
    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;
    private Integer salary;
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee employees;
    @OneToMany(mappedBy = "employees", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Employee> employeesList;
    @OneToMany(mappedBy = "employees1", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Department> departmentList;
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    public Employee() {
    }

    public Employee(Integer commissionPct, Department department, String email, Integer employeeId, String firstName,
                    Date hireDate, String jobId, String lastName, Employee employees, String phoneNumber,
                    Integer salary) {
        this.commissionPct = commissionPct;
        this.department = department;
        this.email = email;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.lastName = lastName;
        this.employees = employees;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public Integer getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Integer commissionPct) {
        this.commissionPct = commissionPct;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public Employee addEmployee(Employee employee) {
        getEmployeesList().add(employee);
        employee.setEmployees(this);
        return employee;
    }

    public Employee removeEmployee(Employee employee) {
        getEmployeesList().remove(employee);
        employee.setEmployees(null);
        return employee;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Department addDepartment(Department department) {
        getDepartmentList().add(department);
        department.setEmployees1(this);
        return department;
    }

    public Department removeDepartment(Department department) {
        getDepartmentList().remove(department);
        department.setEmployees1(null);
        return department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
