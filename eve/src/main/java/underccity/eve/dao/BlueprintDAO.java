package underccity.eve.dao;

import java.util.List;

import underccity.eve.entity.Blueprint;

public interface BlueprintDAO {
	public List<Blueprint> findAll();
	public Blueprint findById(int theId);
	public void save(Blueprint blueprint);
	public void deleteById(int theId);
}