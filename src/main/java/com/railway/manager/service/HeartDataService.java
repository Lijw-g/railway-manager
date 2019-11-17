package com.railway.manager.service;

import com.railway.manager.common.query.GenericQuery;
import com.railway.manager.common.query.ListQuery;
import com.railway.manager.model.HeartData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: railway-manager
 * @description: 获取心跳信息
 * @author: lijiwen
 * @create: 2019-11-16 21:34
 **/
@Service
public class HeartDataService extends AbstractService {
    public List<HeartData> getHeartData(String deviceId) {
        ListQuery query =new GenericQuery() ;
        query.fill("deviceId", deviceId);
        return sqlSession.selectList("heartData.select", query);
    }
}
