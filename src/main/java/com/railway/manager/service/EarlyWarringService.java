package com.railway.manager.service;

import com.google.common.collect.Lists;
import com.railway.manager.common.query.GenericQuery;
import com.railway.manager.common.query.ListQuery;
import com.railway.manager.model.EarlyWarring;
import com.railway.manager.model.MonitorData;
import com.railway.manager.utils.DataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EarlyWarringService extends AbstractService {
    public List<EarlyWarring> listAllData(Integer pageNum, Integer pageSize, String searchParam) {
        ListQuery query = new GenericQuery();
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (StringUtils.isNotEmpty(searchParam)) {
            query.fill("searchParam", searchParam);
        }
        query.fill("_limit", pageSize);
        query.fill("_offset", (pageNum.intValue() - 1) * pageSize.intValue());
        List<EarlyWarring> listAllData = sqlSession.selectList("earlyWarring.list", query);
        //车次
        return listAllData;
    }

    public int getCount(String searchParam) {
        ListQuery query = new GenericQuery();
        if (StringUtils.isNotEmpty(searchParam)) {
            query.fill("searchParam", searchParam);
        }
        return sqlSession.selectOne("earlyWarring.selectCount", query);
    }

    public List<EarlyWarring> getNew() {
        ListQuery query = new GenericQuery();
        query.fill("isNew", 1);
        return sqlSession.selectList("earlyWarring.list", query);
    }

    public int setOld(String id) {
        ListQuery query = new GenericQuery();
        query.fill("id", id);
        return sqlSession.update("earlyWarring.update", query);
    }

    public int setBatchOld(String ids) {
        List updateIds = Lists.newArrayList();
        String[] split = ids.split(",");
        updateIds = Arrays.asList(split);
        return sqlSession.update("earlyWarring.updates", updateIds);
    }
}
