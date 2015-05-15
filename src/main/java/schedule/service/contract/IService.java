package schedule.service.contract;

import java.util.List;

public interface IService<T> {
	public List<T> getAll() throws Exception;

	public T getForId(long id) throws Exception;

	public T deleteForId(long id) throws Exception;

	public T create(T entity) throws Exception;

	public T delete(T entity) throws Exception;

	public int delete(List<T> entities) throws Exception;

}
