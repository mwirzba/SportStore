package ug.ShopApi.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.domain.Product;
import ug.ShopApi.repositories.ManufacturerRepository;
import ug.ShopApi.services.Interfaces.ManufacturerService;


@Service
@ComponentScan()
public class ManufacturerServiceImplementation implements ManufacturerService {

    @Autowired
    private ManufacturerRepository _manufacturerRepository;

    public void createManufacturer(Manufacturer manufacturer) {
       _manufacturerRepository.save(manufacturer);
    }

    public Iterable<Manufacturer> readManufacturers() {
       return  _manufacturerRepository.findAll();
    }

    public Manufacturer readManufacturerByName(String manufacturerName) { return _manufacturerRepository.findByName(manufacturerName).orElse(null);}

    public Manufacturer readManufacturer(long manufacturerId) {
      return  _manufacturerRepository.findById(manufacturerId).orElse(null);
    }

    public void updateManufacturer(Manufacturer manufacturer) {
         _manufacturerRepository.save(manufacturer);
    }

    public void deleteManufacturer(long manufacturerId) {
        _manufacturerRepository.deleteById(manufacturerId);
    }

    @Override
    public void deleteAll() {
        _manufacturerRepository.deleteAll();
    }

}
