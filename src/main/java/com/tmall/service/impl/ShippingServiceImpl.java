package com.tmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.tmall.common.ServerResponse;
import com.tmall.dao.ShippingMapper;
import com.tmall.pojo.Shipping;
import com.tmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);

        // insert方法的useGeneratedKeys="true" keyProperty="id"，把新增加的主键赋值到自己定义的keyProperty中，插入parameterType（Shipping类对象）
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0) {

            // 前端返回信息用，采用上面返回的新增shippingId
            Map<String, Integer> result = Maps.newHashMap();
            result.put("shippingId", shipping.getId());
            return ServerResponse.createBySuccess("创建新收货地址成功", result);
        }
        return ServerResponse.createByErrorMessage("创建新收货地址失败");
    }

    @Override
    public ServerResponse<String> del(Integer userId, Integer shippingId) {
        int resultCount = shippingMapper.deleteByShippingIdUserId(shippingId, userId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除收货地址成功");
        }
        return ServerResponse.createByErrorMessage("删除收货地址失败");
    }

    @Override
    public ServerResponse update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("修改收货地址成功");
        }
        return ServerResponse.createByErrorMessage("修改收货地址失败");
    }

    @Override
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServerResponse.createBySuccess("更新地址成功", shipping);
    }

    @Override
    @SuppressWarnings({"raw type", "unchecked"})
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
