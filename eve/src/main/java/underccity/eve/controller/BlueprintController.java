package underccity.eve.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import underccity.eve.entity.Blueprint;
import underccity.eve.entity.BlueprintCalculationRequest;
import underccity.eve.entity.BlueprintCalculationResult;
import underccity.eve.entity.Item;
import underccity.eve.entity.calculation.BlueprintCalculator;
import underccity.eve.service.BlueprintService;

@Controller
@RequestMapping("/blueprint")
public class BlueprintController {

	private BlueprintService bluePrintService;

	public BlueprintController(BlueprintService bluePrintService) {
		super();
		this.bluePrintService = bluePrintService;
	}

	@GetMapping("/list")
	public String listBluePrint(Model theModel, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Page<Blueprint> itemPage = bluePrintService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		theModel.addAttribute("blueprintPage", itemPage);

		int totalPages = itemPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			theModel.addAttribute("pageNumbers", pageNumbers);
		}
		return "blueprints/list-blueprints";
	}

	@GetMapping("")
	public @ResponseBody List<Blueprint> findByStartName(@RequestParam("name") String blueprintName) {

		return bluePrintService.findByStartName(blueprintName);
	}

	@GetMapping("/{blueprintId}")
	public String getById(@PathVariable(value = "blueprintId") Long blueprintId, Model theModel) {

		Blueprint blueprint = bluePrintService.findById(blueprintId);
		BlueprintCalculationResult bpcr = BlueprintCalculator.calculate(blueprint, null, 1L);
		theModel.addAttribute("calcResult", bpcr);
		theModel.addAttribute("blueprint", blueprint);
		return "blueprints/blueprint";
	}

	@PostMapping("/upsert")
	public String upsertBluePrint(@Valid Blueprint blueprint) {
		bluePrintService.upsert(blueprint);
		return "redirect:/blueprint/list";// bluePrintService.upsert(blueprint);
	}

	@PostMapping("/recalculate")
	public @ResponseBody BlueprintCalculationResult recalculateBlueprint(
			@RequestBody BlueprintCalculationRequest request) {
		Blueprint blueprint = bluePrintService.findById(request.getBlueprintId());
		BlueprintCalculationResult bpcr = BlueprintCalculator.calculate(blueprint, request.getHiddenComponents(),
				request.getBlueprintCount());
		return bpcr;
	}

	@GetMapping("/delete")
	public String deleteBluePrint(@RequestParam("blueprintId") Long blueprintId) {

		bluePrintService.deleteById(blueprintId);
		return "redirect:/blueprint/list";
	}

	@GetMapping("/showEditForm")
	public String showEditForm(@RequestParam(value = "blueprintId", required = false) Long blueprintId,
			Model theModel) {

		Blueprint blueprint = null;
		if (blueprintId != null) {
			blueprint = bluePrintService.findById(blueprintId);
		} else {
			blueprint = new Blueprint();
		}
		theModel.addAttribute("blueprint", blueprint);
		return "blueprints/blueprintEditForm";
	}
}
