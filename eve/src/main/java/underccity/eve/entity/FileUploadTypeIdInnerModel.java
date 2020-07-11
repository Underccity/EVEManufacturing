package underccity.eve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileUploadTypeIdInnerModel {
	int groupID;
	FileUploadNameModel name;
	boolean published;
	FileUploadNameModel description;
	
	public FileUploadNameModel getDescription() {
		return description;
	}
	public void setDescription(FileUploadNameModel description) {
		this.description = description;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public FileUploadNameModel getName() {
		return name;
	}
	public void setName(FileUploadNameModel name) {
		this.name = name;
	}
	
}
