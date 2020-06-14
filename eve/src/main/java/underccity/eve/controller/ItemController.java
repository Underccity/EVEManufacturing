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
import underccity.eve.entity.Item;
import underccity.eve.service.BlueprintService;
import underccity.eve.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	private ItemService itemService;

	public ItemController(ItemService itemService) {
		super();
		this.itemService = itemService;
	}
	
	@GetMapping("")
	public List<Item> findByName(@RequestParam("name") String itemName) {
		
		return itemService.findByName(itemName);
	}

//	@GetMapping("/list")
//	public String listItem(Model theModel) {
//		
//		List<Item> theItems = itemService.findAll();
//		
//		theModel.addAttribute("items", theItems);
//		return "items/list-items";
//	}
//	
//	@GetMapping("/{itemId}")
//	public String getById(@PathVariable(value = "itemId") Long itemId, Model theModel) {
//		
//		Item item = itemService.findById(itemId);
//		
//		theModel.addAttribute("item", item);
//		return "items/item";
//	}
	
	
	
//	@PostMapping("/upsert")
//	public boolean upsertBluePrint(@Valid @RequestBody Blueprint blueprint) {
//		
//		return bluePrintService.upsert(blueprint);
//	}
//	
//	@GetMapping("/delete/{deleteId}")
//	public void deleteBluePrint(@Valid @PathVariable Long deleteId) {
//		
//		bluePrintService.deleteById(deleteId);
//	}
	
//	@GetMapping("/showEditForm")
//	public String showEditForm(@RequestParam("employeeId") Long blueprintId,
//								Model theModel) {
//		
//		Blueprint blueprint = bluePrintService.findById(blueprintId);
//		
//		theModel.addAttribute("blueprint", blueprint);
//		return "blueprints/blueprintEditForm";
//	}
}
