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
@Table(name = "teacher_versions")
public class TeacherVersion {
	public static final String COLUMN_ID = "teacher_version_id";
	public static final String COLUMN_TEACHER_ID = Teacher.COLUMN_ID;
	public static final String COLUMN_DATE = "date";

	@Id
	@GeneratedValue
	@Column(name = COLUMN_ID, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = Teacher.COLUMN_ID, referencedColumnName = COLUMN_TEACHER_ID)
	private Teacher teacher;

	@Column(name = COLUMN_DATE)
	private Date date;

	protected TeacherVersion() {
		super();
	}

	public TeacherVersion(Teacher teacher, Date date) {
		super();
		this.teacher = teacher;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
