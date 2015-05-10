package schedule.entities;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "teacherVersion")
public class TeacherVersion {
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TEACHER_ID = "teacher_id";
	public static final String COLUMN_DATE = "date";

	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	private Integer id;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = COLUMN_TEACHER_ID)
	private Teacher teacher;

	@DatabaseField(dataType = DataType.DATE, columnName = COLUMN_DATE)
	private Date date;

	public TeacherVersion() {
		super();
	}

	public TeacherVersion(Teacher teacher, Date date) {
		super();
		this.teacher = teacher;
		this.date = date;
	}

	public TeacherVersion(Integer id, Teacher teacher, Date date) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
		TeacherVersion other = (TeacherVersion) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TeacherVersion [id=" + id + ", teacher=" + teacher + ", date=" + date + "]";
	}

}
