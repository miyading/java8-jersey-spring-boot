package com.thoughtworks.gaia.A_1_N.endpoint;

import com.thoughtworks.gaia.A_1_N.entity.A_1_N;
import com.thoughtworks.gaia.A_1_N.entity.A_B;
import com.thoughtworks.gaia.A_1_N.service.A_1_NService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("a")
@Api(value = "a", description = "Access to a resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class A_1_NEndPoint {
  @Autowired
  private A_1_NService a_1_NService;
  
  @Path("/{a_id}")
  @ApiOperation(value = "Get a by id", response = A_1_N.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Get a successfully"),
    @ApiResponse(code = 404, message = "No a matches given id")
  })
  @GET
  public Response getA(@PathParam("a_id") Long aId) {
    A_B a_b = a_1_NService.getA(aId);
    return Response.ok().entity(a_b).build();
  }
  
  @Path("/")
  @ApiOperation(value = "Get all A", response = A_1_N.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Get a successfully"),
    @ApiResponse(code = 404, message = "No A")
  })
  @GET
  public Response getAll() {
    List<A_B> a_bs = a_1_NService.getAll();
    GenericEntity<List<A_B>> a_bEntity = new GenericEntity<List<A_B>>(a_bs){};
    return Response.ok().entity(a_bEntity).build();
  }
  
  @Path("/delete/{a_id}")
  @ApiOperation(value = "Delete a by id", response = A_1_N.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Delete a successfully"),
    @ApiResponse(code = 404, message = "No a matches given id")
  })
  @GET
  public Response deleteA(@PathParam("a_id") Long aId) {
    List<A_B> a_bs = a_1_NService.deleteA(aId);
    GenericEntity<List<A_B>> a_bEntity = new GenericEntity<List<A_B>>(a_bs){};
    return Response.ok().entity(a_bEntity).build();
  }
}
