package underccity.eve.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import underccity.eve.dao.BlueprintDAO;
import underccity.eve.entity.Blueprint;

@Service
public class BlueprintServiceImpl implements BlueprintService {

	private BlueprintDAO blueprintRepository;
	
	@Autowired
	public BlueprintServiceImpl(BlueprintDAO blueprintRepository) {
		super();
		this.blueprintRepository = blueprintRepository;
	}

	@Override
	public List<Blueprint> findAll() {
		return blueprintRepository.findAll();
	}

	@Override
	public Blueprint findById(Long id) {
		Blueprint result = blueprintRepository.findById(id);
		
		if (result == null) {
			throw new RuntimeException("Did not find blueprint id - " + id);
		}
		
		return result;
	}

//	@Override
//	public Blueprint findByName(String name) {
//		return blueprintRepository.findByName(name);
//	}


	@Override
	public boolean upsert(Blueprint blueprint) {
		return blueprintRepository.save(blueprint);

	}

	@Override
	public void deleteById(Long id) {
		blueprintRepository.deleteById(id);

	}

}
