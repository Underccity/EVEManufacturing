package underccity.eve.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public @ResponseBody List<Item> findByStartName(@RequestParam("name") String itemName) {

		return itemService.findByStartName(itemName);
	}

	@GetMapping(value = "/list")
	public String listItem(Model theModel, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		 Page<Item> itemPage = itemService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
		 
	        theModel.addAttribute("itemPage", itemPage);
	 
	        int totalPages = itemPage.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            theModel.addAttribute("pageNumbers", pageNumbers);
	        }
		
	//	List<Item> theItems = itemService.findAll();

//		theModel.addAttribute("items", theItems);
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
	public String upsertItem(@Valid Item item, Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "items/itemEditForm"; // to person.jsp page
		}
		Item anotherItem = itemService.findByEqualName(item.getName());
		if (anotherItem != null && item.getId() == null) {
			bindingResult.addError(new FieldError("name", "name", "Название уже занято"));
			return "items/itemEditForm";
		} else {
			itemService.upsert(item);
		}
		return "redirect:/item/list";
	}

	@GetMapping("/showEditForm")
	public String showEditForm(@RequestParam(value = "itemId", required = false) Long itemId, Model theModel) {
		Item item = null;
		if (itemId != null) {
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
		} catch (ExistReferencesItemException e) {
			theModel.addAttribute("item", e.getItem());
			theModel.addAttribute("deleteError",
					"Невозможно удалить компонент. Для данного компонента есть связь с другими компонентами");
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
