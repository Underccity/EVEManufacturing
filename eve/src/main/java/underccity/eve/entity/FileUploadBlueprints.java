package underccity.eve.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class FileUploadBlueprints {

	 private Map<String, FileUploadBPInner> unknownSubCategories = new HashMap<>();
	 
	    @JsonAnyGetter
		public Map<String, FileUploadBPInner> getUnknownSubCategories() {
			return unknownSubCategories;
		}

		@JsonAnySetter
		public void setUnknownSubCategories(String key, FileUploadBPInner value) {
	        unknownSubCategories.put(key, value);
		}
	 
	 
}
