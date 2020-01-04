package com.railway.manager.service.system;

import com.google.common.collect.Maps;
import com.railway.manager.entity.UserAdd;
import com.railway.manager.model.RoleUser;
import com.railway.manager.service.AbstractService;
import com.railway.manager.model.User;
import com.railway.manager.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description:
 * @author: lijiwen
 * @create: 2019-09-15 07:34
 **/
@Service
public class UserService extends AbstractService {

    /***
     * @author: chenglin
     * Description: 添加用户信息
     * @param userAdd
     * @return map
     * @createDate
     **/
    public Map<String, Object> add(UserAdd userAdd) {

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();

        //检查账号userName是否唯一
        if(StringUtils.isBlank(userAdd.getUserName())) {
            resultMap.put("code", "201");
            resultMap.put("description", "用户名不能为空");

            return resultMap;
        }
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("userNameEqual", userAdd.getUserName().trim());

        int count = sqlSession.selectOne("user.selectCount",conditionMap);

        if(count > 0) {
            resultMap.put("code", "202");
            resultMap.put("description", "用户名已存在");

            return resultMap;
        }

        //根据角色编码判断角色是否存在
        if(StringUtils.isBlank(userAdd.getRoleCode())) {
            resultMap.put("code", "301");
            resultMap.put("description", "角色编码不能为空");

            return resultMap;
        }

        userAdd.setRoleCode(userAdd.getRoleCode().trim());

        Map<String, Object> conditionRoleMap = new HashMap<String, Object>();
        conditionRoleMap.put("roleCodeEqual", userAdd.getRoleCode());
        int roleCount = sqlSession.selectOne("role.selectCount", conditionRoleMap);

        resultMap = checkRoleCount(roleCount);

        if(resultMap != null) {
            return resultMap;
        }
        resultMap = Maps.newHashMap();

        int countAdd = -1;
        User user = new User(userAdd);
        countAdd = sqlSession.insert("user.insert", user);

        //数据库中添加用户角色对应关系数据
        RoleUser roleUser = new RoleUser(userAdd);

        sqlSession.insert("roleUser.insert", roleUser);

        if(countAdd > 0) {
            resultMap.put("code", "200");
            resultMap.put("description", "添加成功");
        } else {
            resultMap.put("code", "203");
            resultMap.put("description", "添加失败");
        }

        return resultMap;
    }

    /***
     * @author: chenglin
     * Description: 添加用户信息
     * @param user
     * @return map
     * @createDate
     **/
    public int add(User user) {

        return sqlSession.insert("user.insert", user);
    }

    /***
     * @author: chenglin
     * Description: 修改用户信息
     * @param userAdd
     * @return map
     **/
    public Map<String, Object> editUser(UserAdd userAdd) {

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();

        //检查账号userName是否唯一
        if(StringUtils.isBlank(userAdd.getUserName())) {
            resultMap.put("code", "201");
            resultMap.put("description", "用户名不能为空");

            return resultMap;
        }
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("userNameEqual", userAdd.getUserName().trim());

        int count = sqlSession.selectOne("user.selectCount",conditionMap);

        if(count < 1) {
            resultMap.put("code", "202");
            resultMap.put("description", "用户名不存在，禁止修改");

            return resultMap;
        }

        if(count > 1) {
            resultMap.put("code", "203");
            resultMap.put("description", "用户名不唯一，禁止修改");

            return resultMap;
        }

        //检查用户对应角色信息
        if(StringUtils.isBlank(userAdd.getRoleCode())) {
            resultMap.put("code", "301");
            resultMap.put("description", "角色编码不能为空");

            return resultMap;
        }

        userAdd.setRoleCode(userAdd.getRoleCode().trim());

        Map<String, Object> conditionRoleMap = new HashMap<String, Object>();
        conditionRoleMap.put("roleCodeEqual", userAdd.getRoleCode());
        int roleCount = sqlSession.selectOne("role.selectCount", conditionRoleMap);

        resultMap = checkRoleCount(roleCount);

        if(resultMap != null) {
            return resultMap;
        }
        resultMap = Maps.newHashMap();

        int countUpdate = -1;
        User user = new User(userAdd);
        countUpdate = sqlSession.update("user.update", user);

        //删除角色用户配置信息，并添加新的
        conditionRoleMap.put("userNameEqual", userAdd.getUserName());
        sqlSession.delete("roleUser.delete", conditionRoleMap);
        //数据库中添加用户角色对应关系数据
        RoleUser roleUser = new RoleUser(userAdd);

        sqlSession.insert("roleUser.insert", roleUser);

        if(countUpdate > 0) {
            resultMap.put("code", "200");
            resultMap.put("description", "更新成功");
        } else {
            resultMap.put("code", "203");
            resultMap.put("description", "更新失败");
        }

        return resultMap;
    }

