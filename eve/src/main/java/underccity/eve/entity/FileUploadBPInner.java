package underccity.eve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileUploadBPInner {
	private FileUploadBPActivities activities;

	public FileUploadBPActivities getActivities() {
		return activities;
	}

	public void setActivities(FileUploadBPActivities activities) {
		this.activities = activities;
	}
}
