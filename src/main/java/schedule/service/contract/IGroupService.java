package schedule.service.contract;

import schedule.entities.Group;

public interface IGroupService extends IService<Group> {
	public Group getForTitle(String title) throws Exception;

	public Group createIfNotExist(Group entity) throws Exception;
}
