package com.railway.manager.service;

import com.google.common.collect.Lists;
import com.railway.manager.common.query.GenericQuery;
import com.railway.manager.common.query.ListQuery;
import com.railway.manager.model.Filters;
import com.railway.manager.vo.SysDictVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: railway-manager
 * @description:
 * @author: lijiwen
 * @create: 2019-12-28 16:46
 **/
@Service
public class IndexFilterService extends AbstractService {
    /**
     * @return java.util.List<com.railway.manager.model.Filters>
     * @author: Lijiwen
     * Description:
     * @param: * @param
     * @createDate 2019-12-28 16:48
     **/
    public List<Filters> listFactory() {
        List<Filters> filters = getFilters("manufacturerType");
        return filters;

    }

    public List<Filters> listLine() {
        List<Filters> filters = getFilters("lineType");
        return filters;
    }

    public List<Filters> listCity() {
        List<Filters> filters = getFilters("cityType");
        return filters;
    }

    public List<Filters> listSituation() {
        List<Filters> filters = getFilters("situation");
        return filters;
    }

    private List<Filters> getFilters(String filter) {
        List<Filters> filters = Lists.newArrayList();
        ListQuery query = new GenericQuery();
        List<SysDictVo> sysDicts = sqlSession.selectList("sysDict.selectList", query);
        List<SysDictVo> sysDictStream = sysDicts.stream().filter(o -> filter.equals(o.getDictType())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(sysDictStream)) {
            for (SysDictVo sysDict : sysDictStream
            ) {
                Filters filte = new Filters();
                filte.setCode(sysDict.getDictCode().toString());
                filte.setName(sysDict.getDictName());
                filters.add(filte);
            }
        }
        return filters;
    }
}
