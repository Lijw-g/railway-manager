package com.railway.manager.service.system;

import com.railway.manager.model.SubwayCarLib;
import com.railway.manager.service.AbstractService;
import com.railway.manager.vo.SubwayCarLibVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description:
 * @author: chenglin
 * @create: 2019-12-29 11:22
 **/
@Service
public class SubwayCarLibService extends AbstractService {

    /**
     * Description: 添加轨道交通车辆型号库
     * @param subwayCarLib
     * @return
     */
    public int add(SubwayCarLib subwayCarLib) {
        return sqlSession.insert("subwayCarLib.insert", subwayCarLib);
    }

    /**
     * Description: 查找轨道交通车辆型号库
     * @param map
     * @return
     */
    public List<SubwayCarLibVo> getList(Map<String,Object> map) {
        return sqlSession.selectList("subwayCarLib.selectList", map);
    }

    /**
     * Description: 计算符合要求的数据量
     * @param map
     * @return
     */
    public int selectCount(Map<String,Object> map) {
        return sqlSession.selectOne("subwayCarLib.selectCount", map);
    }
}