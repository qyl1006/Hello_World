package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    int updateByPrimaryKey(Client record);

    //分页
    int queryCount(QueryObject qo);
    List<Client> queryAll(QueryObject qo);
}