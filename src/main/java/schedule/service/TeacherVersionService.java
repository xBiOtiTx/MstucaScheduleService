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
import schedule.entities.TeacherVersion;
import schedule.service.contract.ITeacherVersionService;

@Stateless
public class TeacherVersionService implements ITeacherVersionService {

	@Inject
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<TeacherVersion> getAll() throws Exception {
		Session session = em.unwrap(Session.class);
		return session.createCriteria(TeacherVersion.class).list();
	}

	@Override
	public TeacherVersion getForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		return (TeacherVersion) session.get(TeacherVersion.class, id);
	}

	@Override
	public TeacherVersion getForTeacher(Teacher teacher) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TeacherVersion.class);
		Criterion criterion = Restrictions.eq("teacher.id", teacher.getId());
		return (TeacherVersion) criteria.add(criterion).uniqueResult();
	}

	@Override
	public TeacherVersion deleteForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		TeacherVersion entity = (TeacherVersion) session.get(TeacherVersion.class, id);
		if (entity != null) {
			session.delete(entity);
		}
		return entity;
	}

	@Override
	public TeacherVersion create(TeacherVersion entity) throws Exception {
		Session session = em.unwrap(Session.class);
		// session.update(entity);
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	public TeacherVersion update(TeacherVersion entity) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TeacherVersion.class);
		Criterion criterion = Restrictions.eq("teacher.id", entity.getTeacher().getId());
		TeacherVersion oldVersion = (TeacherVersion) criteria.add(criterion).uniqueResult();
		if (oldVersion != null) {
			session.delete(oldVersion);
		}
		session.save(entity);
		return entity;
	}

	@Override
	public TeacherVersion delete(TeacherVersion entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.delete(session.contains(entity) ? entity : session.merge(entity));
		return entity;
	}

	@Override
	public int delete(List<TeacherVersion> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		int count = 0;
		for (TeacherVersion entity : entities) {
			session.delete(session.contains(entity) ? entity : session.merge(entity));
			count++;
		}
		return count;
	}

}
