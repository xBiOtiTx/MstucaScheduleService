package schedule.restapi;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import schedule.ScheduleParser;
import schedule.ScheduleUpdater;
import schedule.entities.GroupDownloadLink;
import schedule.entities.RawLesson;
import schedule.service.GroupDownloadLinkService;
import schedule.utils.ScheduleUtils;

//http://localhost:8080/schedule/rest/api/update
@Path("/api/update")
@RequestScoped
public class UpdateRestService {

	@Inject
	private GroupDownloadLinkService groupDownloadLinkService;

	@Inject
	private ScheduleUpdater scheduleUpdater;

	// TODO асинхронно
	@GET
	@Path("/")
	@Produces("application/vnd.customer+json")
	public void update() throws Exception {
		System.out.println("hi!");
		ScheduleParser parser = new ScheduleParser();
		List<GroupDownloadLink> links = groupDownloadLinkService.getAll();
		System.out.println("links: " + links);
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
		System.out.println("bye!");
	}
}
