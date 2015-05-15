package schedule;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import schedule.entities.RawLesson;

public class ScheduleParser {
	private static final String LECTURE = "Лекция";
	private static final String PRACTICE = "Пр.Зан.";
	private static final String LAB = "Лаб.раб.";
	private static final String SEMINAR = "Семинар";

	private static final String TOKEN_ONLY = "только ";
	private static final String TOKEN_FROM = "с ";
	private static final String TOKEN_TO = "по ";
	private static final String TOKEN_EXCEPT = "кроме ";

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM");
	private static Pattern DATE_PATTERN = Pattern.compile("..\\...");

	private List<Date> readDates(String dates) throws ParseException {
		List<Date> result = new ArrayList<Date>();
		Matcher matcher = DATE_PATTERN.matcher(dates);
		while (matcher.find()) {
			String date = matcher.group(0);
			result.add(DATE_FORMAT.parse(date.substring(0, 5)));
		}
		return result;
	}

	private List<Date> readOnlyDates(String dateString) throws ParseException {
		int k = dateString.indexOf(TOKEN_ONLY);
		if (k != -1) {
			String dates = dateString.substring(k + 7, dateString.length());
			return readDates(dates);
		} else {
			return null;
		}
	}

	private List<Date> readFromToDates(String dateString) throws ParseException {
		List<Date> result = new ArrayList<Date>();

		int k;
		k = dateString.indexOf(TOKEN_FROM);
		if (k != -1) {
			Date from = DATE_FORMAT.parse(dateString.substring(k + 2, k + 2 + 5));
			result.add(from);
		} else {
			return null;
		}

		k = dateString.indexOf(TOKEN_TO);
		if (k != -1) {
			Date to = DATE_FORMAT.parse(dateString.substring(k + 3, k + 3 + 5));
			result.add(to);
		} else {
			return null;
		}

		return result;
	}

	private List<Date> readExceptDates(String dateString) throws ParseException {
		int k = dateString.indexOf(TOKEN_EXCEPT);
		if (k != -1) {
			String dates = dateString.substring(k + 6, dateString.length());
			return readDates(dates);
		} else {
			return null;
		}
	}

	private List<Date> generateDatesFromInterval(Date begin, Date end, String weekType) {
		List<Date> result = new ArrayList<Date>();

		int add = weekType.isEmpty() ? 7 : 14;

		Calendar calendarBegin = Calendar.getInstance();
		calendarBegin.setTime(begin);

		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(end);
		calendarEnd.add(Calendar.DATE, 1);

		while (calendarBegin.before(calendarEnd)) {
			result.add(calendarBegin.getTime());
			calendarBegin.add(Calendar.DATE, add);
		}
		return result;
	}

	private List<Date> generateDates(List<Date> interval, List<Date> except, String weekType) {
		if (interval != null && interval.size() == 2) {
			List<Date> dates = generateDatesFromInterval(interval.get(0), interval.get(1), weekType);
			if (except != null) {
				dates.removeAll(except);
			}
			return dates;
		}
		return null;
	}

	private List<RawLesson> generateLessons(int number, String lessonTitle, String type, String teacherName, String room, int subgroup, List<Date> datesOnly, List<Date> interval,
			List<Date> except, String weekType) {
		List<RawLesson> result = new ArrayList<RawLesson>();
		if (datesOnly != null) {
			for (Date date : datesOnly) {
				RawLesson lesson = new RawLesson(lessonTitle, number, date, teacherName, type, subgroup, room);
				result.add(lesson);
			}
		} else {
			List<Date> dates = generateDates(interval, except, weekType);
			for (Date date : dates) {
				RawLesson lesson = new RawLesson(lessonTitle, number, date, teacherName, type, subgroup, room);
				result.add(lesson);
			}
		}
		return result;
	}

	public List<RawLesson> parse(String pathToFile) throws Exception {
		List<RawLesson> result = new ArrayList<RawLesson>();

		InputStream in = new FileInputStream(pathToFile);
		HSSFWorkbook wb = new HSSFWorkbook(in);

		int lastNumber = 0;
		String weekType = "";
		String lessonTitle = "";
		String lessonType = "";
		String teacherName = "";
		String room = "";
		int subgroup = 0;
		List<Date> itnterval = null;
		List<Date> only = null;
		List<Date> except = null;

		for (int s = 0; s < wb.getNumberOfSheets(); s++) {
			if (s == 1) {
				continue;
			}
			// Определяем номер подгруппы
			if (s > 1) {
				subgroup = s - 1;
			}
			Sheet sheet = wb.getSheetAt(s);
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				// Определяем номер пары
				Cell numberCell = sheet.getRow(i).getCell(0);
				if (!numberCell.toString().isEmpty()) {
					lastNumber = (int) numberCell.getNumericCellValue();
				}

				String testString = sheet.getRow(i).getCell(2).getStringCellValue();
				if (testString.equals(LECTURE) || testString.equals(PRACTICE) || testString.equals(LAB) || testString.equals(SEMINAR)) {
					// Определяем чётность недели(В/Н)
					Cell weekTypeCell = sheet.getRow(i - 1).getCell(1);
					weekType = weekTypeCell.toString();

					// Считываем название предмета
					Cell titleCell = sheet.getRow(i - 1).getCell(2);
					lessonTitle = titleCell.toString();

					// Считываем тип занятия
					Cell typeCell = sheet.getRow(i).getCell(2);
					lessonType = typeCell.toString();

					// Считываем преподавателя
					Cell teacherCell = sheet.getRow(i).getCell(4);
					teacherName = teacherCell.toString();

					// Считываем аудиторию
					Cell roomCell = sheet.getRow(i).getCell(6);
					room = roomCell.toString();

					// Считываем даты
					Cell dateCell = sheet.getRow(i + 1).getCell(2);
					String dateString = dateCell.toString();
					itnterval = readFromToDates(dateString); // интервал "с хх.хх по хх.хх"
					only = readOnlyDates(dateString); // даты "только хх.хх;хх.хх..."
					except = readExceptDates(dateString); // даты "кроме хх.хх;хх.хх..."

					List<RawLesson> lessons = generateLessons(lastNumber, lessonTitle, lessonType, teacherName, room, subgroup, only, itnterval, except, weekType);
					result.addAll(lessons);
				}
			}
		}
		wb.close();
		return result;
	}

}
