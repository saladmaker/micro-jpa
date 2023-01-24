package com.khaled.managment.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import static com.khaled.managment.domain.Department.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author khaled
 */
@Entity
@NamedQuery(name = FIND_ALL, query = "SELECT d FROM Department d")
@NamedQuery(name = FIND_BY_ID,
        query = "SELECT d FROM Department d WHERE d.id = :id")
@NamedQuery(name = FIND_BY_NAME,
        query = "SELECT d FROM Department d WHERE d.name = :name")
@NamedQuery(name = FIND_BY_CHIEF,
        query = "SELECT d FROM Department d WHERE d.chief.id = :id")
public class Department {

    public static final String FIND_ALL = "Department.findAll";
    public static final String FIND_BY_ID = "Department.findById";
    public static final String FIND_BY_NAME = "Department.findByFirstName";
    public static final String FIND_BY_CHIEF = "Department.findByLastName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;
    private String description;
    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Employee> employees = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "depratment_chier")
    private Employee chief;

    public Department() {
    }

    public Department(String name, String Description) {
        this.description = description;
        this.name = name;
    }

    public boolean equals(Object other) {
        return (other instanceof Department d)
                && Objects.equals(this.id, d.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Employee getChief() {
        return chief;
    }

    public void setChief(Employee chief) {
        this.chief = chief;
    }

    public boolean addEmployee(Employee employee) {
        return getEmployees().add(employee);
    }

    public boolean removeEmployee(Employee employee) {
        return getEmployees().remove(employee);
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

}
