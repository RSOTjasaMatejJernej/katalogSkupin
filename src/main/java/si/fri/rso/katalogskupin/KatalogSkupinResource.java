
package si.fri.rso.katalogskupin;

import com.kumuluz.ee.common.runtime.EeRuntime;
import org.eclipse.microprofile.metrics.annotation.Metered;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;




@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("katalogSkupin")
@ApplicationScoped
public class KatalogSkupinResource {

    @Inject
    private RestProperties restProperties;

    @GET
    @Metered
    public Response getAllSkupins() {
        List<Skupina> skupinas = Database.getSkupinas();
        return Response.ok(skupinas).build();
    }

    @GET
    @Path("{skupinaId}")
    public Response getSkupina(@PathParam("skupinaId") Integer skupinaId) {
        Skupina skupina = Database.getSkupina(skupinaId.toString());
        return skupina != null
                ? Response.ok(skupina).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewSkupina(Skupina skupina) {
        Database.addSkupina(skupina);
        return Response.ok(skupina).build();
    }

    @DELETE
    @Path("{skupinaId}")
    public Response deleteSkupina(@PathParam("skupinaId") String skupinaId) {
        Database.deleteSkupina(skupinaId);
        return Response.ok(Response.Status.OK).build();
    }

    @POST
    @Path("healthy")
    public Response setHealth(Boolean healthy) {
        restProperties.setHealthy(healthy);
        return Response.ok().build();
    }

    @POST
    @Path("load")
    public Response loadOrder(Integer n) {

        for (int i = 1; i <= n; i++) {
            fibonacci(i);
        }

        return Response.status(Response.Status.OK).build();
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @GET
    @Path("instanceid")
    public Response getInstanceId() {

        String instanceId =
                "{\"instanceId\" : \"" + EeRuntime.getInstance().getInstanceId() + "\"}";

        return Response.ok(instanceId).build();
    }


}