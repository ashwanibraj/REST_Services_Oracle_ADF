package model;

import java.io.Serializable;

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

@Entity
@NamedQueries({ @NamedQuery(name = "Department.findAll", query = "select o from Department o") })
@Table(name = "DEPARTMENTS")
public class Department implements Serializable {
    private static final long serialVersionUID = 692339938647019901L;
    @Id
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private Integer departmentId;
    @Column(name = "DEPARTMENT_NAME", nullable = false, length = 30)
    private String departmentName;
    @Column(name = "LOCATION_ID")
    private Integer locationId;
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee employees1;
    @OneToMany(mappedBy = "department", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Employee> employeesList1;

    public Department() {
    }

    public Department(Integer departmentId, String departmentName, Integer locationId, Employee employees1) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.locationId = locationId;
        this.employees1 = employees1;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }


    public Employee getEmployees1() {
        return employees1;
    }

    public void setEmployees1(Employee employees1) {
        this.employees1 = employees1;
    }

    public List<Employee> getEmployeesList1() {
        return employeesList1;
    }

    public void setEmployeesList1(List<Employee> employeesList1) {
        this.employeesList1 = employeesList1;
    }

    public Employee addEmployee(Employee employee) {
        getEmployeesList1().add(employee);
        employee.setDepartment(this);
        return employee;
    }

    public Employee removeEmployee(Employee employee) {
        getEmployeesList1().remove(employee);
        employee.setDepartment(null);
        return employee;
    }
}
