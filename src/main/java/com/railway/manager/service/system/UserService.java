package com.railway.manager.service.system;

import com.google.common.collect.Maps;
import com.railway.manager.service.AbstractService;
import com.railway.manager.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
     * @author: Lijiwen
     * Description:
     * @param user
     * @return java.lang.String
     * @createDate
     **/
    public int add(User user) {
        return sqlSession.insert("user.insert", user);
    }

    public int editUser(User user) {
        return sqlSession.update("user.update", user);
    }

    public List<User> getList(Map<String,Object> map) {
        return sqlSession.selectList("user.selectList", map);
    }

    public List<User> getList(Integer pageNum, Integer pageSize) {
        Map<String, Object> conditionMap = Maps.newHashMap();
        if(pageNum!=null && pageSize!=null) {
            conditionMap.put("_limit", pageSize);
            conditionMap.put("_offset", (pageNum.intValue()-1)*pageSize.intValue());
        }
        return sqlSession.selectList("user.selectList", conditionMap);
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
        map.put("name",userNameTrim);
        int count1 = sqlSession.selectOne("user.selectCount", map);
        if(count1 > 0) {
            return false;
        }

        map.clear();
        String userNameLower = userName.toLowerCase();
        map.put("name",userNameLower);
        int count2 = sqlSession.selectOne("user.selectCount", map);
        if(count2 > 0) {
            return false;
        }

        map.clear();
        String userNameUpper = userName.toUpperCase();
        map.put("name",userNameUpper);
        int count3 = sqlSession.selectOne("user.selectCount", map);
        if(count3 > 0) {
            return false;
        }

        return true;
    }
}
