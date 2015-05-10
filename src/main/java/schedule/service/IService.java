package schedule.service;

import java.util.List;

public interface IService<T> {
	public List<T> getAll() throws Exception;

	public T getForId(int id) throws Exception;
}
