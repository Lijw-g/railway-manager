package com.railway.manager.service.system;

import com.railway.manager.model.DoorPlc;
import com.railway.manager.service.AbstractService;
import com.railway.manager.vo.DoorPlcVo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description:
 * @author: chenglin
 * @create: 2019-12-29 17:12
 **/
@Service
public class DoorPlcService extends AbstractService {

    /**
     * Description: 添加车门控制器数据
     *
     * @param doorPlc
     * @return
     */
    public int add(DoorPlc doorPlc) {
        return sqlSession.insert("doorPlc.insert", doorPlc);
    }

    /**
     * Description: 更新车门控制器数据
     *
     * @param doorPlc
     * @return
     */
    public int update(DoorPlc doorPlc) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("idEqual", doorPlc.getId().intValue());

        if (selectCount(conditionMap) != 1) {
            return -1;
        } else {
            doorPlc.setUpdateTime(new Date());
            return sqlSession.update("doorPlc.update", doorPlc);
        }
    }

    /**
     * Description: 删除车门控制器数据
     *
     * @param id
     * @return
     */
    public int delete(Integer id) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("idEqual", id.intValue());
        if (selectCount(conditionMap) != 1) {
            return -1;
        } else {
            return sqlSession.delete("doorPlc.delete", conditionMap);
        }
    }

    /**
     * Description: 查找车门控制器数据
     *
     * @param map
     * @return
     */
    public List<DoorPlcVo> getList(Map<String, Object> map) {
        return sqlSession.selectList("doorPlc.selectList", map);
    }

    /**
     * Description: 计算符合要求的数据量
     *
     * @param map
     * @return
     */
    public int selectCount(Map<String, Object> map) {
        return sqlSession.selectOne("doorPlc.selectCount", map);
    }
}