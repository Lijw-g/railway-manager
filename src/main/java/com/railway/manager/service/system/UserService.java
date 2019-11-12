package com.railway.manager.service.system;

import com.railway.manager.service.AbstractService;
import com.railway.manager.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> getList() {
        return sqlSession.selectList("user.select", null);
    }
}
