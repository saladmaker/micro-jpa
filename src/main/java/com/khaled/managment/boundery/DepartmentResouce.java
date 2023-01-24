package com.khaled.managment.boundery;

import com.khaled.managment.delegate.DepartmentRepository;
import com.khaled.managment.domain.Department;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.util.List;

/**
 *
 * @author khaled
 */
@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class DepartmentResouce {

    @Inject
    private DepartmentRepository repo;

    @GET
    @Transactional(Transactional.TxType.REQUIRED)
    public List<Department> getAll() {
        return repo.findAll();
    }

    @Path("{id}")
    @GET
    @Transactional(Transactional.TxType.REQUIRED)
    public Department getById(@PathParam("id") Long id) {
        return repo.findById(id).get();
    }

    @Path("/name/{name}")
    @GET
    @Transactional(Transactional.TxType.REQUIRED)
    public Department getByName(@PathParam("name") String name) {
        return repo.findByName(name).get();
    }

    @POST
    public Response create(Department department) {
        repo.save(department);
        return Response.created(
                UriBuilder.fromResource(DepartmentResouce.class)
                        .build(department.getId())
        ).build();

    }
    @Path("{id}")
    @PUT
    public Response update(Department depratment){
        repo.update(depratment);
        return Response.accepted(
                UriBuilder.fromResource(DepartmentResouce.class)
                .build(depratment.getId())
        ).build();               
    }
}
