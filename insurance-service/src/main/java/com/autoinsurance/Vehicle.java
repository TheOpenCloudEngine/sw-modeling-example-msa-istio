/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: uengine
 * License Type: Purchased
 */
package com.autoinsurance;

import com.autoinsurance.entities.Brand;
import com.autoinsurance.services.GMVService;
import org.metaworks.annotation.RestAssociation;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.multitenancy.persistence.BeforeSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Vehicle")
@Configurable
public class Vehicle implements Serializable, BeforeSave {
	public Vehicle() {
	}
	
	@Column(name="Id", nullable=false, length=19)	
	@Id	
	@GeneratedValue(generator="COM_AUTOINSURANCE_VEHICLE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_AUTOINSURANCE_VEHICLE_ID_GENERATOR", strategy="native")	
	private Long id;
	
	@ManyToOne(targetEntity=com.autoinsurance.Customer.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="CustomerId", referencedColumnName="Id", nullable=false) })	
	private com.autoinsurance.Customer customer;
	
	@Column(name="ModelName", nullable=true, length=255)	
	private String modelName;
	
	@Column(name="Type", nullable=false, length=10)	
	private int type;
	
	private String brand;

	@RestAssociation(path="vehicle-detail/{modelName}", serviceId = "gmv-service")
	transient String vehicleDetail;

	
	private void setId(long value) {
		setId(new Long(value));
	}
	
	private void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getORMID() {
		return getId();
	}
	
	public void setModelName(String value) {
		this.modelName = value;
	}
	
	public String getModelName() {
		return modelName;
	}
	
	public void setType(int value) {
		this.type = value;
	}
	
	public int getType() {
		return type;
	}
	
	public void setBrand(String value) {
		this.brand = value;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setCustomer(com.autoinsurance.Customer value) {
		this.customer = value;
	}
	
	public com.autoinsurance.Customer getCustomer() {
		return customer;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}


//	@Autowired
//	GMVService gmvService;

	@Override
	public void beforeSave() {  //Denormalization Example
		GMVService gmvService = MetaworksRemoteService.getInstance().getComponent(GMVService.class);

		Brand brand = gmvService.getBrand(getModelName());

		setBrand(brand.getName());
	}
}
