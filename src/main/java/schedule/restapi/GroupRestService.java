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

	// @Inject
	// private EntityManager em;

	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public List<Group> getAll() throws Exception {
		// List<Group> items = new ArrayList<>();
		// for (int i = 0; i < 3; i++) {
		// items.add(new Group("Group" + i));
		// }
		// return items;

		// em.persist(new Greeting((long) 0, "null"));

		// Map<String, String> env = System.getenv();
		
		String url = System.getenv("OPENSHIFT_SCHEDULE_DB_URL");
		String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
		String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
		
		Connection connection = DriverManager.getConnection(url, username,
	            password);
		
		// ConnectionSource connectionSource = new JdbcConnectionSource(url);
		// connectionSource.close();

		// TableUtils.dropTable(connectionSource, Group.class, true);
		// TableUtils.createTable(connectionSource, Group.class);

		// final Dao<Group, String> groupDao = DaoManager.createDao(connectionSource, Group.class);
		// Group group = groupDao.queryForId("ASD");
		// if (group == null) {
		// group = new Group("ASD");
		// groupDao.create(group);
		// }

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
