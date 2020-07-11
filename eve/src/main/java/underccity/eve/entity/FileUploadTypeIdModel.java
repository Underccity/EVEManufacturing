package underccity.eve.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class FileUploadTypeIdModel {

    private Map<String, FileUploadTypeIdInnerModel> unknownSubCategories = new HashMap<>();

    @JsonAnyGetter
	public Map<String, FileUploadTypeIdInnerModel> getUnknownSubCategories() {
		return unknownSubCategories;
	}

	@JsonAnySetter
	public void setUnknownSubCategories(String key, FileUploadTypeIdInnerModel value) {
        unknownSubCategories.put(key, value);
	}
}
