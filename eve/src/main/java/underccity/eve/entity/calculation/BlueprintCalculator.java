package underccity.eve.entity.calculation;

import java.util.HashMap;
import java.util.List;

import underccity.eve.entity.Blueprint;
import underccity.eve.entity.BlueprintCalculationResult;
import underccity.eve.entity.Components;

public class BlueprintCalculator {

	public static BlueprintCalculationResult calculate(Blueprint blueprint, List<String> hiddenComponents,
			HashMap<String, Integer> resultMap, HashMap<String, Integer> surplusMap, int parentNeedCount, String parentId) {

		int coeff = 1;
		int blueprintCount = blueprint.getCountResultItem();
		if ((double) parentNeedCount / (double) blueprintCount > 1) {
			coeff = (int) Math.ceil((double) parentNeedCount / (double) blueprintCount);
		}
		List<Components> components = blueprint.getComponents();
		for (Components component : components) {
			String frontComponentString = parentId + '-' + component.getId().getBlueprintId() + "-"
					+ component.getId().getItemId();
			if (hiddenComponents != null && hiddenComponents.contains(frontComponentString)) {
				BlueprintCalculator.calculate(component.getItem().getBlueprint(), hiddenComponents, resultMap,
						surplusMap, component.getItem().getBlueprint().getCountResultItem(), frontComponentString);
				BlueprintCalculator.calculateSurpus(surplusMap, component, component.getCount());

			} else {
				String componentName = component.getItem().getName();
				int needCount = component.getCount() * coeff;
				if (resultMap.get(componentName) != null) {
					resultMap.put(componentName, needCount + resultMap.get(componentName));
				} else {
					resultMap.put(componentName, needCount);
				}
//				if (component.getItem().getBlueprint() != null) {// Если компонент не имеет чертежа, т.е. добывается не
//																	// производством то считать остатки не нужно
//					calculateSurpus(surplusMap, component, needCount);
//				}
			}
		}
		return new BlueprintCalculationResult(resultMap, surplusMap);
	}

	private static void calculateSurpus(HashMap<String, Integer> surplusMap, Components component, int needCount) {
		int oneRunCount = component.getItem().getBlueprint().getCountResultItem();
		int surplus = 0;
		if ((double) needCount / (double) oneRunCount < 1) {
			surplus = oneRunCount - needCount;
		} else {
			int needRun = (int) Math.ceil((double) needCount / (double) oneRunCount);
			surplus = oneRunCount * needRun - needCount;
		}
		if (surplus != 0) {
			surplusMap.put(component.getItem().getName(), surplus);
		}
	}

	public static BlueprintCalculationResult calculate(Blueprint blueprint, List<String> hiddenComponents) {

		HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
		HashMap<String, Integer> surplusMap = new HashMap<String, Integer>();
		return BlueprintCalculator.calculate(blueprint, hiddenComponents, resultMap, surplusMap,
				blueprint.getCountResultItem(), "component" + blueprint.getId());
	}
}
