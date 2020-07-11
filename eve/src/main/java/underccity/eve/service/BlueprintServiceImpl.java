package underccity.eve.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import underccity.eve.dao.BlueprintDAO;
import underccity.eve.entity.Blueprint;
import underccity.eve.entity.Item;

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


	@Override
	public boolean upsert(Blueprint blueprint) {
		return blueprintRepository.save(blueprint);

	}

	@Override
	public void deleteById(Long id) {
		blueprintRepository.deleteById(id);

	}

	@Override
	public List<Blueprint> findByStartName(String name) {
		return blueprintRepository.findByStartName(name);
	}

	@Override
	public Page<Blueprint> findPaginated(Pageable pageable) {
		List<Blueprint> blueprintList = blueprintRepository.findAll();
		
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startBlueprint = currentPage * pageSize;
        List<Blueprint> list;
 
        if (blueprintList.size() < startBlueprint) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startBlueprint + pageSize, blueprintList.size());
            list = blueprintList.subList(startBlueprint, toIndex);
        }
 
        Page<Blueprint> blueprintPage
          = new PageImpl<Blueprint>(list, PageRequest.of(currentPage, pageSize), blueprintList.size());
 
        return blueprintPage;
	}

}
