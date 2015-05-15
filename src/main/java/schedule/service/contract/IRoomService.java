package schedule.service.contract;

import schedule.entities.Room;

public interface IRoomService extends IService<Room> {

	public Room getForTitle(String title) throws Exception;

	public Room createIfNotExist(Room entity) throws Exception;

}
