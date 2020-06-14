package underccity.eve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import underccity.eve.dao.ItemDAO;
import underccity.eve.entity.Item;

@Service
public class ItemServiceImpl implements ItemService {

	private ItemDAO itemRepository;
	
	public ItemServiceImpl(ItemDAO itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}

	@Override
	public List<Item> findByName(String name) {
		return itemRepository.findByName(name);
	}

}
