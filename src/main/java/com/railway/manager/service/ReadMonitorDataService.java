package com.railway.manager.service;

import com.railway.manager.common.query.GenericQuery;
import com.railway.manager.common.query.ListQuery;
import com.railway.manager.model.MonitorData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: railway-manager
 * @description: 读取核心数据
 * @author: lijiwen
 * @create: 2019-11-13 20:52
 **/
@Service
public class ReadMonitorDataService extends AbstractService {

    public List<MonitorData> listAllData(String factory, String cityId, String line, String status) {
        ListQuery query =new GenericQuery() ;
        query.fill("factory", factory)
                .fill("cityId", cityId)
                .fill("line", line)
                .fill("status", status)
                 .fill("diviceId","2105030125FFFF");
        return sqlSession.selectList("monitorData.selectAll", query);
    }
}
