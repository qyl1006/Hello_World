package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface DepotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    int updateByPrimaryKey(Depot record);

    //分页
    int queryCount(QueryObject qo);
    List<Depot> queryAll(QueryObject qo);

    List<Depot> listAll();

    //通过员工ID查部门

}