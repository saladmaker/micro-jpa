package com.khaled.managment.boundery;

import com.khaled.managment.delegate.EmployeeRepository;
import com.khaled.managment.domain.Employee;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.util.List;

/**
 *
 * @author khaled
 */
@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResouce {

    @Inject
    private EmployeeRepository repo;

    @GET
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @Path("{id}")
    @GET
    public Employee getById(@PathParam("id") String id) {
        return repo.findById(id).get();
    }

    @Path("firstname/{firstname}")
    @GET
    public List<Employee> getByFirstName(@PathParam("firstname") String firstName) {
        return repo.findByFirstName(firstName);
    }

    @Path("lastname/{lastname}")
    @GET
    public List<Employee> getBylaststName(@PathParam("lastname") String lastName) {
        return repo.findByLastName(lastName);
    }

    @Path("department/{department}")
    @GET
    public List<Employee> getByDepartment(@PathParam("department") String department) {
        return repo.findByDepartment(department);
    }
    @Path("query/")
    @GET
    public List<Employee> getByFirstNameAndLasNameAndDepartment(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("department") Long department
    ){
        return repo.findByFirstNameAndLastNameAndDepratment(
                firstName, lastName,department
        );
    }
    @POST
    public Response create(Employee employee) {
        repo.save(employee);
        return Response.created(
                UriBuilder.fromResource(EmployeeResouce.class)
                        .build(employee.getId())
        ).build();

    }

    @Path("{id}")
    @PUT
    public Response update(Employee employee) {
        repo.update(employee);
        return Response.accepted(
                UriBuilder.fromResource(EmployeeResouce.class)
                        .build(employee.getId())
        ).build();
    }

}
