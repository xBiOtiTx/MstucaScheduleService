package schedule.restapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import schedule.Greeting;
import schedule.entities.ELessonType;
import schedule.entities.Group;
import schedule.entities.GroupVersion;
import schedule.entities.Lesson;
import schedule.service.IService;

@Path("/api/groups")
// @RequestScoped
public class GroupRestService {

	@Inject
	private IService<Group> service;

	//@Inject
	//private EntityManager em;

	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public List<Group> getAll() throws Exception {
		
		
		
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
