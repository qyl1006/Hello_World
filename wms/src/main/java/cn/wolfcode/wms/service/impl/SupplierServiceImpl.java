package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.mapper.SupplierMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private SupplierMapper supplierMapper;

    public void saveOrUpdate(Supplier entity) {
        if (entity.getId() == null) {
            supplierMapper.insert(entity);
        } else {
            supplierMapper.updateByPrimaryKey(entity);
        }
    }

    public void delete(Long id) {
        supplierMapper.deleteByPrimaryKey(id);
    }

    public Supplier get(Long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    public List<Supplier> list() {
        return supplierMapper.selectAll();
    }

    public PageResult query(QueryObject qo) {
        //总记录数
        Integer rows = supplierMapper.queryForCount(qo);
        if (rows == 0) {
            return PageResult.EMPTY_PAGE;
        }
        //----------------------------
        List<?> data = supplierMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
    }
}
