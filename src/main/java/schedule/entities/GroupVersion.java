package schedule.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "group_versions")
public class GroupVersion {
	public static final String COLUMN_ID = "group_version_id";
	public static final String COLUMN_GROUP_ID = "group_id";
	public static final String COLUMN_DATE = "date";

	@Id
	@GeneratedValue
	@Column(name = COLUMN_ID, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = Group.COLUMN_ID)
	private Group group;

	@Column(name = COLUMN_DATE)
	private Date date;

	protected GroupVersion() {
		super();
	}

	public GroupVersion(Group group, Date date) {
		super();
		this.group = group;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return true;
	}

	@Override
	public String toString() {
		return "GroupVersion [id=" + id + ", group=" + group + ", date=" + date + "]";
	}

}
