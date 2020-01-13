
package ug.ShopApi.domain;


import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Entity
@org.hibernate.annotations.NamedQuery(name = "product_findByName",query = "SELECT p FROM Product p where p.name = :productName")

public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long productId;

    private String name;
    private double  price;
    private int numberInStock;


    @ManyToOne(fetch=FetchType.EAGER)
    private Manufacturer manufacturer;


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "Product_Category",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private Set<Category> categories = new HashSet<>();


    public Product(String name, double price, int numberInStock) {
        this.name = name;
        this.price = price;
        this.numberInStock = numberInStock;
    }


    public Product(String name, double price, int numberInStock, Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.numberInStock = numberInStock;
        this.manufacturer = manufacturer;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void removeBCategory(Category category) {
        this.categories.remove(category);
        category.getProducts().remove(this);
    }

    public Product() { }

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
    public int getNumberInStock() { return numberInStock; }
    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }


    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturerId) {
        this.manufacturer = manufacturerId;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }


    @Override
    public String toString() {
        return name +" " +numberInStock;
    }

    @Override
    public boolean equals(Object obj) {
        Product product =  (Product)obj;
        if (name.equals(product.name) &&
                price == product.price &&
                numberInStock == product.numberInStock &&
                manufacturer.getManufacturerId() == product.manufacturer.getManufacturerId())
            return  true;
        else return false;
    }

}