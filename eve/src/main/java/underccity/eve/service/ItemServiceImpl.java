package underccity.eve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import underccity.eve.dao.ItemDAO;
import underccity.eve.entity.Item;
import underccity.eve.exception.ExistReferencesItemException;

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

	@Override
	public boolean upsert(Item item) {
		return itemRepository.upsert(item);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item findById(Long id) {
		return itemRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) throws ExistReferencesItemException {
		itemRepository.deleteById(id);
	}

}
