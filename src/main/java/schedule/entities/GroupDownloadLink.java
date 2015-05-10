package schedule.entities;

public class GroupDownloadLink {
	private String groupTitle;
	private String downloadLink;

	public GroupDownloadLink(String groupTitle, String downloadLink) {
		super();
		this.groupTitle = groupTitle;
		this.downloadLink = downloadLink;
	}

	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((downloadLink == null) ? 0 : downloadLink.hashCode());
		result = prime * result + ((groupTitle == null) ? 0 : groupTitle.hashCode());
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
		GroupDownloadLink other = (GroupDownloadLink) obj;
		if (downloadLink == null) {
			if (other.downloadLink != null)
				return false;
		} else if (!downloadLink.equals(other.downloadLink))
			return false;
		if (groupTitle == null) {
			if (other.groupTitle != null)
				return false;
		} else if (!groupTitle.equals(other.groupTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroupDownloadLink [groupTitle=" + groupTitle + ", downloadLink=" + downloadLink + "]";
	}

}
