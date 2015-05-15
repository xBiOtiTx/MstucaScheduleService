package schedule.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BaseEntity {
	public static final String COLUMN_ID = "_id";

	@Id
	@GeneratedValue
	@Column(name = COLUMN_ID, nullable = false)
	private Long id;

	protected BaseEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}