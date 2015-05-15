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
import schedule.entities.Lesson;
import schedule.entities.LessonGroupLink;
import schedule.service.contract.ILessonGroupLinkService;

@Stateless
public class LessonGroupLinkService implements ILessonGroupLinkService {

	@Inject
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<LessonGroupLink> getAll() throws Exception {
		Session session = em.unwrap(Session.class);
		return session.createCriteria(LessonGroupLink.class).list();
	}

	@Override
	public LessonGroupLink getForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		return (LessonGroupLink) session.get(LessonGroupLink.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<LessonGroupLink> getForGroup(Group group) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(LessonGroupLink.class);
		Criterion criterion = Restrictions.eq("group.id", group.getId());
		return criteria.add(criterion).list();
	}

	@SuppressWarnings("unchecked")
	public List<LessonGroupLink> getForLesson(Lesson lesson) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(LessonGroupLink.class);
		Criterion criterion = Restrictions.eq("lesson.id", lesson.getId());
		return criteria.add(criterion).list();
	}

	@Override
	public LessonGroupLink deleteForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		LessonGroupLink entity = (LessonGroupLink) session.get(LessonGroupLink.class, id);
		if (entity != null) {
			session.delete(entity);
		}
		return entity;
	}

	@Override
	public LessonGroupLink create(LessonGroupLink entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	public int createAll(List<LessonGroupLink> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		session.getTransaction().begin();
		int count = 0;
		for (LessonGroupLink entity : entities) {
			session.save(entity);
			count++;
		}
		session.getTransaction().commit();
		return count;
	}

	@Override
	public LessonGroupLink delete(LessonGroupLink entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.delete(session.contains(entity) ? entity : session.merge(entity));
		return entity;
	}

	@Override
	public int delete(List<LessonGroupLink> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		int count = 0;
		for (LessonGroupLink entity : entities) {
			session.delete(session.contains(entity) ? entity : session.merge(entity));
			count++;
		}
		return count;
	}

}
