package schedule.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "lessonGroupLink")
public class LessonGroupLink {
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LESSON_ID = "lesson_id";
	public static final String COLUMN_GROUP_ID = "group_id";

	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	private Integer id;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = COLUMN_LESSON_ID)
	private Lesson lesson;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = COLUMN_GROUP_ID)
	private Group group;

	public LessonGroupLink() {
		super();
	}

	public LessonGroupLink(Lesson lesson, Group group) {
		super();
		this.lesson = lesson;
		this.group = group;
	}

	public LessonGroupLink(Integer id, Lesson lesson, Group group) {
		super();
		this.id = id;
		this.lesson = lesson;
		this.group = group;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "LessonGroupLink [id=" + id + ", lesson=" + lesson + ", group=" + group + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lesson == null) ? 0 : lesson.hashCode());
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
		LessonGroupLink other = (LessonGroupLink) obj;
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
		if (lesson == null) {
			if (other.lesson != null)
				return false;
		} else if (!lesson.equals(other.lesson))
			return false;
		return true;
	}

}
