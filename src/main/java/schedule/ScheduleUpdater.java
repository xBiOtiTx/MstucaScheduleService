package schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.inject.Inject;

import schedule.entities.ELessonType;
import schedule.entities.Group;
import schedule.entities.GroupVersion;
import schedule.entities.Lesson;
import schedule.entities.LessonGroupLink;
import schedule.entities.LessonTitle;
import schedule.entities.RawLesson;
import schedule.entities.Room;
import schedule.entities.Teacher;
import schedule.entities.TeacherVersion;
import schedule.service.GroupService;
import schedule.service.GroupVersionService;
import schedule.service.LessonGroupLinkService;
import schedule.service.LessonService;
import schedule.service.LessonTitleService;
import schedule.service.RoomService;
import schedule.service.TeacherService;
import schedule.service.TeacherVersionService;
import schedule.service.contract.IGroupService;
import schedule.service.contract.IGroupVersionService;
import schedule.service.contract.ILessonGroupLinkService;
import schedule.service.contract.ILessonService;
import schedule.service.contract.ILessonTitleService;
import schedule.service.contract.IRoomService;
import schedule.service.contract.ITeacherService;
import schedule.service.contract.ITeacherVersionService;
import schedule.utils.TimeWatcher;

// TODO ScheduleUpdaterService
public class ScheduleUpdater {
	public void hi() {
		System.out.println("hi2");
	}

	@Inject
	private IGroupService groupService;

	@Inject
	private ITeacherService teacherService;

	@Inject
	private ILessonTitleService lessonTitleService;

	@Inject
	private IRoomService roomService;

	@Inject
	private ILessonService lessonService;

	@Inject
	private ILessonGroupLinkService lessonGroupLinkService;

	@Inject
	private IGroupVersionService groupVersionService;

	@Inject
	private ITeacherVersionService teacherVersionService;

	// Подготавливает "сырые" занятия для записи в БД - возвращает список пригодных для записи сущностей
	private List<Lesson> prepareLessons(List<RawLesson> rawLessons) throws Exception {
		final List<Lesson> lessons = new ArrayList<>();
		for (RawLesson rawLesson : rawLessons) {
			LessonTitle lessonTitle = lessonTitleService.createIfNotExist(new LessonTitle(rawLesson.getLessonTitle()));
			Integer lessonNumber = rawLesson.getNumber();
			Date lessonDate = rawLesson.getDate();
			Teacher teacher = teacherService.createIfNotExist(new Teacher(rawLesson.getTeacherName()));
			ELessonType lessonType = ELessonType.fromString(rawLesson.getLessonType());
			Integer subgroup = rawLesson.getSubgroup();
			Room lessonRoom = roomService.createIfNotExist(new Room(rawLesson.getRoom()));
			Lesson lesson = new Lesson(lessonTitle, lessonNumber, lessonDate, teacher, lessonType, subgroup, lessonRoom);
			lessons.add(lesson);
		}
		return lessons;
	}

	// Подготавливает список LessonGroupLink. Формирует его на основе группы(group) и занятий(lessons).
	// Группа и занятия уже должны быть записанны в БД(иметь правильные id).
	private List<LessonGroupLink> prepareLessonGroupLinks(Group group, List<Lesson> lessons) {
		List<LessonGroupLink> lessonGroupLinks = new ArrayList<LessonGroupLink>();
		for (Lesson lesson : lessons) {
			lessonGroupLinks.add(new LessonGroupLink(lesson, group));
		}
		return lessonGroupLinks;
	}

	// Возвращает множество(Set) преподавателей ведущие занятия lessons.
	private Set<Teacher> getTeachersFromLessons(List<Lesson> lessons) {
		Set<Teacher> teachers = new HashSet<>();
		for (Lesson lesson : lessons) {
			if (!lesson.getTeacher().getName().equals("*****, ***")) {
				teachers.add(lesson.getTeacher());
			}
		}
		return teachers;
	}

