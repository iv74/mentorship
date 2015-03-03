package com.github.javamentorship.category.controller;

import com.github.javamentorship.category.command.GoodsInsertForm;
import com.github.javamentorship.category.command.GoodsUpdateForm;
import com.github.javamentorship.category.dao.CategoryDao;
import com.github.javamentorship.category.dao.GoodsDao;
import com.github.javamentorship.category.domain.Category;
import com.github.javamentorship.category.domain.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
    public static final String REDIRECT_TO_INDEX = "redirect:/goods";

    @Autowired
    public GoodsDao goodsDao;
    @Autowired
    public CategoryDao categoryDao;

    @RequestMapping("")
    public ModelAndView index() {
        LOGGER.debug("Received request for SELECT from table GOODS");
        ModelAndView modelAndView = new ModelAndView("goods");
        List<Goods> goods = goodsDao.list();
        modelAndView.addObject("viewGoods", goods);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        LOGGER.debug("Received request for get InsertGoods View");
        ModelAndView modelAndView = new ModelAndView("goods_insert");
        modelAndView.addObject("insert_form", new GoodsInsertForm());
        List<Category> parentCategories = categoryDao.list();
        Map<String, String> parentGoodsItems = new LinkedHashMap<String, String>();
        for (Category category : parentCategories) {
            parentGoodsItems.put(category.getId().toString(), category.getName());
        }
        modelAndView.addObject("parentCategories", parentGoodsItems);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("insert_form") GoodsInsertForm form, BindingResult result) {
        LOGGER.debug("Received request to create {}", form);
        Goods goods = new Goods();
        goods.setName(form.getName());
        goods.setPrice(form.getPrice());
        goods.setCategoryId(form.getCategoryId());
        goods.setRest(form.getRest());
        goodsDao.add(goods);
        return REDIRECT_TO_INDEX;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") int id) {
        GoodsUpdateForm updateForm = new GoodsUpdateForm();
        Goods goods = goodsDao.getById(id);
        updateForm.setId(goods.getId());
        updateForm.setName(goods.getName());
        updateForm.setPrice(goods.getPrice());
        updateForm.setCategoryId(goods.getCategoryId());
        updateForm.setRest(goods.getRest());
        return new ModelAndView("goods_update", "update_form", updateForm);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("update_form") GoodsUpdateForm form, BindingResult result) {
        LOGGER.debug("Received request for UPDATE data in table GOODS");
        if (result.hasErrors()) {
            return "goods_update";
        } else {
            Goods goods = goodsDao.getById(form.getId());
            goods.setName(form.getName());
            goods.setPrice(form.getPrice());
            goods.setCategoryId(form.getCategoryId());
            goods.setRest(form.getRest());
            goodsDao.update(goods);
            return REDIRECT_TO_INDEX;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam int id) {
        LOGGER.debug("Received request for DELETE new data in table GOODS");
        Goods goods = goodsDao.getById(id);
        if (goods == null) {
            LOGGER.debug("Goods not found");
            return REDIRECT_TO_INDEX;
        }
        goodsDao.delete(goods);
        return REDIRECT_TO_INDEX;
    }
}