package com.tmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tmall.common.ServerResponse;
import com.tmall.dao.CategoryMapper;
import com.tmall.pojo.Category;
import com.tmall.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 主要难点为递归寻找 parentId
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }

        // id, sortOrder 不需设置，updateTime, createTime 由 SQL 处理为 now(), 仅需传入categoryName, parentId, status
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);

        int rowCount = categoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }

        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新品类名成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名失败");
    }

    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {

        List<Category> categoryList = categoryMapper.selectChildrenParallelCategoryByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            log.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * ex：传入 categoryId = 0，查询子节点为 100001~100005
     * 再次查询 100001 子节点为 100006~100012，下同
     *
     * @param categoryId  品类ID
     * @return  categoryIdList
     */
    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {

        Set<Category> categorySet = Sets.newHashSet();
        categorySet = findChildCategory(categorySet, categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    /**
     * 递归找出子节点 ChildCategory
     *
     * @param categorySet  接收category的集合
     * @param categoryId  品类ID
     * @return  品类集合
     */
    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }

        List<Category> categoryList = categoryMapper.selectChildrenParallelCategoryByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }
}