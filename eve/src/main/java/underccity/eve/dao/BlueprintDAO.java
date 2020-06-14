package underccity.eve.dao;

import java.util.List;

import underccity.eve.entity.Blueprint;

public interface BlueprintDAO {
	public List<Blueprint> findAll();
	public Blueprint findById(Long theId);
	public boolean save(Blueprint blueprint);
	public void deleteById(Long theId);
}