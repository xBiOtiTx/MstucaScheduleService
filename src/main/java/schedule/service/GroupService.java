package schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import schedule.entities.Group;

@ApplicationScoped
public class GroupService implements IService<Group> {

	//@Inject
	//private EntityManager em;

	@Override
	public List<Group> getAll() throws Exception {
		List<Group> items = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			items.add(new Group("_Group" + i));
		}

		// ConnectionSource connectionSource = new JdbcConnectionSource();
		// connectionSource.close();

		//TableUtils.dropTable(connectionSource, Group.class, true);
		//TableUtils.createTable(connectionSource, Group.class);

		//final Dao<Group, String> groupDao = DaoManager.createDao(connectionSource, Group.class);
		//Group group = groupDao.queryForId("ASD");
		//if (group == null) {
		//	group = new Group("ASD");
		//	groupDao.create(group);
		//}
		String s = "";
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			s += envName + "; ";
			//s += String.format("%s=%s%n \n",
           //         envName,
           //         env.get(envName));
           // System.out.format("%s=%s%n",
           //                   envName,
           //                   env.get(envName));
        }
		items.add(new Group(s));

		return items;
	}

	@Override
	public Group getForId(int id) throws Exception {
		return new Group("Group");
	}

}
