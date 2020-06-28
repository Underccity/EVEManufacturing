package underccity.eve.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="blueprint")
public class Blueprint {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotBlank
	@Column(name="name")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "resultitem", referencedColumnName = "id")
	@NotNull
	private Item result;
	
	@Column(name="countresultitem")
	@NotNull
	@Min(1)
	private int countResultItem;
	
	@OneToMany(mappedBy = "blueprint", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
	@NotEmpty
	@Valid
	private List<Components> components;

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

	public Item getResult() {
		return result;
	}

	public void setResult(Item result) {
		this.result = result;
	}

	public int getCountResultItem() {
		return countResultItem;
	}

	public void setCountResultItem(int countResultItem) {
		this.countResultItem = countResultItem;
	}

	public List<Components> getComponents() {
		return components;
	}
	
	public void setComponents(List<Components> components) {
		this.components = components;
	}

}
