package underccity.eve.dao;

import java.util.List;

import underccity.eve.entity.Item;

public interface ItemDAO {
	
	public List<Item> findByName(String name);

}
