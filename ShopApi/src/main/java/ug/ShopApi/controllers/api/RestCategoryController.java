package ug.ShopApi.controllers.api;


import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ug.ShopApi.domain.Category;
import ug.ShopApi.domain.Dto.CategoryDto;
import ug.ShopApi.services.Interfaces.CategoryService;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@ComponentScan()
public class RestCategoryController {


    @Autowired
    public RestCategoryController(DozerBeanMapper dozerBeanMapper, CategoryService categoryService)
    {
        _mapper =dozerBeanMapper;
        _categoryService = categoryService;
    }

    private CategoryService _categoryService;
    private DozerBeanMapper _mapper;

    @GetMapping("api/category")
    public ResponseEntity<Iterable<CategoryDto>> list()
    {
        ArrayList<Category> categoriesInDb = (ArrayList<Category>) _categoryService.readCategories();
        ArrayList<CategoryDto> productDtos = new ArrayList<>();
        categoriesInDb.forEach(p->productDtos.add(_mapper.map(p,CategoryDto.class)));
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("api/category/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int id)
    {

        Category rtn = _categoryService.readCategoryById(id);
        if(rtn!=null) {
            CategoryDto rtnDto = _mapper.map(rtn,CategoryDto.class);
            return new ResponseEntity<>(rtnDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("api/categoryByName/{name}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable String name)
    {

        Category rtn = _categoryService.readCategoryByName(name);
        if(rtn!=null) {
            CategoryDto rtnDto = _mapper.map(rtn,CategoryDto.class);
            return new ResponseEntity<>(rtnDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("api/category/new")
    public ResponseEntity addCategory(@RequestBody @Valid CategoryDto category, Errors errors)
    {
        if(errors.hasErrors())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Category categoryIndb = _mapper.map(category,Category.class);
        _categoryService.createCategory(categoryIndb);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("api/category/{id}")
    public ResponseEntity updateCategory(@RequestBody @Valid CategoryDto category, @PathVariable int id, Errors errors)
    {
        if(errors.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Category categoryIndb = _categoryService.readCategoryById(id);
        if(categoryIndb!=null)
        {
            categoryIndb = _mapper.map(category,Category.class);
            _categoryService.updateCategory(categoryIndb);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("api/category/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id)
    {
        Category categoryInDb = _categoryService.readCategoryById(id);
        if(categoryInDb==null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        _categoryService.deleteCategory(categoryInDb);
        return new ResponseEntity(HttpStatus.OK);
    }

}
