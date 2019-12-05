package com.railway.manager.service.system;

import com.railway.manager.entity.SysDict;
import com.railway.manager.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description: 字典服务
 * @author: chenglin
 * @create: 2019-12-06 00:01
 **/
@Service
public class SysDictService extends AbstractService {

    /**
     * Description: 添加字典数据
     * @param sysDict
     * @return
     */
    public int add(SysDict sysDict) {
        return sqlSession.insert("sysDict.insert", sysDict);
    }

    /**
     * Description: 更新字典数据
     * @param sysDict
     * @return
     */
    public int update(SysDict sysDict) {
        return sqlSession.update("sysDict.update", sysDict);
    }

    /**
     * Description: 查找字典数据
     * @param map
     * @return
     */
    public List<SysDict> getList(Map<String,Object> map) {
        return sqlSession.selectList("sysDict.selectList", map);
    }

    /**
     * Description: 计算符合要求的数据量
     * @param map
     * @return
     */
    public int selectCount(Map<String,Object> map) {
        return sqlSession.selectOne("sysDict.selectCount", map);
    }
}