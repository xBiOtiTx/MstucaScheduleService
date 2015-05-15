package schedule.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Embeddable
@Entity
@Table(name = "lesson_group_links")
public class LessonGroupLink {
	public static final String COLUMN_ID = "lesson_group_link_id";
	public static final String COLUMN_LESSON_ID = "lesson_id";
	public static final String COLUMN_GROUP_ID = "group_id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_ID, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = COLUMN_LESSON_ID)
	private Lesson lesson;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = COLUMN_GROUP_ID)
	private Group group;

	protected LessonGroupLink() {
		super();
	}

	public LessonGroupLink(Lesson lesson, Group group) {
		super();
		this.lesson = lesson;
		this.group = group;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
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
		if (lesson == null) {
			if (other.lesson != null)
				return false;
		} else if (!lesson.equals(other.lesson))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LessonGroupLink [id=" + id + ", lesson=" + lesson + ", group=" + group + "]";
	}

}
