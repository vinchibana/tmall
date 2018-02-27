package com.tmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.tmall.common.ServerResponse;
import com.tmall.service.IOrderService;
import com.tmall.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private IOrderService iOrderService;

    @RequestMapping(value = "list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return iOrderService.manageList(pageNum, pageSize);
    }

    @RequestMapping(value = "detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(Long orderNo) {

        return iOrderService.manageDetail(orderNo);
    }

    @RequestMapping(value = "search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(
            Long orderNo,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return iOrderService.manageSearch(orderNo, pageNum, pageSize);
    }

    @RequestMapping(value = "send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(Long orderNo) {

        return iOrderService.manageSendGoods(orderNo);
    }
}