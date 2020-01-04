package com.railway.manager.service.system;

import com.railway.manager.model.Role;
import com.railway.manager.service.AbstractService;
import com.railway.manager.vo.RoleVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description: 角色表
 * @author: lijiwen
 * @create: 2019-10-20 14:24
 **/
@Service
public class UserRoleService  extends AbstractService {

    /**
     * Description: 查找角色数据
     * @param conditionMap
     * @return
     */
    public List<RoleVo> getList(Map<String, Object> conditionMap) {
        return sqlSession.selectList("role.selectList", conditionMap);
    }

    /**
     * Description: 添加角色信息
     * @param role
     * @return
     */
    public int add(Role role) {
        return sqlSession.insert("role.insert", role);
    }

    public int editRole(Role role) {
        return sqlSession.update("role.update", role);
    }

    /**
     * Description: 删除角色信息
     * @param roleCode
     * @return
     */
    public int deleteRole(String roleCode) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("roleCodeEqual", roleCode);
        if(selectCount(conditionMap) != 1) {
            return -1;
        } else {
            return sqlSession.delete("role.delete", roleCode);
        }
    }

    /**
     * Description: 计算符合要求的数据量
     * @param map
     * @return
     */
    public int selectCount(Map<String,Object> map) {
        return sqlSession.selectOne("role.selectCount", map);
    }
}
