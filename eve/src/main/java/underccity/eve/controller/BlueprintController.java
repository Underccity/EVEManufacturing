package underccity.eve.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import underccity.eve.entity.Blueprint;
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
	public String listBluePrint(Model theModel) {
		
		List<Blueprint> thebluePrints = bluePrintService.findAll();
		
		theModel.addAttribute("blueprints", thebluePrints);
		return "blueprints/list-blueprints";
	}
	
	@GetMapping("/{blueprintId}")
	public String getById(@PathVariable(value = "blueprintId") Long blueprintId, Model theModel) {
		
		Blueprint blueprint = bluePrintService.findById(blueprintId);
		
		theModel.addAttribute("blueprint", blueprint);
		return "blueprints/blueprint";
	}
	
	@PostMapping("/upsert")
	public boolean upsertBluePrint(@Valid @RequestBody Blueprint blueprint) {
		
		return bluePrintService.upsert(blueprint);
	}
	
	@GetMapping("/delete/{deleteId}")
	public void deleteBluePrint(@Valid @PathVariable Long deleteId) {
		
		bluePrintService.deleteById(deleteId);
	}
	
	@GetMapping("/showEditForm")
	public String showEditForm(@RequestParam("blueprintId") Long blueprintId,
								Model theModel) {
		
		Blueprint blueprint = bluePrintService.findById(blueprintId);
		
		theModel.addAttribute("blueprint", blueprint);
		return "blueprints/blueprintEditForm";
	}
}
