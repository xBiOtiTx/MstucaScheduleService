package schedule.rest;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import schedule.Greeting;

@Path("/greeting")
public class GreetingResrService {

//    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();
//
//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name));
//    }
	
	@GET
	@Path("/{param}")
	@Produces("application/vnd.customer+json")
	public Greeting printMessage(@PathParam("param") String msg) {
		return new Greeting(0,"Hello(" + counter.incrementAndGet() + ")");
	}
	
	@GET
	@Path("/cron")
	public void cron() {
		counter.incrementAndGet();
	}
}
