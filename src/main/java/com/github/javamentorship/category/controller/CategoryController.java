package com.github.javamentorship.category.controller;

import com.github.javamentorship.category.command.CategoryInsertForm;
import com.github.javamentorship.category.command.CategoryUpdateForm;
import com.github.javamentorship.category.dao.CategoryDao;
import com.github.javamentorship.category.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryDao categoryDao;

    @RequestMapping("/category")
    public ModelAndView viewCategory() throws SQLException, ClassNotFoundException {
        LOGGER.debug("Received request for SELECT from table CATEGORY");
        ModelAndView modelAndView = new ModelAndView("category");
        List<Category> categories = categoryDao.listCategory();
        modelAndView.addObject("viewCategory", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/category_insert.html", method = RequestMethod.GET)
    public ModelAndView getInsertCategoryView() throws SQLException, ClassNotFoundException {
        LOGGER.debug("Received request for get InsertCategory View");
        return new ModelAndView("category_insert", "insert_form", new CategoryInsertForm());
    }

    @RequestMapping(value = "/category_insert.html", method = RequestMethod.POST)
    public String insertCategory(@ModelAttribute("insert_form") CategoryInsertForm form) {
        LOGGER.debug("Received request to create {}", form);
        try {
            categoryDao.addCategory(form.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/category";
    }

    @RequestMapping(value = "/category_update_form.html", method = RequestMethod.GET)
    public ModelAndView getUpdateCategoryView(@RequestParam("id") int id,
                                              @RequestParam("name") String name,
                                              @RequestParam("parentId") int parentId) {

        CategoryUpdateForm updateForm = new CategoryUpdateForm();
        updateForm.setId(id);
        updateForm.setName(name);
        updateForm.setParentId(parentId);
        return new ModelAndView("category_update", "update_form", updateForm);
    }

    @RequestMapping(value = "/category_update.html", method = RequestMethod.POST)
    public String updateCategory(@ModelAttribute("update_form") CategoryUpdateForm form) throws SQLException, ClassNotFoundException {
        categoryDao.update(form.getName(), form.getParentId(), form.getId());
        LOGGER.debug("Received request for UPDATE data in table CATEGORY");
        return "redirect:/category";
    }
    
    @RequestMapping("/category_delete.html/{id}")
    public String deleteCategory(@PathVariable("id") int id) throws SQLException, ClassNotFoundException {
        categoryDao.deleteCategory(id);
        LOGGER.debug("Received request for DELETE new data in table CATEGORY");
        return "redirect:/category";
    }

}