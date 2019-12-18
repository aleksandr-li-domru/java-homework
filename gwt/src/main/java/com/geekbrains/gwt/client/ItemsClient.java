package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.*;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/tasks")
public interface ItemsClient extends RestService {
    @GET
    void getAllItems(@QueryParam("title") String title,
                     @QueryParam("owner_id") Long ownerId,
                     @QueryParam("exec_id") Long executerId,
                     @QueryParam("status_id") Long statusId,
                     MethodCallback<List<TaskDto>> items);

    @GET
    @Path("remove/{id}")
    void removeItem(@PathParam("id") String id, MethodCallback<Void> result);

    @GET
    @Path("status")
    void getAllStatuses(MethodCallback<List<StatusDto>> items);

    @GET
    @Path("users")
    void getAllUsers(MethodCallback<List<UserDto>> items);
}
