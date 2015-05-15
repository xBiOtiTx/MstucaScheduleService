package schedule.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import schedule.entities.Teacher;
import schedule.service.contract.ITeacherService;

@Stateless
public class TeacherService implements ITeacherService {

	@Inject
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Teacher> getAll() throws Exception {
		Session session = em.unwrap(Session.class);
		return session.createCriteria(Teacher.class).list();
	}

	@Override
	public Teacher getForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		return (Teacher) session.get(Teacher.class, id);
	}

	public Teacher getForName(String name) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Teacher.class);
		Criterion criterion = Restrictions.eq(Teacher.COLUMN_NAME, name);
		return (Teacher) criteria.add(criterion).uniqueResult();
	}

	@Override
	public Teacher deleteForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		Teacher entity = (Teacher) session.get(Teacher.class, id);
		if (entity != null) {
			session.delete(entity);
		}
		return entity;
	}

	@Override
	public Teacher create(Teacher entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	public Teacher createIfNotExist(Teacher entity) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Teacher.class);
		Criterion criterion = Restrictions.eq(Teacher.COLUMN_NAME, entity.getName());
		Teacher found = (Teacher) criteria.add(criterion).uniqueResult();
		if (found != null) {
			return found;
		}
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	@Override
	public Teacher delete(Teacher entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.delete(session.contains(entity) ? entity : session.merge(entity));
		return entity;
	}

	@Override
	public int delete(List<Teacher> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		int count = 0;
		for (Teacher entity : entities) {
			session.delete(session.contains(entity) ? entity : session.merge(entity));
			count++;
		}
		return count;
	}

}
