package com.railway.manager.service;

import com.railway.manager.model.GeographicalData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: railway-manager
 * @description: 地理位置信息
 * @author: lijiwen
 * @create: 2019-11-16 21:52
 **/
@Service
public class GeoraphicalDataService extends AbstractService {

    public List<GeographicalData> GeoraphicalData() {
        return sqlSession.selectList("geographicalData.select");
    }
}
