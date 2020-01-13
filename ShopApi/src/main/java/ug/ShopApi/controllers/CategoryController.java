package ug.ShopApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ug.ShopApi.domain.Category;
import ug.ShopApi.services.Interfaces.CategoryService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class CategoryController {

    @Autowired
    public CategoryController(CategoryService categoryService)
    {
        _categoryService =categoryService;
    }

    private CategoryService _categoryService;

    @GetMapping("category")
    public String list(Model model)
    {
        ArrayList<Category> categories = (ArrayList<Category>) _categoryService.readCategories();
        model.addAttribute("categories",categories);
        return "category-list";
    }

    @RequestMapping("category/edit/{id}")
    public String edit(Model model,@PathVariable int id)
    {
        Category categoryInDB = _categoryService.readCategoryById(id);
        if(categoryInDB==null)
            return "redirect:category";

        model.addAttribute("category",categoryInDB);
        return "category-Form";
    }

    @RequestMapping("category/add")
    public String add(Model model)
    {
        model.addAttribute("category",new Category());
        return "category-Form";
    }

    @PostMapping("/category/save")
    public String save(@Valid @ModelAttribute("category") final Category category, Errors errors, Model model)
    {
        if(errors.hasErrors())
        {
            return "category-form";
        }
        if(category.getCategoryId()==0)
        {
            _categoryService.createCategory(category);
        }
        else
        {
            _categoryService.updateCategory(category);
        }
        return "redirect:/category";
    }

    @RequestMapping("category/delete/{id}")
    public String delete(@PathVariable int id)
    {
        Category categoryInDb = _categoryService.readCategoryById(id);
        if(categoryInDb!=null)
            _categoryService.deleteCategory(categoryInDb);
        return "redirect:/category";
    }
}
