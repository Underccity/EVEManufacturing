package underccity.eve.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import underccity.eve.entity.Item;
import underccity.eve.exception.ExistReferencesItemException;

public interface ItemService {
	public List<Item> findByStartName(String name);
	public Item findByEqualName(String name);
	public List<Item> findAll();
	public boolean upsert(Item item);
	public Item findById(Long id);
	public void deleteById(Long id) throws ExistReferencesItemException;
	public Page<Item> findPaginated(Pageable pageable);
}
