package schedule.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import schedule.entities.LessonTitle;
import schedule.service.contract.ILessonTitleService;

@Stateless
public class LessonTitleService implements ILessonTitleService {

	@Inject
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<LessonTitle> getAll() throws Exception {
		Session session = em.unwrap(Session.class);
		return session.createCriteria(LessonTitle.class).list();
	}

	@Override
	public LessonTitle getForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		return (LessonTitle) session.get(LessonTitle.class, id);
	}

	public LessonTitle getForTitle(String title) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(LessonTitle.class);
		Criterion criterion = Restrictions.eq(LessonTitle.COLUMN_TITLE, title);
		return (LessonTitle) criteria.add(criterion).uniqueResult();
	}

	@Override
	public LessonTitle deleteForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		LessonTitle entity = (LessonTitle) session.get(LessonTitle.class, id);
		if (entity != null) {
			session.delete(entity);
		}
		return entity;
	}

	@Override
	public LessonTitle create(LessonTitle entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	public LessonTitle createIfNotExist(LessonTitle entity) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(LessonTitle.class);
		Criterion criterion = Restrictions.eq(LessonTitle.COLUMN_TITLE, entity.getTitle());
		LessonTitle found = (LessonTitle) criteria.add(criterion).uniqueResult();
		if (found != null) {
			return found;
		}
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	@Override
	public LessonTitle delete(LessonTitle entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.delete(session.contains(entity) ? entity : session.merge(entity));
		return entity;
	}

	@Override
	public int delete(List<LessonTitle> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		int count = 0;
		for (LessonTitle entity : entities) {
			session.delete(session.contains(entity) ? entity : session.merge(entity));
			count++;
		}
		return count;
	}

}
