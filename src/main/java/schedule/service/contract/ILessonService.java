package schedule.service.contract;

import java.util.List;

import schedule.entities.Lesson;
import schedule.entities.Room;
import schedule.entities.Teacher;
import schedule.service.contract.IService;

public interface ILessonService extends IService<Lesson> {

	public int createAll(List<Lesson> entities) throws Exception;

	public List<Lesson> getForTeacher(Teacher teacher) throws Exception;

	public List<Lesson> getForRoom(Room room) throws Exception;
}
