package underccity.eve.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import underccity.eve.entity.Blueprint;

public interface BlueprintService {
	
	public List<Blueprint> findAll();
	public Blueprint findById(Long id);
	public List<Blueprint> findByStartName(String name);
	public Page<Blueprint> findPaginated(Pageable pageable);
	public boolean upsert(Blueprint blueprint);
	public void deleteById(Long id);
}
