package schedule.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import schedule.entities.Room;
import schedule.service.contract.IRoomService;

@Stateless
public class RoomService implements IRoomService {

	@Inject
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Room> getAll() throws Exception {
		Session session = em.unwrap(Session.class);
		return session.createCriteria(Room.class).list();
	}

	@Override
	public Room getForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		return (Room) session.get(Room.class, id);
	}

	public Room getForTitle(String title) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Room.class);
		Criterion criterion = Restrictions.eq(Room.COLUMN_TITLE, title);
		return (Room) criteria.add(criterion).uniqueResult();
	}

	@Override
	public Room deleteForId(long id) throws Exception {
		Session session = em.unwrap(Session.class);
		Room entity = (Room) session.get(Room.class, id);
		if (entity != null) {
			session.delete(entity);
		}
		return entity;
	}

	@Override
	public Room create(Room entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	public Room createIfNotExist(Room entity) throws Exception {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Room.class);
		Criterion criterion = Restrictions.eq(Room.COLUMN_TITLE, entity.getTitle());
		Room found = (Room) criteria.add(criterion).uniqueResult();
		if (found != null) {
			return found;
		}
		session.save(entity);
		// entity.setId((Long) session.save(entity));
		return entity;
	}

	@Override
	public Room delete(Room entity) throws Exception {
		Session session = em.unwrap(Session.class);
		session.delete(session.contains(entity) ? entity : session.merge(entity));
		return entity;
	}

	@Override
	public int delete(List<Room> entities) throws Exception {
		Session session = em.unwrap(Session.class);
		int count = 0;
		for (Room entity : entities) {
			session.delete(session.contains(entity) ? entity : session.merge(entity));
			count++;
		}
		return count;
	}

}
