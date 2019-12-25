package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.*;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/tasks")
public interface ItemsClient extends RestService {
    @GET
    void getAllItems(@HeaderParam("Authorization") String token,
                     @QueryParam("title") String title,
                     @QueryParam("owner_id") Long ownerId,
                     @QueryParam("exec_id") Long executerId,
                     @QueryParam("status_id") Long statusId,
                     MethodCallback<List<TaskDto>> items);

    @GET
    @Path("remove/{id}")
    void removeItem(@HeaderParam("Authorization") String token, @PathParam("id") String id, MethodCallback<Void> result);

    @GET
    @Path("status")
    void getAllStatuses(@HeaderParam("Authorization") String token, MethodCallback<List<StatusDto>> items);

    @GET
    @Path("users")
    void getAllUsers(@HeaderParam("Authorization") String token, MethodCallback<List<UserDto>> items);

    @GET
    @Path("get/{id}")
    void getItem(@HeaderParam("Authorization") String token, @PathParam("id") String id, MethodCallback<TaskDto> result);

    @POST
    @Path("add")
    void add(@HeaderParam("Authorization") String token, TaskDto dto, MethodCallback<Void> result);
}
