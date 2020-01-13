package com.railway.manager.service;

import com.google.common.collect.Maps;
import com.railway.manager.common.query.GenericQuery;
import com.railway.manager.common.query.ListQuery;
import com.railway.manager.model.GeographicalData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: railway-manager
 * @description: 地理位置信息
 * @author: lijiwen
 * @create: 2019-11-16 21:52
 **/
@Service
public class GeoraphicalDataService extends AbstractService {

    public List<GeographicalData> GeoraphicalData(Integer pageNum, Integer pageSize) {
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("_limit", pageSize);
        conditionMap.put("_offset", (pageNum.intValue() - 1) * pageSize.intValue());

        return sqlSession.selectList("geographicalData.select", conditionMap);
    }

    public Integer getCount() {
        ListQuery query = new GenericQuery();
        return sqlSession.selectOne("geographicalData.selectCount");
    }
}
