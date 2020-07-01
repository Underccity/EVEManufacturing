package underccity.eve.entity;

import java.util.HashMap;

public class BlueprintCalculationResult {
	
	public BlueprintCalculationResult(HashMap<String, Integer> resultMap, HashMap<String, Integer> resultSurplus) {
		super();
		this.resultMap = resultMap;
		this.resultSurplus = resultSurplus;
	}
	private HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> resultSurplus = new HashMap<String, Integer>();
	
	public HashMap<String, Integer> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Integer> resultMap) {
		this.resultMap = resultMap;
	}
	public HashMap<String, Integer> getResultSurplus() {
		return resultSurplus;
	}
	public void setResultSurplus(HashMap<String, Integer> resultSurplus) {
		this.resultSurplus = resultSurplus;
	}
}
