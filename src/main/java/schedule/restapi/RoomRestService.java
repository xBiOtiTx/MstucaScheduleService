package schedule.restapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import schedule.entities.ELessonType;
import schedule.entities.Group;
import schedule.entities.GroupVersion;
import schedule.entities.Lesson;
import schedule.entities.Room;
import schedule.entities.Teacher;
import schedule.entities.TeacherVersion;

@Path("/api/rooms")
public class RoomRestService {

	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public List<Room> getAll() {
		List<Room> items = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			items.add(new Room("Room" + i));
		}
		return items;
	}

	@GET
	@Path("/{id}")
	@Produces("application/vnd.customer+json")
	public Room get(@PathParam("id") Integer id) {
		return new Room("Room");
	}
}
