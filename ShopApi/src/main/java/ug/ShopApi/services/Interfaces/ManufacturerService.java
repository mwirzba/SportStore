package ug.ShopApi.services.Interfaces;


import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.domain.Product;

public interface ManufacturerService {
    void createManufacturer(Manufacturer manufacturer);
    Iterable<Manufacturer> readManufacturers();
    Manufacturer readManufacturerByName(String manufacturerName);
    Manufacturer readManufacturer(long manufacturerId);
    void updateManufacturer(Manufacturer manufacturer);
    void deleteManufacturer(long manufacturerId);
    void deleteAll();
}
