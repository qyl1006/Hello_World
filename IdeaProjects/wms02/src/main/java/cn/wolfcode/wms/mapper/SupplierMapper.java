package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);


    int updateByPrimaryKey(Supplier record);

    //分页
    int queryCount(QueryObject qo);
    List<Supplier> queryAll(QueryObject qo);

    //全部供应商
    List<Supplier> listAll();
}