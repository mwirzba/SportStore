package ug.ShopApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.services.Interfaces.ManufacturerService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class ManufacturerController {

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService)
    {
        _manufacturerService =manufacturerService;
    }

    private ManufacturerService _manufacturerService;

    @GetMapping("manufacturer")
    public String list(Model model)
    {
        ArrayList<Manufacturer> manufacturers = (ArrayList<Manufacturer>) _manufacturerService.readManufacturers();
        model.addAttribute("manufacturers",manufacturers);
        return "manufacturer-list";
    }

    @RequestMapping("manufacturer/edit/{id}")
    public String edit(Model model,@PathVariable int id)
    {
        Manufacturer manufacturerInDB = _manufacturerService.readManufacturer(id);
        if(manufacturerInDB==null)
            return "redirect:Manufacturer";

        model.addAttribute("manufacturer",manufacturerInDB);
        return "manufacturer-form";
    }

    @RequestMapping("manufacturer/add")
    public String add(Model model)
    {
        model.addAttribute("manufacturer",new Manufacturer());
        return "manufacturer-form";
    }

    @PostMapping("/manufacturer/save")
    public String save(@Valid @ModelAttribute("manufacturer") final Manufacturer manufacturer, Errors errors, Model model)
    {
        if(errors.hasErrors())
        {
            return "manufacturer-form";
        }
        if(manufacturer.getManufacturerId()==0)
        {
            _manufacturerService.createManufacturer(manufacturer);
        }
        else
        {
            _manufacturerService.updateManufacturer(manufacturer);
        }
        return "redirect:/manufacturer";
    }

    @RequestMapping("manufacturer/delete/{id}")
    public String delete(@PathVariable int id)
    {
        Manufacturer manufacturerInDb = _manufacturerService.readManufacturer(id);
        if(manufacturerInDb!=null)
            _manufacturerService.deleteManufacturer(manufacturerInDb.getManufacturerId());
        return "redirect:/manufacturer";
    }
}
