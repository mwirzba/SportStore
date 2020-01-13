package ug.ShopApi.domain.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ug.ShopApi.domain.Product;

import java.util.List;

public class ManufacturerDto {
    private long manufacturerId;
    private String name;


    @JsonIgnore
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    private List<Product> productList;

    public ManufacturerDto() {
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

    public ManufacturerDto(long manufacturerId, String name, short yearOfCreation, String representative) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.yearOfCreation = yearOfCreation;
        this.representative = representative;
    }

    private short yearOfCreation;
    private String representative;
}
