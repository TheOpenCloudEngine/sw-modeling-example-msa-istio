package gov.gmv;

import javax.persistence.*;

/**
 * Created by uengine on 2017. 12. 3..
 */
@Entity
public class Vehicle {

    @Id
    String vehicleId;

    @ManyToOne
    @JoinColumn(name="brandId")
    Owner owner;

    String name;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


}
