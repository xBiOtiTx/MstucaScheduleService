package schedule.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lesson_titles")
public class LessonTitle {
	public static final String COLUMN_ID = "lesson_title_id";
	public static final String COLUMN_TITLE = "title";

	@Id
	@GeneratedValue
	@Column(name = COLUMN_ID, nullable = false)
	private Long id;

	@Column(name = COLUMN_TITLE)
	private String title;

	protected LessonTitle() {
		super();
	}

	public LessonTitle(String title) {
		super();
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		LessonTitle other = (LessonTitle) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LessonTitle [id=" + id + ", title=" + title + "]";
	}
	
	
}
