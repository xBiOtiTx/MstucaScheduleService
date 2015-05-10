package schedule.entities;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "lesson")
public class Lesson {
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE_ID = "title_id";
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TEACHER_ID = "teacher_id";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_SUBGROUP = "subgroup";
	public static final String COLUMN_ROOM_ID = "room_id";

	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	private Integer id;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = COLUMN_TITLE_ID)
	private LessonTitle lessonName;

	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName = COLUMN_NUMBER)
	private Integer number;

	@DatabaseField(dataType = DataType.DATE, columnName = COLUMN_DATE)
	private Date date;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = COLUMN_TEACHER_ID)
	private Teacher teacher;

	@DatabaseField(dataType = DataType.ENUM_INTEGER, columnName = COLUMN_TYPE)
	private ELessonType lessonType;

	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName = COLUMN_SUBGROUP)
	private Integer subgroup;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = COLUMN_ROOM_ID)
	private Room room;

	public Lesson() {
		super();
	}

	public Lesson(LessonTitle lessonName, Integer number, Date date, Teacher teacher, ELessonType lessonType, Integer subgroup, Room room) {
		super();
		this.lessonName = lessonName;
		this.number = number;
		this.date = date;
		this.teacher = teacher;
		this.lessonType = lessonType;
		this.subgroup = subgroup;
		this.room = room;
	}

	public Lesson(Integer id, LessonTitle lessonName, Integer number, Date date, Teacher teacher, ELessonType lessonType, Integer subgroup, Room room) {
		super();
		this.id = id;
		this.lessonName = lessonName;
		this.number = number;
		this.date = date;
		this.teacher = teacher;
		this.lessonType = lessonType;
		this.subgroup = subgroup;
		this.room = room;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LessonTitle getLessonName() {
		return lessonName;
	}

	public void setLessonName(LessonTitle lessonName) {
		this.lessonName = lessonName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public ELessonType getLessonType() {
		return lessonType;
	}

	public void setLessonType(ELessonType lessonType) {
		this.lessonType = lessonType;
	}

	public Integer getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(Integer subgroup) {
		this.subgroup = subgroup;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", lessonName=" + lessonName + ", number=" + number + ", date=" + date + ", teacher=" + teacher + ", lessonType="
				+ lessonType + ", subgroup=" + subgroup + ", room=" + room + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((lessonName == null) ? 0 : lessonName.hashCode());
		result = prime * result + ((lessonType == null) ? 0 : lessonType.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((subgroup == null) ? 0 : subgroup.hashCode());
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
		Lesson other = (Lesson) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (lessonName == null) {
			if (other.lessonName != null)
				return false;
		} else if (!lessonName.equals(other.lessonName))
			return false;
		if (lessonType != other.lessonType)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (subgroup == null) {
			if (other.subgroup != null)
				return false;
		} else if (!subgroup.equals(other.subgroup))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}

}
