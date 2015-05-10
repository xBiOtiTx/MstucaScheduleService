package schedule.entities;

import java.util.Date;

public class RawLesson {
	private String lessonTitle;
	private int number;
	private Date date;
	private String teacherName;
	private String lessonType;
	private int subgroup;
	private String room;

	public RawLesson(String lessonTitle, int number, Date date, String teacherName, String lessonType, int subgroup, String room) {
		super();
		this.lessonTitle = lessonTitle;
		this.number = number;
		this.date = date;
		this.teacherName = teacherName;
		this.lessonType = lessonType;
		this.subgroup = subgroup;
		this.room = room;
	}

	public String getLessonTitle() {
		return lessonTitle;
	}

	public void setLessonTitle(String lessonTitle) {
		this.lessonTitle = lessonTitle;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getLessonType() {
		return lessonType;
	}

	public void setLessonType(String lessonType) {
		this.lessonType = lessonType;
	}

	public int getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(int subgroup) {
		this.subgroup = subgroup;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "RawLesson [lessonTitle=" + lessonTitle + ", number=" + number + ", date=" + date + ", teacherName=" + teacherName + ", lessonType="
				+ lessonType + ", subgroup=" + subgroup + ", room=" + room + "]";
	}

}
