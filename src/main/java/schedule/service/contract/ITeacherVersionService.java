package schedule.service.contract;

import schedule.entities.Teacher;
import schedule.entities.TeacherVersion;

public interface ITeacherVersionService extends IService<TeacherVersion> {

	public TeacherVersion getForTeacher(Teacher teacher) throws Exception;

	public TeacherVersion update(TeacherVersion entity) throws Exception;
}
