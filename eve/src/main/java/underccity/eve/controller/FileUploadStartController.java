package underccity.eve.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import underccity.eve.entity.BPItem;
import underccity.eve.entity.Blueprint;
import underccity.eve.entity.Components;
import underccity.eve.entity.ComponentsKey;
import underccity.eve.entity.FileUploadBPActivities;
import underccity.eve.entity.FileUploadBlueprints;
import underccity.eve.entity.FileUploadTypeIdInnerModel;
import underccity.eve.entity.FileUploadTypeIdModel;
import underccity.eve.entity.Item;
import underccity.eve.service.BlueprintService;
import underccity.eve.service.ItemService;

@RestController
@RequestMapping("/fileUploadStart")
public class FileUploadStartController {

	private ItemService itemService;
	private BlueprintService bluePrintService;

	public FileUploadStartController(ItemService itemService, BlueprintService bluePrintService) {
		super();
		this.itemService = itemService;
		this.bluePrintService = bluePrintService;
	}

	@GetMapping("/startItems")
	public void startUploadItems() {
		ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
		try {
			String fileContent = new String(
					Files.readAllBytes(Paths.get("C://Users/lenic/OneDrive/Рабочий стол/sde/fsd/typeIDs.yaml")));

			FileUploadTypeIdModel typeIds = yamlReader.readValue(fileContent, FileUploadTypeIdModel.class);
			Map<String, FileUploadTypeIdInnerModel> inner = typeIds.getUnknownSubCategories();

			for (String key : inner.keySet()) {
				String enName = inner.get(key).getName().getEn();
				String ruDesc = "";
				if (inner.get(key).getDescription() != null) {
					ruDesc = inner.get(key).getDescription().getRu();
				}
				if (inner.get(key).isPublished() && enName != null && !enName.endsWith("Blueprint")
						&& !enName.endsWith("Formula")) {
					Item item = new Item();
					item.setId(Long.parseLong(key));
					item.setName(enName);
					item.setDescription(ruDesc);
					itemService.upsert(item);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@GetMapping("/startBlueprints")
	public void startUploadBlueprints() {
		ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
		try {
			String fileContentBPNames = new String(Files.readAllBytes(Paths.get("C://Users/lenic/OneDrive/Рабочий стол/sde/fsd/typeIDs.yaml")));
			String fileContentBPContent = new String(Files.readAllBytes(Paths.get("C://Users/lenic/OneDrive/Рабочий стол/sde/fsd/blueprints.yaml")));
			FileUploadTypeIdModel bpsNames = yamlReader.readValue(fileContentBPNames, FileUploadTypeIdModel.class);
			FileUploadBlueprints bpsContents = yamlReader.readValue(fileContentBPContent, FileUploadBlueprints.class);
			
			Map<String, FileUploadTypeIdInnerModel> innerBpNames = bpsNames.getUnknownSubCategories();
			for(String key: innerBpNames.keySet()) {
				String enName = innerBpNames.get(key).getName().getEn();
				if(innerBpNames.get(key).isPublished() && enName!=null && (enName.endsWith("Blueprint") || enName.endsWith("Formula")) 
						&& bpsContents.getUnknownSubCategories().get(key) != null) {
					FileUploadBPActivities blueprintItem =  bpsContents.getUnknownSubCategories().get(key).getActivities();
					
					if(blueprintItem.getManufacturing() != null && blueprintItem.getManufacturing().getMaterials() != null ||
						blueprintItem.getReaction() != null && blueprintItem.getReaction().getMaterials() != null) {
						
						Blueprint blueprint = new Blueprint();
						blueprint.setId(Long.parseLong(key));
						blueprint.setName(enName);
						List<Components> components = new ArrayList<Components>();
						if(blueprintItem.getManufacturing() != null) {
							BPItem bResult = blueprintItem.getManufacturing().getProducts().get(0);
							
							if(itemService.findById(bResult.getTypeID()) == null){
								continue;
							}
							Item resultItem = new Item();
							resultItem.setId(bResult.getTypeID());
							blueprint.setResult(resultItem);
							blueprint.setCountResultItem(bResult.getQuantity());
							
							for(BPItem bpComponent : blueprintItem.getManufacturing().getMaterials()) {
								
								Components component = new Components();
								ComponentsKey componentKey = new ComponentsKey();
								componentKey.setBlueprintId(Long.parseLong(key));
								componentKey.setItemId(bpComponent.getTypeID());;
								component.setId(componentKey);
								component.setCount(bpComponent.getQuantity());
								Item item = new Item();
								item.setId(bpComponent.getTypeID());
								component.setItem(item);
								components.add(component);
							}
						}
						
						if(blueprintItem.getReaction() != null) {
							BPItem bResult = blueprintItem.getReaction().getProducts().get(0);
							if(itemService.findById(bResult.getTypeID()) == null){
								continue;
							}
							Item resultItem = new Item();
							resultItem.setId(bResult.getTypeID());
							blueprint.setResult(resultItem);
							blueprint.setCountResultItem(bResult.getQuantity());
							
							for(BPItem bpComponent : blueprintItem.getReaction().getMaterials()) {
								
								Components component = new Components();
								ComponentsKey componentKey = new ComponentsKey();
								componentKey.setBlueprintId(Long.parseLong(key));
								componentKey.setItemId(bpComponent.getTypeID());;
								component.setId(componentKey);
								component.setCount(bpComponent.getQuantity());
								Item item = new Item();
								item.setId(bpComponent.getTypeID());
								component.setItem(item);
								components.add(component);
								
							}
						}
						
						blueprint.setComponents(components);
//						if(blueprint.getResult() == null || blueprint.getResult().getName()  ==null || blueprint.getId() ==null || blueprint.getComponents() == null) {
//							System.out.println(key + ":"  + enName);
//						}
						bluePrintService.upsert(blueprint);
					}
				}
			}
		}catch(

	IOException e)
	{
		e.printStackTrace();
	}

}}
