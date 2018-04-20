package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.OrderChartQueryObject;
import cn.wolfcode.wms.query.OrderSaleQueryObject;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface OrderChartMapper {

    List<Map<String, Object>> queryOrderChart(OrderChartQueryObject qo);
    List<Map<String, Object>> queryOrderSale(OrderSaleQueryObject qo);
}