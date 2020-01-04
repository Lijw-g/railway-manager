package com.railway.manager.service;

import com.google.common.collect.Maps;
import com.railway.manager.common.query.GenericQuery;
import com.railway.manager.common.query.ListQuery;
import com.railway.manager.model.MonitorData;
import com.railway.manager.model.ReferenceData;
import com.railway.manager.utils.DataUtil;
import com.railway.manager.vo.CoreDataVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: railway-manager
 * @description: 读取核心数据
 * @author: lijiwen
 * @create: 2019-11-13 20:52
 **/
@Service
public class ReadMonitorDataService extends AbstractService {
    ListQuery query = new GenericQuery();

    public List<MonitorData> listAllData(String factory, String cityId, String line, String situation, Integer pageNum, Integer pageSize, String beginTime, String enTime) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        query.fill("_limit", pageSize);
        query.fill("cityId", cityId);
        query.fill("line", line);
        query.fill("_offset", (pageNum.intValue() - 1) * pageSize.intValue());
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(enTime)) {
            query.fill("beginTime", beginTime);
            query.fill("endTime", enTime);
        }
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        //车次
        listAllData.stream().forEach(o -> o.setLine(getRailwayShift(o.getDeviceId())));
        return listAllData;
    }

    public List<com.railway.manager.model.excel.MonitorData> listAllData() {
        ListQuery query = new GenericQuery();
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String beginTime = simpleDateFormat.format(dayStart);

        //第二天的开始时间 yyyy:MM:dd 00:00:00
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayEnd = calendar.getTime();
        String endTime = simpleDateFormat.format(dayEnd);

        query.fill("beginTime", beginTime);
        query.fill("endTime", endTime);
        return sqlSession.selectList("monitorDataExcel.list", query);
    }

    public int getCount(String factory, String cityId, String line, String situation, Integer pageNum, Integer pageSize, String beginTime, String enTime) {
        query.fill("cityId", cityId);
        query.fill("line", line);
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(enTime)) {
            query.fill("beginTime", beginTime);
            query.fill("endTime", enTime);
        }
        return sqlSession.selectOne("monitorData.selectCount", query);
    }

    public CoreDataVo listMvState(String factory, String city, String line, String situation) {
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllDatas = sqlSession.selectList("monitorData.selectAll", query);
        if (listAllDatas.size() > 20) {
            coreDataVo.setCoreData(listAllDatas.stream().map(MonitorData::getmVstate).collect(Collectors.toList()).subList(0, 20));
        } else {
            coreDataVo.setCoreData(listAllDatas.stream().map(MonitorData::getmVstate).collect(Collectors.toList()));
        }
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("110V")
                .setNormal_value("60V--80V")
                .setTrue_value("94V")
                .setMax_value("125V")
                .setAverage_value("77V")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        int count = sqlSession.selectOne("monitorData.selectCount", query);
        coreDataVo.setCount(count);
        return coreDataVo;
    }

    public CoreDataVo listMAState(String factory, String city, String line, String situation) {
        filters(city, line, query);
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        if (20 > listAllData.size()) {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getmAstate).collect(Collectors.toList()).subList(0, 20));
        } else {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getmAstate).collect(Collectors.toList()));
        }
        ReferenceData referenceData =
                new ReferenceData().setReference_max_value("3.5A")
                        .setNormal_value("1A--2.6")
                        .setTrue_value("2.2A")
                        .setMax_value("3.6")
                        .setAverage_value("1.5")
                        .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        int count = sqlSession.selectOne("monitorData.selectCount", query);
        coreDataVo.setCount(count);
        return coreDataVo;
    }

    public CoreDataVo listMTState(String factory, String city, String line, String situation) {
        filters(city, line, query);
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        if (20 > listAllData.size()) {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getmTstate).collect(Collectors.toList()).subList(0, 20));
        } else {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getmTstate).collect(Collectors.toList()));
        }
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("40℃--60℃")
                .setNormal_value("-20℃--80℃")
                .setTrue_value("66℃")
                .setMax_value("92℃")
                .setAverage_value("70℃")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        int count = sqlSession.selectOne("monitorData.selectCount", query);
        coreDataVo.setCount(count);
        return coreDataVo;
    }

    public CoreDataVo listDVState(String factory, String city, String line, String situation) {
        filters(city, line, query);
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        if (20 > listAllData.size()) {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getdVstate).collect(Collectors.toList()).subList(0, 20));
        } else {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getdVstate).collect(Collectors.toList()));
        }
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("3.5A")
                .setNormal_value("1A--2.6A")
                .setTrue_value("2.2A")
                .setMax_value("3.6A")
                .setAverage_value("1.5A")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        int count = sqlSession.selectOne("monitorData.selectCount", query);
        coreDataVo.setCount(count);
        return coreDataVo;
    }

    public CoreDataVo listDAState(String factory, String city, String line, String situation) {
        filters(city, line, query);
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        if (listAllData.size() > 20) {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getdAstate).collect(Collectors.toList()).subList(0, 20));
        } else {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getdAstate).collect(Collectors.toList()));
        }
        ReferenceData referenceData =
                new ReferenceData().setReference_max_value("3.8A")
                        .setNormal_value("1A--2.6")
                        .setTrue_value("2.2A")
                        .setMax_value("3.6")
                        .setAverage_value("1.5")
                        .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        int count = sqlSession.selectOne("monitorData.selectCount", query);
        coreDataVo.setCount(count);
        return coreDataVo;
    }

    public CoreDataVo listDTState(String factory, String city, String line, String situation) {
        filters(city, line, query);
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        if (listAllData.size() > 20) {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getdTstate).collect(Collectors.toList()).subList(0, 20));
        } else {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getdTstate).collect(Collectors.toList()));
        }
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("44℃--66℃")
                .setNormal_value("-20℃--80℃")
                .setTrue_value("66℃")
                .setMax_value("92℃")
                .setAverage_value("70℃")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        int count = sqlSession.selectOne("monitorData.selectCount", query);
        coreDataVo.setCount(count);
        return coreDataVo;
    }

    public CoreDataVo listDegree(String factory, String city, String line, String situation) {
        filters(city, line, query);
        CoreDataVo coreDataVo = new CoreDataVo();
        List<MonitorData> listAllData = sqlSession.selectList("monitorData.selectAll", query);
        if (listAllData.size() > 20) {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getDegree).collect(Collectors.toList()).subList(0, 20));
        } else {
            coreDataVo.setCoreData(listAllData.stream().map(MonitorData::getDegree).collect(Collectors.toList()));
        }
        ReferenceData referenceData = new ReferenceData()
                .setReference_max_value("0CM--150CM")
                .setNormal_value("170CM")
                .setTrue_value("140CM")
                .setMax_value("165CM")
                .setAverage_value("140CM")
                .setMax_value_time("2019-12-22 18:28:129");
        coreDataVo.setReferenceData(referenceData);
        int count = sqlSession.selectOne("monitorData.selectCount", query);
        coreDataVo.setCount(count);
        return coreDataVo;
    }

    private void filters(String city, String line, ListQuery query) {
        int pageSize = 20;
        int pageNum = 1;
        if (StringUtils.isNotEmpty(city)) {
            query.fill("city", city);
        }
        if (StringUtils.isNotEmpty(line)) {
            query.fill("line", line);
        }
        query.fill("_limit", pageSize);
        query.fill("_offset", (pageNum - 1) * pageSize);
    }

    private String getRailwayShift(String deviceId) {
        if (!Objects.isNull(deviceId) && deviceId.length() > 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("G");
            sb.append(Long.parseLong(deviceId.substring(0, 2), 16));
            sb.append(Long.parseLong(deviceId.substring(2, 4), 16));
            return sb.toString();
        } else {
            logger.warn("deviceId info is error :" + deviceId);
            return "";
        }
    }
}
