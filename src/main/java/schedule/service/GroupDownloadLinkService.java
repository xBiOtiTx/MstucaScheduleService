package schedule.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import schedule.entities.GroupDownloadLink;

@Stateless
public class GroupDownloadLinkService {

	public List<GroupDownloadLink> getAll() throws Exception {
		List<GroupDownloadLink> groupDownloadLinks = new ArrayList<GroupDownloadLink>();

		String server = "http://www.mstuca.ru";
		String page = "/students/schedule/?content=.xls&timestamp_datesel=&timestamp_days=&timestamp_from=&timestamp_to=&doctype=acf489b4&%3FTAGS=&user_user_input=&user_user_input=&FILE_SIZE_from=&FILE_SIZE_to=&FILE_SIZE_multiply=b&WF_LOCK_STATUS=&filter=%CD%E0%E9%F2%E8&clear_filter=";

		while (true) {
			Document doc = Jsoup.connect(server + page).get();
			// System.out.println(doc);

			Elements links = doc.getElementsByClass("element-title");
			for (Element link : links) {
				if (link.attr("data-bx-title").contains(".xlsx")) {
					continue;
				}

				String groupTitle = link.attr("data-bx-title").replace(".xls", "");
				String downloadLink = server + link.attr("data-bx-download");
				groupDownloadLinks.add(new GroupDownloadLink(groupTitle, downloadLink));
				// System.out.println(groupTitle + " - " + downloadLink);
			}

			Elements linksNext = doc.getElementsByClass("modern-page-next");
			if (!linksNext.isEmpty()) {
				page = linksNext.get(0).attr("href");
			} else {
				break;
			}
		}

		return groupDownloadLinks;
	}
}
