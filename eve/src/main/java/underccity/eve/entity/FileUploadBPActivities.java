package underccity.eve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileUploadBPActivities {

	private BPMamufacturing manufacturing;
	private BPMamufacturing reaction;

	public BPMamufacturing getManufacturing() {
		return manufacturing;
	}

	public void setManufacturing(BPMamufacturing manufacturing) {
		this.manufacturing = manufacturing;
	}

	public BPMamufacturing getReaction() {
		return reaction;
	}

	public void setReaction(BPMamufacturing reaction) {
		this.reaction = reaction;
	}
	
}