    /***
     * @author: chenglin
     * Description: 重置用户密码
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @param pwdConfirm
     * @return map
     **/
    public Map<String, Object> resetPassword(String userName, String oldPassword,
                                        String newPassword, String pwdConfirm) {

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();

        //检查账号userName是否唯一
        if(StringUtils.isBlank(userName)) {
            resultMap.put("code", "201");
            resultMap.put("description", "用户名不能为空");

            return resultMap;
        }
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("userNameEqual", userName.trim());

        int count = sqlSession.selectOne("user.selectCount",conditionMap);

        if(count < 1) {
            resultMap.put("code", "202");
            resultMap.put("description", "用户名不存在，禁止重置密码");

            return resultMap;
        }

        if(count > 1) {
            resultMap.put("code", "203");
            resultMap.put("description", "用户名不唯一，禁止重置密码");

            return resultMap;
        }

        //检查旧密码是否存在
        conditionMap.clear();
        conditionMap.put("userNameEqual", userName.trim());
        conditionMap.put("passwordEqual", oldPassword);

        int countPwd = sqlSession.selectOne("user.selectCount",conditionMap);
        if(countPwd != 1) {
            resultMap.put("code", "204");
            resultMap.put("description", "旧密码输入错误");

            return resultMap;
        }

        //检查新密码与确认密码是否一致
        if(StringUtils.isBlank(newPassword)) {
            resultMap.put("code", "205");
            resultMap.put("description", "新密码不能为空");

            return resultMap;
        }
        if(!newPassword.equals(pwdConfirm)) {
            resultMap.put("code", "206");
            resultMap.put("description", "新密码与确认密码不一致");

            return resultMap;
        }

        //更新密码
        int countUpdate = -1;
        User user = new User();
        user.setUserName(userName);
        user.setPassword(newPassword.trim());

        countUpdate = sqlSession.update("user.updatePwd", user);

        if(countUpdate > 0) {
            resultMap.put("code", "200");
            resultMap.put("description", "重置密码成功");
        } else {
            resultMap.put("code", "207");
            resultMap.put("description", "重置密码失败");
        }

        return resultMap;
    }

    public List<User> getList(Map<String,Object> map) {
        return sqlSession.selectList("user.selectList", map);
    }

    public List<UserVo> getList(Integer pageNum, Integer pageSize, String advanceColumn) {
        Map<String, Object> conditionMap = Maps.newHashMap();
        if(pageNum!=null && pageSize!=null) {
            conditionMap.put("_limit", pageSize);
            conditionMap.put("_offset", (pageNum.intValue()-1)*pageSize.intValue());
        }
        if (StringUtils.isNotEmpty(advanceColumn)) {
            conditionMap.put("advanceColumnLike", "%"+advanceColumn.trim()+"%");
        }
        return sqlSession.selectList("user.selectList", conditionMap);
    }
    /**
    * @author: Lijiwen
    * Description:
    * @param:  * @param pageNum
     * @param pageSize
     * @param name
    * @return int
    * @createDate 2019-12-29 14:54
    **/
    public int getCount(String advanceColumn) {
        Map<String, Object> conditionMap = Maps.newHashMap();
        if (StringUtils.isNotEmpty(advanceColumn)) {
            conditionMap.put("advanceColumnLike", "%"+advanceColumn.trim()+"%");
        }
        return sqlSession.selectOne("user.selectCount",conditionMap);
    }
    /**
     * Description: 检查用户名是否已注册
     * param userName
     * return boolean
     * @return
     */
    public boolean checkUserName(String userName) {

        //判断是否为空
        if(StringUtils.isBlank(userName)) {
            return false;
        }

        String userNameTrim = userName.trim();

        //检查用户名是否存在
        Map<String,String> map = new HashMap<String,String>();
        map.put("userNameEqual",userNameTrim);
        int count1 = sqlSession.selectOne("user.selectCount", map);
        if(count1 > 0) {
            return false;
        }

        map.clear();
        String userNameLower = userName.toLowerCase();
        map.put("userNameEqual",userNameLower);
        int count2 = sqlSession.selectOne("user.selectCount", map);
        if(count2 > 0) {
            return false;
        }

        map.clear();
        String userNameUpper = userName.toUpperCase();
        map.put("userNameEqual",userNameUpper);
        int count3 = sqlSession.selectOne("user.selectCount", map);
        if(count3 > 0) {
            return false;
        }

        return true;
    }

    private Map<String, Object> checkRoleCount(int roleCount) {

        Map<String, Object> resultMap = Maps.newHashMap();

        if(roleCount < 1) {
            resultMap.put("code", "302");
            resultMap.put("description", "角色编码对应角色不存在");
            return resultMap;
        } else if(roleCount > 1) {
            resultMap.put("code", "303");
            resultMap.put("description", "角色编码对应多个角色，请检查角色信息");
            return resultMap;
        }

        return null;
    }
}
