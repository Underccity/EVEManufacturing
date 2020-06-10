package underccity.eve.service;

import java.util.List;

import underccity.eve.entity.Blueprint;

public interface BlueprintService {
	
	public List<Blueprint> findAll();
	public Blueprint findById(int id);
//	public Blueprint findByName(String name);
	
	public void upsert(Blueprint blueprint);
	public void deleteById(int id);
}
