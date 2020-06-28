package underccity.eve.service;

import java.util.List;

import underccity.eve.entity.Item;
import underccity.eve.exception.ExistReferencesItemException;

public interface ItemService {
	public List<Item> findByName(String name);
	public List<Item> findAll();
	public boolean upsert(Item item);
	public Item findById(Long id);
	public void deleteById(Long id) throws ExistReferencesItemException;
}
