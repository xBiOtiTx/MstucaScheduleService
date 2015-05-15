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
import schedule.entities.GroupVersion;
import schedule.service.contract.IGroupVersionService;

@Stateless
public class GroupVersionService implements IGroupVersionService {

	@Inject
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<GroupVersion> getAll() throws Exception {
		Session session = em.unwrap(Session.class);
		return session.createCriteria(GroupVersion.class).list();
	}

	@Override
	public GroupVersion getForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		return (GroupVersion) session.get(GroupVersion.class, id);
	}

	public GroupVersion getForGroup(Group group) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(GroupVersion.class);
		Criterion criterion = Restrictions.eq("group.id", group.getId());
		return (GroupVersion) criteria.add(criterion).uniqueResult();
	}

	@Override
	public GroupVersion deleteForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		GroupVersion entity = (GroupVersion) session.get(GroupVersion.class, id);
		if (entity != null) {
			session.delete(entity);
		}
		return entity;
	}

	@Override
	public GroupVersion create(GroupVersion entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.update(entity);
		// session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	public GroupVersion update(GroupVersion entity) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(GroupVersion.class);
		Criterion criterion = Restrictions.eq("group.id", entity.getGroup().getId());
		GroupVersion oldVersion = (GroupVersion) criteria.add(criterion).uniqueResult();
		if (oldVersion != null) {
			session.delete(oldVersion);
		}
		session.save(entity);
		return entity;
	}

	@Override
	public GroupVersion delete(GroupVersion entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.delete(session.contains(entity) ? entity : session.merge(entity));
		return entity;
	}

	@Override
	public int delete(List<GroupVersion> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		int count = 0;
		for (GroupVersion entity : entities) {
			session.delete(session.contains(entity) ? entity : session.merge(entity));
			count++;
		}
		return count;
	}

}
