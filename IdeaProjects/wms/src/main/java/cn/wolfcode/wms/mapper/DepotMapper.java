package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface DepotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Depot record);

    List<Depot> selectAll();
    //分页查询
    Integer queryCount(QueryObject qo);
    List<Depot> queryAll(QueryObject qo);
}