package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String name;

	private String description;
	@Column(nullable = false)
	private Float price;

	//	@Column(nullable = false)
	@ManyToMany(mappedBy = "products")
	private List<Provider> providers;
	
	private String code;
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	private int inStock;


	public Product() {
	}


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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
   

	public List<Provider> getProviders() {
		return providers;
	}


}