	// кастыльный ремув. удаляет из lessons lesson по equals.
	private boolean remove(List<Lesson> lessons, Lesson lesson) {
		// List<Lesson> removes = new ArrayList<Lesson>();
		// for (Lesson l : lessons) {
		// if(l.equals(lesson)) {
		// removes.add(l);
		// }
		// }
		// lessons.removeAll(removes);
		for (Lesson l : lessons) {
			if (l.equals(lesson)) {
				lessons.remove(l);
				return true;
			}
		}
		return false;
	}

	// Возвращает список занятий которые отличаются(удалены, изменены, добавленны).
	private List<Lesson> getChangedLessons(List<Lesson> newLessons, List<Lesson> oldLessons) {
		List<Lesson> changedLessons = new ArrayList<Lesson>(newLessons.size());
		changedLessons.addAll(newLessons);
		for (Lesson l : oldLessons) {
			if (!remove(changedLessons, l)) {
				changedLessons.add(l);
			}
			// if (!changedLessons.remove(l)) {
			// changedLessons.add(l);
			// System.out.println("changedLessons: add" + l);
			// } else {
			// System.out.println("changedLessons: remove" + l);
			// }
		}
		return changedLessons;
	}

	private List<Lesson> getLessons(List<LessonGroupLink> links) {
		List<Lesson> lessons = new ArrayList<Lesson>(links.size());
		for (LessonGroupLink link : links) {
			lessons.add(link.getLesson());
		}
		return lessons;
	}

	// TODO Добавить логирование
	// Обновление занятий по группе groupTitle занятиями rawLessons.
	// Обновление версии расписания по группе и версий расписаний по преподавателям.
	public void update(String groupTitle, List<RawLesson> rawLessons) throws Exception {
		// Получаем группу на основе её названия
		final Group group = groupService.createIfNotExist(new Group(groupTitle));

		TimeWatcher.start("Получаем старый список линков по группе и удаляем их из БД. ");
		// Получаем старый список линков по группе и удаляем их из БД
		final List<LessonGroupLink> oldLessonGroupLinks = lessonGroupLinkService.getForGroup(group);
		lessonGroupLinkService.delete(oldLessonGroupLinks);
		TimeWatcher.stop();

		TimeWatcher.start("Получаем старый список занятий(из списка линков по группе) и удаляем их из БД. ");
		// Получаем старый список занятий(из списка линков по группе) и удаляем их из БД
		final List<Lesson> oldLessons = getLessons(oldLessonGroupLinks);
		lessonService.delete(oldLessons);
		TimeWatcher.stop();

		TimeWatcher.start("Формируем список новых занятий и записываем их в БД. ");
		// Формируем список новых занятий и записываем их в БД
		final List<Lesson> newLessons = prepareLessons(rawLessons);
		lessonService.createAll(newLessons);
		TimeWatcher.stop();

		TimeWatcher.start("Формируем список линков и записываем их в БД. ");
		// Формируем список линков и записываем их в БД
		final List<LessonGroupLink> newLessonGroupLinks = prepareLessonGroupLinks(group, newLessons);
		lessonGroupLinkService.createAll(newLessonGroupLinks);
		TimeWatcher.stop();

		// Получаем список отличающихся занятий и обновляем версии группы и преподавателей если он не пуст
		final List<Lesson> changedLessons = getChangedLessons(newLessons, oldLessons);
		if (!changedLessons.isEmpty()) {
			// Ставим группе новую версию
			groupVersionService.update(new GroupVersion(group, new Date()));

			// Ставим преподавателям ведущим отличающиеся занятия новые версии
			final Set<Teacher> teachers = getTeachersFromLessons(changedLessons);
			for (Teacher teacher : teachers) {
				teacherVersionService.update(new TeacherVersion(teacher, new Date()));
			}

			// Так как список преподавателей мог изменится то проверяем целостность БД
			// (таблицу с преподавателями и таблицу с версиями расписаний по преподавателям)
			checkTeachers();
		}

		// System.out.println("oldLessons: " + oldLessons);
		// System.out.println("changedLessons: " + changedLessons);
		// System.out.println("newLessons: " + newLessons);
	}

	// TODO
	// Проверка целостности таблиц с преподавателями и с версиями расписаний по преподавателям.
	// Это необходимо делать всякий раз когда список преподавателей может измениться.
	private void checkTeachers() throws Exception {
	}

}
