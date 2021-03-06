package schedule.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import schedule.entities.Group;
import schedule.service.contract.IGroupService;

@Stateless
public class GroupService implements IGroupService {

	@Inject
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Group> getAll() throws Exception {
		Session session = em.unwrap(Session.class);
		return session.createCriteria(Group.class).list();
	}

	@Override
	public Group getForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		return (Group) session.get(Group.class, id);
	}

	public Group getForTitle(String title) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Group.class);
		Criterion criterion = Restrictions.eq(Group.COLUMN_TITLE, title);
		return (Group) criteria.add(criterion).uniqueResult();
	}

	@Override
	public Group deleteForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		Group entity = (Group) session.get(Group.class, id);
		if (entity != null) {
			session.delete(entity);
		}
		return entity;
	}

	@Override
	public Group create(Group entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	@Override
	public Group createIfNotExist(Group entity) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Group.class);
		Criterion criterion = Restrictions.eq(Group.COLUMN_TITLE, entity.getTitle());
		Group found = (Group) criteria.add(criterion).uniqueResult();
		if (found != null) {
			return found;
		}
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	@Override
	public Group delete(Group entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.delete(session.contains(entity) ? entity : session.merge(entity));
		return entity;
	}

	@Override
	public int delete(List<Group> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		int count = 0;
		for (Group entity : entities) {
			session.delete(session.contains(entity) ? entity : session.merge(entity));
			count++;
		}
		return count;
	}

}
