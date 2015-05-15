package schedule.service.contract;

import schedule.entities.Group;
import schedule.entities.GroupVersion;

public interface IGroupVersionService extends IService<GroupVersion> {

	public GroupVersion getForGroup(Group group) throws Exception;

	public GroupVersion update(GroupVersion entity) throws Exception;
}
