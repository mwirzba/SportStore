package ug.ShopApi.controllers.api;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ug.ShopApi.domain.Dto.CategoryDto;
import ug.ShopApi.domain.Dto.ProductDto;
import ug.ShopApi.domain.Product;
import ug.ShopApi.services.Interfaces.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;



@RestController
@ComponentScan()
public class RestProductController {

    @Autowired
    public RestProductController(DozerBeanMapper dozerBeanMapper, ProductService productService)
    {
        _mapper =dozerBeanMapper;
        _productService = productService;
    }

    private ProductService _productService;
    private DozerBeanMapper _mapper;

    @GetMapping("api/product")
    public ResponseEntity<Iterable<ProductDto>> list()
    {
        ArrayList<Product> productsInDb = (ArrayList<Product>) _productService.readProducts();
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        productsInDb.forEach(p->productDtos.add(_mapper.map(p,ProductDto.class)));
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("api/product/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id)
    {

        Product rtn = _productService.readProductById(id);
        if(rtn!=null) {
            ProductDto rtnDto = _mapper.map(rtn,ProductDto.class);
            return new ResponseEntity<>(rtnDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("api/productByName/{name}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String name)
    {

        Product rtn = _productService.readProductByName(name);
        if(rtn!=null) {
            ProductDto rtnDto = _mapper.map(rtn,ProductDto.class);
            return new ResponseEntity<>(rtnDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("api/product/new")
    public ResponseEntity addProduct(@RequestBody @Valid ProductDto product, Errors errors)
    {
        if(errors.hasErrors())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Product productInDb = _mapper.map(product,Product.class);
        _productService.createProduct(productInDb);
        return  new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("api/product/{id}")
    public ResponseEntity updateProduct(@RequestBody @Valid ProductDto product, @PathVariable long id, Errors errors)
    {
        if(errors.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ArrayList<Product> products =  (ArrayList<Product>) _productService.readProducts();
        Product productInDb =  products.stream().filter(p->p.getProductId()==id).findAny().orElse(null);
        if(productInDb!=null)
        {
            productInDb = _mapper.map(product,Product.class);
            _productService.updateProduct(productInDb);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("api/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id)
    {
        Product productInDb = _productService.readProductById(id);
        if(productInDb==null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        _productService.deleteProduct(productInDb);
        return new ResponseEntity(HttpStatus.OK);
    }

   @PutMapping("api/product/{id}/addCategory/{categoryId}")
   public ResponseEntity addCategoryToProduct(@PathVariable long id,@PathVariable int categoryId)
   {
       boolean result = _productService.addCategory(id,categoryId);
       if(!result)
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
       return new ResponseEntity(HttpStatus.CREATED);
   }

    @GetMapping("api/product/{id}/categories")
    public ResponseEntity getProductCategories(@PathVariable long id)
    {
        Product rtn = _productService.readProductById(id);

        if(rtn==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        ArrayList<CategoryDto> categories = new ArrayList<>();
        rtn.getCategories().forEach(p->categories.add(_mapper.map(p,CategoryDto.class)));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

   @PutMapping("api/product/{id}/removeCategory/{categoryId}")
   public ResponseEntity removeCategoryFromProduct(@PathVariable long id,@PathVariable int categoryId)
   {
       boolean result = _productService.addCategory(id,categoryId);

       if(!result)
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
       return new ResponseEntity(HttpStatus.OK);
   }

}
