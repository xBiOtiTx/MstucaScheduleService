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
import schedule.entities.Teacher;
import schedule.entities.TeacherVersion;

@Path("/api/teachers")
public class TeacherRestService {

	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public List<Teacher> getAll() {
		List<Teacher> items = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			items.add(new Teacher("Teacher" + i));
		}
		return items;
	}

	@GET
	@Path("/{id}")
	@Produces("application/vnd.customer+json")
	public Teacher get(@PathParam("id") Integer id) {
		return new Teacher("Teacher");
	}

	@GET
	@Path("/{id}/lessons")
	@Produces("application/vnd.customer+json")
	public List<Lesson> getLessons(@PathParam("id") Integer id) {
		List<Lesson> lessons = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			lessons.add(new Lesson(null, i, null, null, ELessonType.LECTURE, 0, null));
		}
		return lessons;
	}

	@GET
	@Path("/{id}/version")
	@Produces("application/vnd.customer+json")
	public TeacherVersion getVersion(@PathParam("id") Integer id) {
		return new TeacherVersion(new Teacher("Teacher"), new Date());
	}

}
