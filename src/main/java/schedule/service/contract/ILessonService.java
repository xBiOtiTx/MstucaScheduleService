package schedule.service.contract;

import java.util.List;

import schedule.entities.Lesson;
import schedule.service.contract.IService;

public interface ILessonService extends IService<Lesson> {

	public int createAll(List<Lesson> entities) throws Exception;

}
