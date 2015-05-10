package schedule.entities;

import com.j256.ormlite.field.DatabaseField;

public class Entity {
	public static final String COLUMN_ID = "_id";

	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static String getColumnId() {
		return COLUMN_ID;
	}
}
