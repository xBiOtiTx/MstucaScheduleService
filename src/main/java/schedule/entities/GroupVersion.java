package schedule.entities;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "groupVersion")
public class GroupVersion {
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_GROUP_ID = "group_id";
	public static final String COLUMN_DATE = "date";

	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	private Integer id;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = COLUMN_GROUP_ID)
	private Group group;

	@DatabaseField(dataType = DataType.DATE, columnName = COLUMN_DATE)
	private Date date;

	public GroupVersion() {
		super();
	}

	public GroupVersion(Group group, Date date) {
		super();
		this.group = group;
		this.date = date;
	}

	public GroupVersion(Integer id, Group group, Date date) {
		super();
		this.id = id;
		this.group = group;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupVersion other = (GroupVersion) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroupVersion [id=" + id + ", group=" + group + ", date=" + date + "]";
	}
}
