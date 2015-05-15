package schedule.utils;

public class TimeWatcher {
	// TODO stack

	private static String title;
	private static long begin;
	private static long end;

	public static void start() {
		start("");
	}

	public static void start(String title) {
		TimeWatcher.title = title;
		begin = System.nanoTime();
	}

	public static void stop() {
		end = System.nanoTime();
		System.out.println(title + "Времени затрачено: " + (end - begin) / 1000000000.0f + " секунд.");
	}
}
