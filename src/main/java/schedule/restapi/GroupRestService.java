package schedule.restapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import schedule.entities.ELessonType;
import schedule.entities.Group;
import schedule.entities.GroupVersion;
import schedule.entities.Lesson;
import schedule.service.IService;

@Path("/api/groups")
public class GroupRestService {

	@Inject
	private IService<Group> service;

	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public List<Group> getAll() throws Exception {
//		List<Group> items = new ArrayList<>();
//		for (int i = 0; i < 3; i++) {
//			items.add(new Group("Group" + i));
//		}
//		return items;
		
		return service.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces("application/vnd.customer+json")
	public Group get(@PathParam("id") Integer id) {
		return new Group("Group");
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
	public GroupVersion getVersion(@PathParam("id") Integer id) {
		return new GroupVersion(new Group("Group"), new Date());
	}

}
