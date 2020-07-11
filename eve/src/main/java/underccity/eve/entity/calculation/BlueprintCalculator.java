package underccity.eve.entity.calculation;

import java.util.HashMap;
import java.util.List;

import underccity.eve.entity.Blueprint;
import underccity.eve.entity.BlueprintCalculationResult;
import underccity.eve.entity.Components;

public class BlueprintCalculator {

	public static BlueprintCalculationResult calculate(Blueprint blueprint, List<String> hiddenComponents,
			HashMap<String, Long> resultMap, HashMap<String, Long> surplusMap, Long parentNeedCount, String parentId) {

		int needParentRun = 1;
		if (parentNeedCount > blueprint.getCountResultItem()) {
			needParentRun = (int) Math.ceil((double) parentNeedCount / (double) blueprint.getCountResultItem());
		}
		List<Components> components = blueprint.getComponents();
		for (Components component : components) {

			String frontComponentString = parentId + '-' + component.getId().getBlueprintId() + "-"
					+ component.getId().getItemId();// Наименование компонента с фронтовой части
			String componentName = component.getItem().getName();
			final Long componentNeedCount = component.getCount() * needParentRun;// Количество производимых единиц
																					// компонента с учетом количества
																					// производимых единиц родительского
																					// компонента

			if (hiddenComponents != null && hiddenComponents.contains(frontComponentString)) {// Если компонент нужно
																								// производить
				if (surplusMap.containsKey(componentName)) {// Проверяем наличие компонента в остатках
					if (componentNeedCount <= surplusMap.get(componentName)) {
						if (surplusMap.get(componentName) - componentNeedCount != 0) {
							surplusMap.compute(componentName, (a, b) -> b - componentNeedCount);
						}
					} else {// Если компонента в остатках недостаточно, то получаем нужное количество за
							// вычетом остатков и вызываем рекурсию, а в остатках обнуляем компонент
						Long componentSurplus = componentNeedCount - surplusMap.get(componentName);
						Long oneRunCount = component.getItem().getBlueprint().getCountResultItem();
						int needRun = 1;
						if (componentSurplus > oneRunCount) {
							needRun = (int) Math.ceil((double) componentSurplus / (double) oneRunCount);
						}
						if (needRun == 1) {
							BlueprintCalculator.calculate(component.getItem().getBlueprint(), hiddenComponents,
									resultMap, surplusMap, componentSurplus, frontComponentString);
						} else {
							BlueprintCalculator.calculate(component.getItem().getBlueprint(), hiddenComponents,
									resultMap, surplusMap, needRun * oneRunCount, frontComponentString);
						}

						final Long surplusAfterRuns = needRun * oneRunCount - componentSurplus;
						if (surplusAfterRuns != 0) {// Если после прогонов есть что то в остатках, то из мапы остатков и
													// мапы результата нужно вычесть их
							if (resultMap.containsKey(componentName)) {
								if (surplusAfterRuns - resultMap.get(componentName) > 0) {
									surplusMap.put(componentName, surplusAfterRuns - resultMap.get(componentName));
									resultMap.remove(componentName);
								} else {
									resultMap.compute(componentName, (a, b) -> b - surplusAfterRuns);
								}
							} else {
								if (surplusAfterRuns != 0) {
									surplusMap.put(componentName, surplusAfterRuns);
								}
							}
						}

					}
				} else {// Если компонента нет в остатках, то просто вызываем рекурсию
					Long oneRunCount = component.getItem().getBlueprint().getCountResultItem();
					int needRun = 1;
					if (componentNeedCount > oneRunCount) {
						needRun = (int) Math.ceil((double) componentNeedCount / (double) oneRunCount);
					}
					if (needRun == 1) {
						BlueprintCalculator.calculate(component.getItem().getBlueprint(), hiddenComponents, resultMap,
								surplusMap, componentNeedCount, frontComponentString);
					} else {
						BlueprintCalculator.calculate(component.getItem().getBlueprint(), hiddenComponents, resultMap,
								surplusMap, needRun * oneRunCount, frontComponentString);
					}

					final Long surplusAfterRuns = needRun * oneRunCount - componentNeedCount;
					if (surplusAfterRuns != 0) {// Если после прогонов есть что то в остатках, то из мапы остатков и
						// мапы результата нужно вычесть их
						if (resultMap.containsKey(componentName)) {
							if (surplusAfterRuns - resultMap.get(componentName) > 0) {
								surplusMap.put(componentName, surplusAfterRuns - resultMap.get(componentName));
								resultMap.remove(componentName);
							} else {
								resultMap.compute(componentName, (a, b) -> b - surplusAfterRuns);
							}
						} else {
							if (surplusAfterRuns != 0) {
								surplusMap.put(componentName, surplusAfterRuns);
							}
						}
					}

				}

			} else {// Заходим сюда когда нужно закупать компонент, а не производить

				if (surplusMap.containsKey(componentName)) {// Проверяем наличие компонента в остатках
					if (componentNeedCount <= surplusMap.get(componentName)) {
						surplusMap.compute(componentName, (a, b) -> b - componentNeedCount);
					} else {// При недостаточном количестве в остатках добавляем в закуп нужное за вычетов
							// остатков и обнуляем данный компонент в остатках
						Long componentSurplus = componentNeedCount - surplusMap.get(componentName);
						surplusMap.remove(componentName);
						resultMap.merge(componentName, componentSurplus, (a, b) -> a + b);
					}
				} else {// Если в остатках пусто, то просто добавляем в закуп
					resultMap.merge(componentName, componentNeedCount, (a, b) -> a + b);
				}
			}
		}
		return new BlueprintCalculationResult(resultMap, surplusMap);
	}

	public static BlueprintCalculationResult calculate(Blueprint blueprint, List<String> hiddenComponents, Long blueprintCount) {

		HashMap<String, Long> resultMap = new HashMap<String, Long>();
		HashMap<String, Long> surplusMap = new HashMap<String, Long>();
		if(blueprintCount == null || blueprintCount == 0) {
			blueprintCount = 1L;
		}
		return BlueprintCalculator.calculate(blueprint, hiddenComponents, resultMap, surplusMap,
				blueprintCount, "component" + blueprint.getId());
	}
}
