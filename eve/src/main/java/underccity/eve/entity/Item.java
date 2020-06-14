package underccity.eve.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "blueprint", "components"})
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;

	@OneToOne(mappedBy="result")
	private Blueprint blueprint;
	
	@OneToMany(mappedBy = "item")
	Set<Components> components;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blueprint getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(Blueprint blueprint) {
		this.blueprint = blueprint;
	}

	public Set<Components> getComponents() {
		return components;
	}

	public void setComponents(Set<Components> components) {
		this.components = components;
	}
}
