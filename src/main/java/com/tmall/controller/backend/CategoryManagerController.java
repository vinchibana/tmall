package com.tmall.controller.backend;

import com.tmall.common.ServerResponse;
import com.tmall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qiuxin
 */
@Controller
@RequestMapping(value = "/manage/category")
public class CategoryManagerController {

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "add_category.do")
    @ResponseBody
    public ServerResponse addCategory(String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {

        return iCategoryService.addCategory(categoryName, parentId);
    }

    @RequestMapping(value = "set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(Integer categoryId, String categoryName) {

        return iCategoryService.updateCategoryName(categoryId, categoryName);
    }

    /**
     * 查询平级节点 category，与 get_deep_category 不同，前端获取参数 categoryId 在 mapper 查询时重命名为了 parentId
     */
    @RequestMapping(value = "get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(@RequestParam(value = "categoryId", defaultValue = "0") int categoryId) {

        return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping(value = "get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(@RequestParam(value = "categoryId", defaultValue = "0") int categoryId) {

        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }
}