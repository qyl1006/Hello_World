package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.mapper.DepotMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepotServiceImpl implements IDepotService {
    @Autowired
    private DepotMapper depotMapper;


    @Override
    public void insertOrUpdate(Depot entity) {
        if (entity.getId() == null) {
            depotMapper.insert(entity);
        }else{
            depotMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        depotMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Depot getById(Long id) {
        return depotMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Depot> listAll() {
        return depotMapper.selectAll();
    }


    //分页
    @Override
    public PageResult queryAll(QueryObject qo) {
        Integer count = depotMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Depot> data = depotMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }
}
