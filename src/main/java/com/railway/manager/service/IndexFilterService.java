package com.railway.manager.service;

import com.google.common.collect.Lists;
import com.railway.manager.common.query.GenericQuery;
import com.railway.manager.common.query.ListQuery;
import com.railway.manager.entity.SysDict;
import com.railway.manager.model.Filters;
import com.railway.manager.model.MonitorData;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: railway-manager
 * @description:
 * @author: lijiwen
 * @create: 2019-12-28 16:46
 **/
@Service
public class IndexFilterService extends  AbstractService {
    /**
    * @author: Lijiwen
    * Description:
    * @param:  * @param
    * @return java.util.List<com.railway.manager.model.Filters>
    * @createDate 2019-12-28 16:48
    **/
    public List<Filters> listFactory() {
        List<Filters> filters = getFilters("factory");
        return filters;

    }

    public List<Filters> listLine() {
        List<Filters> filters = getFilters("line");
        return filters;
    }

    public List<Filters> listCity() {
        List<Filters> filters = getFilters("city");
        return filters;
    }

    public List<Filters> listSituation() {
        List<Filters> filters = getFilters("situation");
        return filters;
    }

    private List<Filters> getFilters(String filter) {
        List<Filters> filters = Lists.newArrayList();
        ListQuery query = new GenericQuery();
        List<SysDict> sysDicts = sqlSession.selectList("sysDict.selectList", query);
        List<SysDict> sysDictStream = sysDicts.stream().filter(o -> filter.equals(o.getDictType())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(sysDictStream)) {
            for (SysDict sysDict:sysDictStream
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