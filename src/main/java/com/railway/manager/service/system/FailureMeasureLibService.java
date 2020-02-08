package com.railway.manager.service.system;

import com.google.common.collect.Maps;
import com.railway.manager.model.FailureMeasureLib;
import com.railway.manager.model.SubwayCarLib;
import com.railway.manager.service.AbstractService;
import com.railway.manager.utils.ResultMapUtil;
import com.railway.manager.vo.FailureMeasureLibVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description:
 * @author: chenglin
 * @create: 2019-12-29 10:33
 **/
@Service
public class FailureMeasureLibService extends AbstractService {

    /**
     * Description: 添加故障措施库
     *
     * @param failureMeasureLib
     * @return
     */
    public int add(FailureMeasureLib failureMeasureLib) {
        return sqlSession.insert("failureMeasureLib.insert", failureMeasureLib);
    }

    /**
     * Description: 查找故障措施库
     *
     * @param map
     * @return
     */
    public List<FailureMeasureLibVo> getList(Map<String, Object> map) {
        return sqlSession.selectList("failureMeasureLib.selectList", map);
    }

    /**
     * Description: 计算符合要求的数据量
     *
     * @param map
     * @return
     */
    public int selectCount(Map<String, Object> map) {
        return sqlSession.selectOne("failureMeasureLib.selectCount", map);
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public Map<String, Object> delete(Integer id) {

        //返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("idEqual", id.intValue());

        if (selectCount(conditionMap) < 1) {
            resultMap.put("code", "201");
            resultMap.put("description", "根据id查不到数据，禁止删除");

            return resultMap;
        }

        int count = sqlSession.delete("failureMeasureLib.delete", conditionMap);

        resultMap = ResultMapUtil.getDeleteResult(count);
        return resultMap;
    }

    /**
     * 修改信息
     *
     * @param failureMeasureLib
     * @return
     */
    public int editSubwayCarLib(FailureMeasureLib failureMeasureLib) {
        return sqlSession.update("failureMeasureLib.update", failureMeasureLib);
    }
}