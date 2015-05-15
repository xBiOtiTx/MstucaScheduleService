package schedule.service.contract;

import schedule.entities.LessonTitle;

public interface ILessonTitleService extends IService<LessonTitle> {

	public LessonTitle getForTitle(String title) throws Exception;

	public LessonTitle createIfNotExist(LessonTitle entity) throws Exception;

}
