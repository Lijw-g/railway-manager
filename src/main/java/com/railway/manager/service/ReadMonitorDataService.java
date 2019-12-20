package com.railway.manager.service;

import com.railway.manager.common.query.GenericQuery;
import com.railway.manager.common.query.ListQuery;
import com.railway.manager.model.MonitorData;
import com.railway.manager.model.ReferenceData;
import com.railway.manager.utils.DateUtil;
import com.railway.manager.vo.CoreDataVo;
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

    public CoreDataVo listMvState() {
        ListQuery query = new GenericQuery();
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getmVstate).collect(Collectors.toList()));
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("110V")
                .setNormal_value("60V--80V")
                .setTrue_value("94V")
                .setMax_value("125V")
                .setAverage_value("77V")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        return coreDataVo;
    }

    public CoreDataVo listMAState() {
        ListQuery query = new GenericQuery();
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getmAstate).collect(Collectors.toList()));
        ReferenceData referenceData =
                new ReferenceData().setReference_max_value("3.5A")
                        .setNormal_value("1A--2.6")
                        .setTrue_value("2.2A")
                        .setMax_value("3.6")
                        .setAverage_value("1.5")
                        .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        return coreDataVo;
    }

    public CoreDataVo listMTState() {
        ListQuery query = new GenericQuery();
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getmTstate).collect(Collectors.toList()));
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("40℃--60℃")
                .setNormal_value("-20℃--80℃")
                .setTrue_value("66℃")
                .setMax_value("92℃")
                .setAverage_value("70℃")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        return coreDataVo;
    }

    public CoreDataVo listDVState() {
        ListQuery query = new GenericQuery();
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
       coreDataVo.setCoreData( listAllData.stream().map(MonitorData::getdVstate).collect(Collectors.toList()));
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("3.5A")
                .setNormal_value("1A--2.6A")
                .setTrue_value("2.2A")
                .setMax_value("3.6A")
                .setAverage_value("1.5A")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        return coreDataVo;
    }

    public CoreDataVo listDAState() {
        ListQuery query = new GenericQuery();
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getdAstate).collect(Collectors.toList()));
        ReferenceData referenceData =
                new ReferenceData().setReference_max_value("3.8A")
                        .setNormal_value("1A--2.6")
                        .setTrue_value("2.2A")
                        .setMax_value("3.6")
                        .setAverage_value("1.5")
                        .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        return coreDataVo;
    }

    public CoreDataVo listDTState() {
        ListQuery query = new GenericQuery();
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getdTstate).collect(Collectors.toList()));
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("44℃--66℃")
                .setNormal_value("-20℃--80℃")
                .setTrue_value("66℃")
                .setMax_value("92℃")
                .setAverage_value("70℃")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        return coreDataVo;
    }

    public CoreDataVo listDegree() {
        ListQuery query = new GenericQuery();
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getDegree).collect(Collectors.toList()));
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("0CM--150CM")
                .setNormal_value("170CM")
                .setTrue_value("140CM")
                .setMax_value("165CM")
                .setAverage_value("140CM")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        return coreDataVo;
    }
}
