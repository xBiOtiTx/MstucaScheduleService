package schedule.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lessons")
public class Lesson {
	public static final String COLUMN_ID = "lesson_id";
	public static final String COLUMN_LESSON_TITLE_ID = LessonTitle.COLUMN_ID;
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TEACHER_ID = Teacher.COLUMN_ID;
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_SUBGROUP = "subgroup";
	public static final String COLUMN_ROOM_ID = Room.COLUMN_ID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_ID, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = LessonTitle.COLUMN_ID, referencedColumnName = COLUMN_LESSON_TITLE_ID)
	private LessonTitle lessonTitle;

	@Column(name = COLUMN_NUMBER)
	private Integer number;

	@Column(name = COLUMN_DATE)
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = Teacher.COLUMN_ID, referencedColumnName = COLUMN_TEACHER_ID)
	private Teacher teacher;

	@Column(name = COLUMN_TYPE)
	@Enumerated(EnumType.ORDINAL)
	private ELessonType lessonType;

	@Column(name = COLUMN_SUBGROUP)
	private Integer subgroup;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = Room.COLUMN_ID, referencedColumnName = COLUMN_ROOM_ID)
	private Room room;

	protected Lesson() {
		super();
	}

	public Lesson(LessonTitle lessonTitle, Integer number, Date date, Teacher teacher, ELessonType lessonType, Integer subgroup, Room room) {
		super();
		this.lessonTitle = lessonTitle;
		this.number = number;
		this.date = date;
		this.teacher = teacher;
		this.lessonType = lessonType;
		this.subgroup = subgroup;
		this.room = room;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LessonTitle getLessonTitle() {
		return lessonTitle;
	}

	public void setLessonTitle(LessonTitle lessonTitle) {
		this.lessonTitle = lessonTitle;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((lessonTitle == null) ? 0 : lessonTitle.hashCode());
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
		} else if (!date.equals(other.date)) {
			return false;
		}

		if (lessonTitle == null) {
			if (other.lessonTitle != null)
				return false;
		} else if (!lessonTitle.equals(other.lessonTitle)) {
			return false;
		}

		if (lessonType != other.lessonType)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number)) {
			return false;
		}

		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room)) {
			return false;
		}

		if (subgroup == null) {
			if (other.subgroup != null)
				return false;
		} else if (!subgroup.equals(other.subgroup)) {
			return false;
		}

		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", lessonTitle=" + lessonTitle + ", number=" + number + ", date=" + date + ", teacher=" + teacher + ", lessonType=" + lessonType
				+ ", subgroup=" + subgroup + ", room=" + room + "]";
	}

}
