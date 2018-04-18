package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IDepotService {
    void insertOrUpdate(Depot entity);

    void deleteById(Long id);

    Depot getById(Long id);

    PageResult queryAll(QueryObject qo);


    List<Depot> listAll();
}
