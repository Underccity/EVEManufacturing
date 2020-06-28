package underccity.eve.exception;

import underccity.eve.entity.Item;

public class ExistReferencesItemException extends Exception {
	private Item item;

	public ExistReferencesItemException(Item item) {
		super();
		this.item = item;
	}

	public Item getItem() {
		return item;
	}
	
	
}
