package underccity.eve.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import underccity.eve.entity.Item;
import underccity.eve.exception.ExistReferencesItemException;
import underccity.eve.service.BlueprintService;
import underccity.eve.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	private ItemService itemService;

	public ItemController(ItemService itemService) {
		super();
		this.itemService = itemService;
	}
	
	@GetMapping("")
	public @ResponseBody List<Item> findByName(@RequestParam("name") String itemName) {
		
		return itemService.findByName(itemName);
	}

	@GetMapping("/list")
	public String listItem(Model theModel) {
		
		List<Item> theItems = itemService.findAll();
		
		theModel.addAttribute("items", theItems);
		return "items/list-items";
	}
//	
//	@GetMapping("/{itemId}")
//	public String getById(@PathVariable(value = "itemId") Long itemId, Model theModel) {
//		
//		Item item = itemService.findById(itemId);
//		
//		theModel.addAttribute("item", item);
//		return "items/item";
//	}
	
	@PostMapping("/upsert")
	public String upsertItem(@Valid Item item, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
            return "items/itemEditForm"; // to person.jsp page
        }
		itemService.upsert(item);
		return "redirect:/item/list"; 
	}
	
	@GetMapping("/showEditForm")
	public String showEditForm(@RequestParam(value="itemId", required = false) Long itemId,
								Model theModel) {
		Item item = null;
		if(itemId != null) {
			 item = itemService.findById(itemId);
		} else {
			item = new Item();
		}
		
		theModel.addAttribute("item", item);
		return "items/itemEditForm";
	}
	
	@GetMapping("/delete")
	public String deleteItem(@RequestParam("itemId") Long itemId, Model theModel) {
		
		try {
			itemService.deleteById(itemId);
		} catch(ExistReferencesItemException e) {
			theModel.addAttribute("item", e.getItem());
			theModel.addAttribute("deleteError", "Невозможно удалить компонент. Для данного компонента есть связь с другими компонентами");
			return "items/itemEditForm";
		}
		return "redirect:/item/list"; 
	}
	
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
