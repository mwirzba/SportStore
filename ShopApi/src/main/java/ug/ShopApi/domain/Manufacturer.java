package ug.ShopApi.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Manufacturer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long manufacturerId;
    private String name;
    private short yearOfCreation;
    private String representative;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Product> products  = new HashSet<>();


    public  Manufacturer () {}

    public Manufacturer(String name, short yearOfCreation, String representative) {
        this.name = name;
        this.yearOfCreation = yearOfCreation;
        this.representative = representative;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(short yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    @Override
    public boolean equals(Object obj) {
        Manufacturer manufacturer = (Manufacturer)obj;
        if (name.equals(manufacturer.name) &&
                yearOfCreation == manufacturer.yearOfCreation &&
                representative.equals(manufacturer.representative))
            return  true;
        else return false;
    }

}
