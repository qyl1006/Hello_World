package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IClientService {
    void insertOrUpdate(Client entity);

    void deleteById(Long id);

    Client getById(Long id);

    PageResult queryAll(QueryObject qo);

    List<Client> listAll();
}
