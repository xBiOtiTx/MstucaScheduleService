package schedule.entities;

public enum ELessonType {
	LECTURE, // ������
	PRACTICAL, // �� ���
	LAB, // ��� ���
	SEMINAR, // �������
	EMPTY; // �����

	public static final int STATUS_LECTURE = 1;
	public static final int STATUS_PRACTICAL = 2;
	public static final int STATUS_LAB = 3;
	public static final int STATUS_SEMINAR = 4;
	public static final int STATUS_EMPTY = 0;

	public static final String STRING_LECTURE = "������";
	public static final String STRING_PRACTICAL = "��.���.";
	public static final String STRING_LAB = "���.���.";
	public static final String STRING_SEMINAR = "�������";
	public static final String STRING_EMPTY = "�������";

	public static int toStatus(ELessonType lessonType) {
		switch (lessonType) {
		case LECTURE:
			return STATUS_LECTURE;

		case PRACTICAL:
			return STATUS_PRACTICAL;

		case LAB:
			return STATUS_LAB;

		case SEMINAR:
			return STATUS_SEMINAR;

		default:
			throw new IllegalArgumentException("Invalid lessonType");
		}
	}

	public static ELessonType fromStatus(int status) {
		switch (status) {
		case STATUS_LECTURE:
			return LECTURE;

		case STATUS_PRACTICAL:
			return PRACTICAL;

		case STATUS_LAB:
			return LAB;

		case STATUS_SEMINAR:
			return SEMINAR;

		default:
			throw new IllegalArgumentException("Invalid status");
		}
	}

	public static ELessonType fromString(String lessonType) {
		switch (lessonType) {
		case STRING_LECTURE:
			return LECTURE;

		case STRING_PRACTICAL:
			return PRACTICAL;

		case STRING_LAB:
			return LAB;

		case STRING_SEMINAR:
			return SEMINAR;

		default:
			throw new IllegalArgumentException("Invalid status");
		}
	}

	public static String getResTitle(ELessonType type) {
		switch (type) {
		case LECTURE:
			return "������";

		case PRACTICAL:
			return "��.���.";

		case LAB:
			return "���.���.";

		case SEMINAR:
			return "�������";

		case EMPTY:
			return "�����";

		default:
			throw new IllegalArgumentException("Invalid type");
		}
	}
}
