package model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Date creationTime;
	@Column(nullable = false)
	@ManyToOne
	private Customer customer;
	@Column(nullable = false)
	@OneToMany
	private Set<OrderLine> productOrderLine;
	private int state;



	public Orders(){
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Set<OrderLine> getProductOrderLine() {
		return productOrderLine;
	}


	public void setProductOrderLine(OrderLine line) {
		if(productOrderLine==null || productOrderLine.isEmpty()){
			productOrderLine= new HashSet<OrderLine>();
			productOrderLine.add(line);
		}
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
		Orders other = (Orders) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}