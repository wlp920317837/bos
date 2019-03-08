package cn.wlp.bos.service.impl;

import cn.wlp.bos.common.PageBean;
import cn.wlp.bos.dao.RegionDao;
import cn.wlp.bos.domain.Region;
import cn.wlp.bos.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wlp
 * @date 2019-03-06 19:47
 * 区域service
 **/
@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public void saveBatch(List<Region> regionList) {
        for (Region region : regionList) {
            regionDao.saveOrUpdate(region);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    public void add(Region model) {
        regionDao.save(model);
    }

    @Override
    public void edit(Region model) {
        regionDao.saveOrUpdate(model);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            regionDao.deleteById(id);
        }
    }

    @Override
    public List<Region> findByQ(String q) {
        List<Region> list = regionDao.findByQ(q);
        return list;
    }

    @Override
    public List<Region> findAll() {
        List<Region> list = regionDao.findAll();
        return list;
    }

}
