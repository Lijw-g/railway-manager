package com.railway.manager.service.system;

import com.railway.manager.model.FailureAnalysis;
import com.railway.manager.service.AbstractService;
import com.railway.manager.vo.FailureAnalysisVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description:
 * @author: chenglin
 * @create: 2019-12-04 22:11
 **/
@Service
public class FailureAnalysisService extends AbstractService {

    /**
     * Description: 添加故障分析结果
     * @param failureAnalysis
     * @return
     */
    public int add(FailureAnalysis failureAnalysis) {
        return sqlSession.insert("failureAnalysis.insert", failureAnalysis);
    }

    /**
     * Description: 查找故障分析数据
     * @param map
     * @return
     */
    public List<FailureAnalysisVo> getList(Map<String,Object> map) {
        return sqlSession.selectList("failureAnalysis.selectList", map);
    }

    /**
     * Description: 计算符合要求的数据量
     * @param map
     * @return
     */
    public int selectCount(Map<String,Object> map) {
        return sqlSession.selectOne("failureAnalysis.selectCount", map);
    }
}