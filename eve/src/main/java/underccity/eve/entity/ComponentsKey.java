package underccity.eve.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ComponentsKey implements Serializable {
	
	@Column(name = "blueprintid")
    Long blueprintId;
	
	@Column(name = "itemid")
    Long itemId;
	
    public Long getBlueprintId() {
		return blueprintId;
	}

	public void setBlueprintId(Long blueprintId) {
		this.blueprintId = blueprintId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


}
