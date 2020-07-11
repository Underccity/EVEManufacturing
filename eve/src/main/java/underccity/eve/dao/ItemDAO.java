package underccity.eve.dao;

import java.util.List;

import underccity.eve.entity.Item;
import underccity.eve.exception.ExistReferencesItemException;

public interface ItemDAO {
	
	public List<Item> findByStartName(String name);
	public Item findByEqualName(String name);
	public boolean upsert(Item item);
	public List<Item> findAll();
	public Item findById(Long id);
	public void deleteById(Long id) throws ExistReferencesItemException;
}
