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
import schedule.entities.Teacher;
import schedule.entities.TeacherVersion;
import schedule.service.contract.ILessonService;
import schedule.service.contract.ITeacherService;
import schedule.service.contract.ITeacherVersionService;

@Path("/api/teachers")
@RequestScoped
@Stateful
public class TeacherRestService {

	@Inject
	private ITeacherService teacherService;

	@Inject
	private ILessonService lessonService;

	@Inject
	private ITeacherVersionService teacherVersionService;

	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public List<Teacher> getAll() throws Exception {
		return teacherService.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces("application/vnd.customer+json")
	public Teacher get(@PathParam("id") Long id) throws Exception {
		return teacherService.getForId(id);
	}

	@GET
	@Path("/{id}/lessons")
	@Produces("application/vnd.customer+json")
	public List<Lesson> getLessons(@PathParam("id") Long id) throws Exception {
		Teacher teacher = teacherService.getForId(id);
		return lessonService.getForTeacher(teacher);
	}

	@GET
	@Path("/{id}/version")
	@Produces("application/vnd.customer+json")
	public TeacherVersion getVersion(@PathParam("id") Long id) throws Exception {
		Teacher teacher = teacherService.getForId(id);
		TeacherVersion version = teacherVersionService.getForTeacher(teacher);
		return version;
	}
}
