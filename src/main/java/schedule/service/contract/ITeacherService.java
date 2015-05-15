package schedule.service.contract;

import schedule.entities.Teacher;

public interface ITeacherService extends IService<Teacher> {

	public Teacher getForName(String name) throws Exception;

	public Teacher createIfNotExist(Teacher entity) throws Exception;
}
