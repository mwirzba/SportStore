package ug.ShopApi.UnitTests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.repositories.ManufacturerRepository;
import ug.ShopApi.services.Implementations.ManufacturerServiceImplementation;
import ug.ShopApi.services.Interfaces.ManufacturerService;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
 class ManufacturerServiceTests {


    @InjectMocks
    private ManufacturerServiceImplementation _manufacturerService;


    @Mock
    private ManufacturerRepository _manufacturerRepository;


    @Test
     void CanRead()
    {
        Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");

        doReturn(Optional.of(manufacturer)).when(_manufacturerRepository).findById(1L);

        Manufacturer manufacturerInDb = _manufacturerService.readManufacturer(1);
        Assert.assertEquals(manufacturer,manufacturerInDb);
    }

    @Test
    void CanReadList()
    {
        ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
        Manufacturer manufacturer1 = new Manufacturer("test1", (short)1998,"rep1");
        Manufacturer manufacturer2 = new Manufacturer("test2", (short)1998,"rep2");
        Manufacturer manufacturer3 = new Manufacturer("test3", (short)1998,"rep3");
        manufacturers.add(manufacturer1);
        manufacturers.add(manufacturer2);
        manufacturers.add(manufacturer3);

        when(_manufacturerRepository.findAll()).thenReturn(manufacturers);

        Iterable<Manufacturer> manufacturersInDb = _manufacturerService.readManufacturers();

        Assert.assertEquals(manufacturers,manufacturersInDb);
    }

    @Test
    void CanCreate()
    {
        Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
        _manufacturerService.createManufacturer(manufacturer);
        verify(_manufacturerRepository,times(1)).save(manufacturer);
    }

    @Test
    void CanUpdate()
    {
        Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
        _manufacturerService.updateManufacturer(manufacturer);
        verify(_manufacturerRepository,times(1)).save(manufacturer);
    }

    @Test
    void CanDelete()
    {
        Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
        _manufacturerService.deleteManufacturer(1);
        verify(_manufacturerRepository,times(1)).deleteById(1L);
    }


    @Test
    void CanDeleteAll()
    {
        _manufacturerService.deleteAll();
        verify(_manufacturerRepository,times(1)).deleteAll();
    }


}
