package underccity.eve.service;

import java.util.List;

import underccity.eve.entity.Item;

public interface ItemService {
	public List<Item> findByName(String name);

}
