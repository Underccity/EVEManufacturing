package underccity.eve.entity;

import java.util.HashMap;

public class BlueprintCalculationResult {
	
	public BlueprintCalculationResult(HashMap<String, Long> resultMap, HashMap<String, Long> resultSurplus) {
		super();
		this.resultMap = resultMap;
		this.resultSurplus = resultSurplus;
	}
	private HashMap<String, Long> resultMap = new HashMap<String, Long>();
	private HashMap<String, Long> resultSurplus = new HashMap<String, Long>();
	
	public HashMap<String, Long> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Long> resultMap) {
		this.resultMap = resultMap;
	}
	public HashMap<String, Long> getResultSurplus() {
		return resultSurplus;
	}
	public void setResultSurplus(HashMap<String, Long> resultSurplus) {
		this.resultSurplus = resultSurplus;
	}
}
