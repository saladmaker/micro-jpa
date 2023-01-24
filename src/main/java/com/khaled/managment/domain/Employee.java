package com.khaled.managment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;
import static com.khaled.managment.domain.Employee.*;
import jakarta.persistence.NamedQueries;

/**
 *
 * @author khaled
 */
@Entity

@NamedQuery(name = FIND_ALL, query = "SELECT e FROM Employee e")
@NamedQuery(name = FIND_BY_ID,
        query = "SELECT e FROM Employee e WHERE e.id = :id")
@NamedQuery(name = FIND_BY_FIRST_NAME,
        query = "SELECT e FROM Employee e WHERE e.firstName = :fistName")
@NamedQuery(name = FIND_BY_LAST_NAME,
        query = "SELECT e FROM Employee e WHERE e.lastName = :lastName")
@NamedQuery(name = FIND_BY_DEPARTMENT,
        query = "SELECT e FROM Employee e WHERE e.department.id = :id")
@NamedQuery(name = FIND_BY_ALL,query ="""
SELECT e FROM Employee e WHERE e.firstName like :firstName
AND e.lastName LIKE :lastName AND e.department = :department
"""
)
public class Employee {

    public static final String FIND_ALL = "findAll";
    public static final String FIND_BY_ID = "findById";
    public static final String FIND_BY_FIRST_NAME = "findByFirstName";
    public static final String FIND_BY_LAST_NAME = "findByLastName";
    public static final String FIND_BY_DEPARTMENT = "findByDepartment";
    public static final String FIND_BY_ALL = "findByAll";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "last_name")
    private String lastName;
    @Min(1000)
    @Column(name = "salary")
    private BigDecimal wage;
    @ManyToOne
    private Department department;

    public Employee() {
    }

    public Employee(String firstName, String lastName, BigDecimal wage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wage = wage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Employee e)
                && Objects.equals(e.id, this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", wage=" + wage + ", department=" + department.getId() + '}';
    }

}
