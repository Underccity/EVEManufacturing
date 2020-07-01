package underccity.eve.entity;

import java.util.List;

public class BlueprintCalculationRequest {
	private Long blueprintId;
	private List<String> hiddenComponents;
	
	public BlueprintCalculationRequest(Long blueprintId, List<String> hiddenComponents) {
		super();
		this.blueprintId = blueprintId;
		this.hiddenComponents = hiddenComponents;
	}
	public List<String> getHiddenComponents() {
		return hiddenComponents;
	}
	public void setHiddenComponents(List<String> hiddenComponents) {
		this.hiddenComponents = hiddenComponents;
	}
	public Long getBlueprintId() {
		return blueprintId;
	}
	public void setBlueprintId(Long blueprintId) {
		this.blueprintId = blueprintId;
	}
}
