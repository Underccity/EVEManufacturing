package underccity.eve.service;

import java.util.List;

import underccity.eve.entity.Blueprint;

public interface BlueprintService {
	
	public List<Blueprint> findAll();
	public Blueprint findById(Long id);
//	public Blueprint findByName(String name);
	
	public boolean upsert(Blueprint blueprint);
	public void deleteById(Long id);
}
