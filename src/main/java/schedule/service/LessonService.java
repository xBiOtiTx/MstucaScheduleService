package schedule.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import schedule.entities.Lesson;
import schedule.entities.Room;
import schedule.entities.Teacher;
import schedule.service.contract.ILessonService;

@Stateless
public class LessonService implements ILessonService {

	@Inject
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Lesson> getAll() throws Exception {
		Session session = em.unwrap(Session.class);
		return session.createCriteria(Lesson.class).list();
	}

	@Override
	public Lesson getForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		return (Lesson) session.get(Lesson.class, id);
	}

	@Override
	public List<Lesson> getForTeacher(Teacher teacher) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Lesson.class);
		Criterion criterion = Restrictions.eq("teacher.id", teacher.getId());
		return criteria.add(criterion).list();
	}

	@Override
	public List<Lesson> getForRoom(Room room) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Lesson.class);
		Criterion criterion = Restrictions.eq("room.id", room.getId());
		return criteria.add(criterion).list();
	}

	@Override
	public Lesson deleteForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		Lesson entity = (Lesson) session.get(Lesson.class, id);
		if (entity != null) {
			session.delete(entity);
		}
		return entity;
	}

	public int createAll(List<Lesson> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		session.getTransaction().begin();
		int count = 0;
		for (Lesson entity : entities) {
			session.save(entity);
			count++;
		}
		session.getTransaction().commit();
		return count;
	}

	@Override
	public Lesson create(Lesson entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	@Override
	public Lesson delete(Lesson entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.delete(session.contains(entity) ? entity : session.merge(entity));
		return entity;
	}

	@Override
	public int delete(List<Lesson> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		int count = 0;
		for (Lesson entity : entities) {
			session.delete(session.contains(entity) ? entity : session.merge(entity));
			count++;
		}
		return count;
	}
}
