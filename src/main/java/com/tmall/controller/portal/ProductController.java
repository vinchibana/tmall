package com.tmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.tmall.common.ServerResponse;
import com.tmall.service.IProductService;
import com.tmall.vo.ProductDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 商品类，目前接口仅有搜索并显示商品细节，但涉及排序分页
 */
@Controller
@RequestMapping(value = "/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVo> detailRESTful(@PathVariable Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {

        return iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }

    /**
     * 自定义方法隔离ambiguous请求
     */
    @RequestMapping(value = "/{keyword}/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(
            @PathVariable(value = "keyword") String keyword,
            @PathVariable(value = "categoryId") Integer categoryId,
            @PathVariable(value = "pageNum") Integer pageNum,
            @PathVariable(value = "pageSize") Integer pageSize,
            @PathVariable(value = "orderBy") String orderBy) {

        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(orderBy)) {
            orderBy = "price_asc";
        }
        return iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }

    @RequestMapping(value = "/keyword/{keyword}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(
            @PathVariable(value = "keyword") String keyword,
            @PathVariable(value = "pageNum") Integer pageNum,
            @PathVariable(value = "pageSize") Integer pageSize,
            @PathVariable(value = "orderBy") String orderBy) {

        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(orderBy)) {
            orderBy = "price_asc";
        }
        return iProductService.getProductByKeywordCategory(keyword, null, pageNum, pageSize, orderBy);
    }

    @RequestMapping(value = "/category/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(
            @PathVariable(value = "categoryId") Integer categoryId,
            @PathVariable(value = "pageNum") Integer pageNum,
            @PathVariable(value = "pageSize") Integer pageSize,
            @PathVariable(value = "orderBy") String orderBy) {

        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(orderBy)) {
            orderBy = "price_asc";
        }
        return iProductService.getProductByKeywordCategory("", categoryId, pageNum, pageSize, orderBy);
    }
}