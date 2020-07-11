package underccity.eve.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BPMamufacturing {

	private List<BPItem> materials;
	private List<BPItem> products;
	
	public List<BPItem> getMaterials() {
		return materials;
	}
	public void setMaterials(List<BPItem> materials) {
		this.materials = materials;
	}
	public List<BPItem> getProducts() {
		return products;
	}
	public void setProducts(List<BPItem> products) {
		this.products = products;
	}
	
	
}
