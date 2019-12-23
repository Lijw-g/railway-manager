package com.railway.manager.service.system;

import com.railway.manager.model.Role;
import com.railway.manager.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Role> listRole(Map<String, Object> conditionMap) {
        return sqlSession.selectList("role.select", conditionMap);

    }

    public int add(Role role) {
        return sqlSession.insert("role.insert", role);
    }

    public int editUser(Role role) {
        role.setUpdatedTime(new Date());
        return sqlSession.update("role.update", role);
    }

    public int delRole(Role role) {
        return sqlSession.delete("role.delete", role);
    }
}
