package schedule.restapi;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import schedule.Greeting;
import schedule.entities.Group;
import schedule.entities.GroupVersion;
import schedule.entities.Lesson;
import schedule.entities.LessonGroupLink;
import schedule.service.contract.IGroupService;
import schedule.service.contract.IGroupVersionService;
import schedule.service.contract.ILessonGroupLinkService;
import schedule.service.contract.ILessonService;
import schedule.service.contract.IService;

@Path("/api/groups")
@RequestScoped
@Stateful
// @Stateless
public class GroupRestService {

	@Inject
	private IGroupService groupService;

	@Inject
	private ILessonGroupLinkService lessonGroupLinkService;

	@Inject
	private IGroupVersionService groupVersionService;

	private List<Lesson> getLessons(List<LessonGroupLink> links) {
		List<Lesson> lessons = new ArrayList<Lesson>(links.size());
		for (LessonGroupLink link : links) {
			lessons.add(link.getLesson());
		}
		return lessons;
	}

	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public List<Group> getAll() throws Exception {
		return groupService.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces("application/vnd.customer+json")
	public Group get(@PathParam("id") Long id) throws Exception {
		return groupService.getForId(id);
	}

	@GET
	@Path("/{id}/lessons")
	@Produces("application/vnd.customer+json")
	public List<Lesson> getLessons(@PathParam("id") Long id) throws Exception {
		Group group = groupService.getForId(id);
		List<LessonGroupLink> links = lessonGroupLinkService.getForGroup(group);
		return getLessons(links);
	}

	@GET
	@Path("/{id}/version")
	@Produces("application/vnd.customer+json")
	public GroupVersion getVersion(@PathParam("id") Long id) throws Exception {
		Group group = groupService.getForId(id);
		GroupVersion version = groupVersionService.getForGroup(group);
		return version;
	}
}
