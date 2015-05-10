package schedule.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import schedule.entities.Group;

@ApplicationScoped
public class GroupService implements IService<Group> {

	@Override
	public List<Group> getAll() throws Exception {
		List<Group> items = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			items.add(new Group("Group" + i));
		}
		return items;
	}

	@Override
	public Group getForId(int id) throws Exception {
		return new Group("Group");
	}

}
