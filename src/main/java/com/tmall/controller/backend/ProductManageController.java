package com.tmall.controller.backend;

import com.google.common.collect.Maps;
import com.tmall.common.ServerResponse;
import com.tmall.pojo.Product;
import com.tmall.service.IFileService;
import com.tmall.service.IProductService;
import com.tmall.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author qiuxin
 */

@Controller
@RequestMapping(value = "/manage/product")
public class ProductManageController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    @RequestMapping(value = "save.do")
    @ResponseBody
    public ServerResponse productSave(Product product) {

        return iProductService.saveOrUpdateProduct(product);
    }

    @RequestMapping(value = "set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(Integer productId, Integer status) {

        return iProductService.setSaleStatus(productId, status);
    }

    @RequestMapping(value = "detail.do")
    @ResponseBody
    public ServerResponse getDetail(Integer productId) {

        return iProductService.manageProductDetail(productId);
    }

    @RequestMapping(value = "list.do")
    @ResponseBody
    public ServerResponse getList(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return iProductService.getProductList(pageNum, pageSize);
    }

    @RequestMapping(value = "search.do")
    @ResponseBody
    public ServerResponse productSearch(
            String productName, Integer productId,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return iProductService.searchProduct(productName, productId, pageNum, pageSize);
    }

    /**
     * 上传产品图片功能
     * @param file  要上传的图片
     * @param request  用于获取路径的request
     * @return  fileMap
     */
    @RequestMapping(value = "upload.do")
    @ResponseBody
    public ServerResponse upload(
            @RequestParam(value = "upload_file", required = false) MultipartFile file,
            HttpServletRequest request) {
        /*
         * 从request获取各种路径总结
         * request.getRealPath("url"); // 虚拟目录映射为实际目录
         * request.getRealPath("./");    // 网页所在的目录
         * request.getRealPath("../"); // 网页所在目录的上一层目录
         * request.getContextPath();    // 应用的web目录的名称
         */
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map<String, String> fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }

    @RequestMapping(value = "richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(
            @RequestParam(value = "upload_file", required = false) MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response) {

        Map<String, Object> resultMap = Maps.newHashMap();

        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        if (StringUtils.isBlank(targetFileName)) {
            resultMap.put("success", false);
            resultMap.put("msg", "上传失败");
            return resultMap;
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        resultMap.put("success", true);
        resultMap.put("msg", "上传成功");
        resultMap.put("file_path", url);

        response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
        return resultMap;
    }
}