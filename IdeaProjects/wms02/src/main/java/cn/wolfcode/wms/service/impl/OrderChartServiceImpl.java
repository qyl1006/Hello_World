package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.mapper.DepartmentMapper;
import cn.wolfcode.wms.mapper.OrderChartMapper;
import cn.wolfcode.wms.query.OrderChartQueryObject;
import cn.wolfcode.wms.query.OrderSaleQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.service.IOrderChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderChartServiceImpl implements IOrderChartService{
    @Autowired
    private OrderChartMapper orderChartMapper;

    @Override
    public List<Map<String, Object>> queryOrderChart(OrderChartQueryObject qo) {
        return orderChartMapper.queryOrderChart(qo);
    }

    @Override
    public List<Map<String, Object>> queryOrderSale(OrderSaleQueryObject qo) {
        return orderChartMapper.queryOrderSale(qo);
    }
}
