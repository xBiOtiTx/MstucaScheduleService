package schedule.restapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import schedule.ScheduleParser;
import schedule.ScheduleUpdater;
import schedule.entities.ELessonType;
import schedule.entities.Group;
import schedule.entities.GroupDownloadLink;
import schedule.entities.Lesson;
import schedule.entities.LessonGroupLink;
import schedule.entities.LessonTitle;
import schedule.entities.RawLesson;
import schedule.entities.Room;
import schedule.entities.Teacher;
import schedule.service.GroupDownloadLinkService;
import schedule.service.contract.IGroupService;
import schedule.service.contract.IGroupVersionService;
import schedule.service.contract.ILessonGroupLinkService;
import schedule.service.contract.ILessonService;
import schedule.service.contract.ILessonTitleService;
import schedule.service.contract.IRoomService;
import schedule.service.contract.ITeacherService;
import schedule.service.contract.ITeacherVersionService;
import schedule.utils.ScheduleUtils;
import schedule.utils.TimeWatcher;

//http://localhost:8080/schedule/rest/api/update
@Path("/api/update")
@RequestScoped
public class UpdateRestService {

	@Inject
	private GroupDownloadLinkService groupDownloadLinkService;

	@Inject
	private ScheduleUpdater scheduleUpdater;

	@Inject
	private IGroupService groupService;

	@Inject
	private ITeacherService teacherService;

	@Inject
	private ILessonTitleService lessonTitleService;

	@Inject
	private IRoomService roomService;

	@Inject
	private ILessonService lessonService;

	@Inject
	private ILessonGroupLinkService lessonGroupLinkService;

	@Inject
	private IGroupVersionService groupVersionService;

	@Inject
	private ITeacherVersionService teacherVersionService;

	// TODO асинхронно
	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public void update() throws Exception {
		// Result result = JUnitCore.runClasses(TestService.class);
		// for (Failure failure : result.getFailures()) {
		// System.out.println(failure.toString());
		// }
		// System.out.println(result.wasSuccessful());

		scheduleUpdater.hi();

		// Group group = new Group("gr1");
		// groupService.create(group);
		// System.out.println("group: " + group);
		//
		// LessonTitle lessonTitle = lessonTitleService.createIfNotExist(new LessonTitle("title1"));
		// Integer lessonNumber = 1;
		// Date lessonDate = new Date();
		// Teacher teacher = teacherService.createIfNotExist(new Teacher("teacher"));
		// ELessonType lessonType = ELessonType.fromString("Лекция");
		// Integer subgroup = 1;
		// Room lessonRoom = roomService.createIfNotExist(new Room("5-112"));
		// Lesson lesson = new Lesson(lessonTitle, lessonNumber, lessonDate, teacher, lessonType, subgroup, lessonRoom);
		// lessonService.create(lesson);
		// System.out.println("lesson: " + lesson);

		//
		// LessonGroupLink link = new LessonGroupLink(lesson, group);
		// lessonGroupLinkService.create(link);
		// System.out.println("link: " + link);
		//
		// final List<LessonGroupLink> links = lessonGroupLinkService.getForGroup(group);
		// // lessonGroupLinkService.delete(links);
		// System.out.println("links: " + links);

		ScheduleParser parser = new ScheduleParser();
		List<GroupDownloadLink> links = groupDownloadLinkService.getAll();
		System.out.println("hi: " + links);
		for (GroupDownloadLink link : links) {

			// TimeWatcher.start("saveUrlToFile");
			String temp = "temp.xls";
			ScheduleUtils.saveUrlToFile(link.getDownloadLink(), temp);
			System.out.println("g: " + link.getGroupTitle());
			// TimeWatcher.stop();

			// TimeWatcher.start("parse");
			List<RawLesson> rawLessons = parser.parse(temp);
			System.out.println("l: " + rawLessons.size());
			// TimeWatcher.stop();

			// TimeWatcher.start("update");
			scheduleUpdater.update(link.getGroupTitle(), rawLessons);
			// TimeWatcher.stop();
			// break;
		}
		System.out.println("bye2!");
	}

	public void testIGroupService() throws Exception {
		groupService.getAll();
		groupService.getForId(1);
		groupService.deleteForId(1);
		groupService.create(new Group("gr0"));
		groupService.delete(new Group("gr0"));
		groupService.delete(new ArrayList<Group>());

		System.out.println("create gr1: " + groupService.create(new Group("gr1")));
		System.out.println("create gr2: " + groupService.create(new Group("gr2")));

		Group g = groupService.createIfNotExist(new Group("gr2"));
		System.out.println("createIfNotExist gr2: " + g);

		System.out.println("delete g: " + groupService.delete(g));
		System.out.println("getForId : " + groupService.getForId(1));
		System.out.println("getForTitle gr2: " + groupService.getForTitle("gr1"));
	}

	public void testITeacherService() throws Exception {
		teacherService.getAll();
	}
}
