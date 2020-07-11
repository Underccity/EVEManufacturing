package underccity.eve.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BPItem {
	
	private Long quantity;
	private Long typeID;
	
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getTypeID() {
		return typeID;
	}
	public void setTypeID(Long typeID) {
		this.typeID = typeID;
	}
	
}
