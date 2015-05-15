package schedule.service.contract;

import java.util.List;

import schedule.entities.Group;
import schedule.entities.Lesson;
import schedule.entities.LessonGroupLink;

public interface ILessonGroupLinkService extends IService<LessonGroupLink> {

	public List<LessonGroupLink> getForGroup(Group group) throws Exception;

	public List<LessonGroupLink> getForLesson(Lesson lesson) throws Exception;

	public int createAll(List<LessonGroupLink> entities) throws Exception;
}
