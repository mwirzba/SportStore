package ug.ShopApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ug.ShopApi.domain.Category;
import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.domain.Product;
import ug.ShopApi.domain.ProductFormViewModel;
import ug.ShopApi.services.Interfaces.CategoryService;
import ug.ShopApi.services.Interfaces.ManufacturerService;
import ug.ShopApi.services.Interfaces.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class ProductController {

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService)
    {
        _productService = productService;
        _categoryService =categoryService;
        _manufacturerService = manufacturerService;
    }

    private ManufacturerService _manufacturerService;
    private ProductService _productService;
    private CategoryService _categoryService;

    @GetMapping("product")
    public String list(Model model)
    {
        ArrayList<Product> productsInDb = (ArrayList<Product>) _productService.readProducts();
        ArrayList<Category> categories = (ArrayList<Category>) _categoryService.readCategories();
        model.addAttribute("products",productsInDb);
        model.addAttribute("allCategories",categories);
        return "products-list";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(Model model,@PathVariable long id)
    {
        Product productInDb = _productService.readProductById(id);
        if(productInDb==null)
            return "redirect:product";

        ProductFormViewModel productFormViewModel = new ProductFormViewModel(productInDb
                ,(ArrayList<Category>) _categoryService.readCategories()
                ,(ArrayList<Manufacturer>)_manufacturerService.readManufacturers());

        model.addAttribute("productFormViewModel",productFormViewModel);
        return "product-form";
    }

    @RequestMapping("product/add")
    public String add(Model model)
    {
        Product newProduct = new Product("",0,0);
        ProductFormViewModel productFormViewModel = new ProductFormViewModel(
                newProduct
                ,(ArrayList<Category>) _categoryService.readCategories()
                ,(ArrayList<Manufacturer>)_manufacturerService.readManufacturers());
        model.addAttribute("productFormViewModel",productFormViewModel);
        return "product-form";
    }

    @PostMapping("/product/save")
    public String save(@Valid @ModelAttribute("product") final ProductFormViewModel productFormViewModel, Errors errors, Model model)
    {
        if(errors.hasErrors())
        {
            return "product-form";
        }
        if(productFormViewModel.getProduct().getProductId()==0)
        {
            _productService.createProduct(productFormViewModel.getProduct());
            Product product = _productService.readProductById(productFormViewModel.getProduct().getProductId());
            if(productFormViewModel.getNewCategoryId()!=-1)
                _productService.addCategory(product.getProductId(),productFormViewModel.getNewCategoryId());
        }
        else
        {   Product product = productFormViewModel.getProduct();
            if(productFormViewModel.getNewCategoryId() != -1) {
                _productService.addCategory(product.getProductId(), productFormViewModel.getNewCategoryId());
                product = _productService.readProductById(product.getProductId());
            }
            if(productFormViewModel.getDeletedCategoryId() != -1) {
                _productService.removeCategory(product.getProductId(), productFormViewModel.getDeletedCategoryId());
                product = _productService.readProductById(product.getProductId());
            }
            else
                _productService.updateProduct(product);
        }
        return "redirect:/product";
    }

    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable long id)
    {
        Product productInDb = _productService.readProductById(id);
        if(productInDb!=null)
            _productService.deleteProduct(productInDb);
        return "redirect:/product";
    }

}
