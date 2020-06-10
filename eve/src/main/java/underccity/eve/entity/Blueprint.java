package underccity.eve.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="blueprint")
public class Blueprint {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "resultitem", referencedColumnName = "id")
	private Item result;
	
	@Column(name="countresultitem")
	private int countResultItem;
	
	@OneToMany(mappedBy = "blueprint")
	Set<Components> components;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setCountResultItems(int countResultItem) {
		this.countResultItem = countResultItem;
	}

	public Set<Components> getComponents() {
		return components;
	}

	public void setComponents(Set<Components> components) {
		this.components = components;
	}
}
