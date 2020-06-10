package underccity.eve.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import underccity.eve.entity.Blueprint;
import underccity.eve.service.BlueprintService;

@Controller
public class HomeController {

	private BlueprintService bluePrintService;

	public HomeController(BlueprintService bluePrintService) {
		super();
		this.bluePrintService = bluePrintService;
	}

	@GetMapping("/list")
	public String listBluePrint(Model theModel) {
		
		List<Blueprint> thebluePrints = bluePrintService.findAll();
		
		
//		theModel.addAttribute("bluePrints", thebluePrints);
		
		return "greeting";
	}
}
