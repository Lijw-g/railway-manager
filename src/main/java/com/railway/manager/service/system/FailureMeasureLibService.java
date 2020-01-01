package com.railway.manager.service.system;

import com.railway.manager.model.FailureMeasureLib;
import com.railway.manager.service.AbstractService;
import com.railway.manager.vo.FailureMeasureLibVo;
import org.springframework.stereotype.Service;

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
     * @param failureMeasureLib
     * @return
     */
    public int add(FailureMeasureLib failureMeasureLib) {
        return sqlSession.insert("failureMeasureLib.insert", failureMeasureLib);
    }

    /**
     * Description: 查找故障措施库
     * @param map
     * @return
     */
    public List<FailureMeasureLibVo> getList(Map<String,Object> map) {
        return sqlSession.selectList("failureMeasureLib.selectList", map);
    }

    /**
     * Description: 计算符合要求的数据量
     * @param map
     * @return
     */
    public int selectCount(Map<String,Object> map) {
        return sqlSession.selectOne("failureMeasureLib.selectCount", map);
    }
}