package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface ISupplierService {
    void insertOrUpdate(Supplier entity);

    void deleteById(Long id);

    Supplier getById(Long id);

    PageResult queryAll(QueryObject qo);

    List<Supplier> listAll();
}
