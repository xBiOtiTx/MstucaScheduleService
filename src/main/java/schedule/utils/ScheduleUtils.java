package schedule.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ScheduleUtils {
	public static void saveUrlToFile(String url, String fileName) throws IOException {
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
}
