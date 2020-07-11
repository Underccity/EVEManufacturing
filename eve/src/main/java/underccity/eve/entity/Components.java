package underccity.eve.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "blueprint"})
public class Components {
	
	@EmbeddedId
	ComponentsKey id;
	
	public ComponentsKey getId() {
		return id;
	}

	public void setId(ComponentsKey id) {
		this.id = id;
	}

	@ManyToOne
	@MapsId("blueprintid")
	@JoinColumn(name="blueprintid")
	Blueprint blueprint;
	
	@ManyToOne
	@MapsId("itemid")
	@JoinColumn(name="itemid")
	@NotNull
	Item item;
	
	@JoinColumn(name="count")
	@NotNull
	@Min(1)
	Long count;

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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
