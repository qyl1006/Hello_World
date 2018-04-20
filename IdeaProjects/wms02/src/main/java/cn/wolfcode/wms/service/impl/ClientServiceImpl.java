package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.mapper.ClientMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private ClientMapper clientMapper;


    @Override
    public void insertOrUpdate(Client entity) {
        if (entity.getId() == null) {
            clientMapper.insert(entity);
        }else{
            clientMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        clientMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Client getById(Long id) {
        return clientMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult queryAll(QueryObject qo) {
        int count = clientMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<Client> data = clientMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }

    @Override
    public List<Client> listAll() {
        return clientMapper.selectAll();
    }
}
