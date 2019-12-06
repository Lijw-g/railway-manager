package com.railway.manager.service;

import com.railway.manager.common.query.GenericQuery;
import com.railway.manager.common.query.ListQuery;
import com.railway.manager.model.MonitorData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program: railway-manager
 * @description: 读取核心数据
 * @author: lijiwen
 * @create: 2019-11-13 20:52
 **/
@Service
public class ReadMonitorDataService extends AbstractService {

    public List<MonitorData> listAllData(String factory, String cityId, String line, String status) {
        ListQuery query = new GenericQuery();
        query.fill("factory", factory)
                .fill("cityId", cityId)
                .fill("line", line)
                .fill("status", status)
                .fill("diviceId", "2105030125FFFF");
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        //车次
        listAllData.stream().forEach(o -> o.setCityCode(getRailwayShift(o.getDeviceId())));
        return listAllData;
    }

    private String getRailwayShift(String deviceId) {
        if (!Objects.isNull(deviceId) && deviceId.length() > 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("G");
            sb.append(Long.parseLong(deviceId.substring(0, 2), 16));
            sb.append(Long.parseLong(deviceId.substring(2, 4), 16));
            return sb.toString();
        } else {
            logger.warn("devide info is error :" + deviceId);
            return "";
        }
    }

    public List<String> listMvState() {
        ListQuery query = new GenericQuery();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);

        return listAllData.stream().map(MonitorData::getmVstate).collect(Collectors.toList());
    }

    public List<String> listMAState() {
        ListQuery query = new GenericQuery();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        return listAllData.stream().map(MonitorData::getmAstate).collect(Collectors.toList());
    }

    public List<String> listMTState() {
        ListQuery query = new GenericQuery();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        return listAllData.stream().map(MonitorData::getmTstate).collect(Collectors.toList());
    }

    public List<String> listDVState() {
        ListQuery query = new GenericQuery();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        return listAllData.stream().map(MonitorData::getdVstate).collect(Collectors.toList());
    }

    public List<String> listDAState() {
        ListQuery query = new GenericQuery();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        return listAllData.stream().map(MonitorData::getdAstate).collect(Collectors.toList());
    }

    public List<String> listDTState() {
        ListQuery query = new GenericQuery();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        return listAllData.stream().map(MonitorData::getdTstate).collect(Collectors.toList());
    }

    public List<String> listDegree() {
        ListQuery query = new GenericQuery();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        return listAllData.stream().map(MonitorData::getDegree).collect(Collectors.toList());
    }
}
