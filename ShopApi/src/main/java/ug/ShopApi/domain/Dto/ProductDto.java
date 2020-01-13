package ug.ShopApi.domain.Dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ug.ShopApi.domain.Category;
import ug.ShopApi.domain.Manufacturer;

import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

public class ProductDto {
    private long productId;
    private String name;

    private double  price;
    private int numberInStock;
    private ManufacturerDto manufacturer;


    public ProductDto() {
    }

    public ManufacturerDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }


    public ProductDto(long productId, String name, double price, int numberInStock, ManufacturerDto manufacturer) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.numberInStock = numberInStock;
        this.manufacturer = manufacturer;
    }

}
