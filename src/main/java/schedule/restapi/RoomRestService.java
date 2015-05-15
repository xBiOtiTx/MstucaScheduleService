package schedule.restapi;

import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import schedule.entities.Lesson;
import schedule.entities.Room;
import schedule.service.contract.ILessonService;
import schedule.service.contract.IRoomService;

@Path("/api/rooms")
@RequestScoped
@Stateful
public class RoomRestService {

	@Inject
	private IRoomService roomService;

	@Inject
	private ILessonService lessonService;

	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public List<Room> getAll() throws Exception {
		return roomService.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces("application/vnd.customer+json")
	public Room get(@PathParam("id") Long id) throws Exception {
		return roomService.getForId(id);
	}

	@GET
	@Path("/{id}/lessons")
	@Produces("application/vnd.customer+json")
	public List<Lesson> getLessons(@PathParam("id") Long id) throws Exception {
		Room room = roomService.getForId(id);
		return lessonService.getForRoom(room);
	}
}
