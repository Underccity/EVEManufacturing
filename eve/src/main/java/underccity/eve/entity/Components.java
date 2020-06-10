package underccity.eve.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Components {
	
	@EmbeddedId
	ComponentsKey id;
	
	@ManyToOne
	@MapsId("blueprintid")
	@JoinColumn(name="blueprintid")
	Blueprint blueprint;
	
	@ManyToOne
	@MapsId("itemid")
	@JoinColumn(name="itemid")
	Item item;
	
	int count;

	public Blueprint getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(Blueprint blueprint) {
		this.blueprint = blueprint;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
