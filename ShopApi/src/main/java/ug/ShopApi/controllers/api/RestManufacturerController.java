package ug.ShopApi.controllers.api;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ug.ShopApi.domain.Dto.ManufacturerDto;
import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.services.Interfaces.ManufacturerService;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@ComponentScan()
public class RestManufacturerController {

    @Autowired
    public RestManufacturerController(DozerBeanMapper dozerBeanMapper, ManufacturerService manufacturerService)
    {
        _mapper =dozerBeanMapper;
        _manufacturerService = manufacturerService;
    }

    private ManufacturerService _manufacturerService;
    private DozerBeanMapper _mapper;

    @GetMapping("api/manufacturer")
    public ResponseEntity<Iterable<ManufacturerDto>> list()
    {
        ArrayList<Manufacturer> productsInDb = (ArrayList<Manufacturer>) _manufacturerService.readManufacturers();
        ArrayList<ManufacturerDto> ManufacturerDtos = new ArrayList<>();
        productsInDb.forEach(p->ManufacturerDtos.add(_mapper.map(p,ManufacturerDto.class)));
        return new ResponseEntity<>(ManufacturerDtos, HttpStatus.OK);
    }

    @GetMapping("api/manufacturer/{id}")
    public ResponseEntity<ManufacturerDto> getManufacturer(@PathVariable long id)
    {
        Manufacturer rtn = _manufacturerService.readManufacturer(id);
        if(rtn!=null) {
            ManufacturerDto rtnDto = _mapper.map(rtn,ManufacturerDto.class);
            return new ResponseEntity<>(rtnDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("api/manufacturer/new")
    public ResponseEntity addManufacturer(@RequestBody @Valid ManufacturerDto manufacturer, Errors errors)
    {
        if(errors.hasErrors())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Manufacturer manufacturerInDb = _mapper.map(manufacturer,Manufacturer.class);
        _manufacturerService.createManufacturer(manufacturerInDb);
        return  new ResponseEntity(HttpStatus.CREATED);
    }


    @PutMapping("api/manufacturer/{id}")
    public ResponseEntity updateManufactorer(@RequestBody @Valid ManufacturerDto manufacturer,@PathVariable long id, Errors errors)
    {
        if(errors.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Manufacturer manufacturerInDb =  _manufacturerService.readManufacturer(id);
        if(manufacturerInDb!=null)
        {
            manufacturerInDb = _mapper.map(manufacturer,Manufacturer.class);
            manufacturerInDb.setManufacturerId(id);
            _manufacturerService.updateManufacturer(manufacturerInDb);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("api/manufacturer/{id}")
    public ResponseEntity deleteManufactorer(@PathVariable long id)
    {
        Manufacturer manufacturerInDb = _manufacturerService.readManufacturer(id);
        if(manufacturerInDb==null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        _manufacturerService.deleteManufacturer(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
