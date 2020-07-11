package underccity.eve.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import underccity.eve.dao.ItemDAO;
import underccity.eve.entity.Item;
import underccity.eve.exception.ExistReferencesItemException;

@Service
public class ItemServiceImpl implements ItemService {

	private ItemDAO itemRepository;
	
	public ItemServiceImpl(ItemDAO itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}

	@Override
	public List<Item> findByStartName(String name) {
		return itemRepository.findByStartName(name);
	}
	
	@Override
	public Item findByEqualName(String name) {
		return itemRepository.findByEqualName(name);
	}

	@Override
	public boolean upsert(Item item) {
		return itemRepository.upsert(item);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item findById(Long id) {
		return itemRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) throws ExistReferencesItemException {
		itemRepository.deleteById(id);
	}

	@Override
	public Page<Item> findPaginated(Pageable pageable) {
		
		List<Item> itemList = itemRepository.findAll();
		
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Item> list;
 
        if (itemList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, itemList.size());
            list = itemList.subList(startItem, toIndex);
        }
 
        Page<Item> itemPage
          = new PageImpl<Item>(list, PageRequest.of(currentPage, pageSize), itemList.size());
 
        return itemPage;
	}

}
