package schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import schedule.entities.Group;

@ApplicationScoped
public class GroupService implements IService<Group> {

	// @Inject
	// private EntityManager em;

	@Override
	public List<Group> getAll() throws Exception {
		List<Group> items = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			items.add(new Group("_Group" + i));
		}

		return items;
	}

	@Override
	public Group getForId(int id) throws Exception {
		return new Group("Group");
	}

}
