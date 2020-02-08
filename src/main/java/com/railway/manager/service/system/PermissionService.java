package com.railway.manager.service.system;

import com.railway.manager.Enum.ConstantEnum;
import com.railway.manager.model.Permission;
import com.railway.manager.service.AbstractService;
import com.railway.manager.vo.PermissionVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description: 权限信息业务处理服务类
 * @author: chenglin
 * @create: 2020-01-04 21:00
 **/
@Service
public class PermissionService extends AbstractService {

    /**
     * @author: chenglin
     * Description: 获取权限列表数据
     **/
    public Map<String, Object> getPermissionResult() {

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("menuTypeCodeEqual", ConstantEnum.menuType_dir);

        //目录数据
        int dirCount = sqlSession.selectOne("permission.selectCount", conditionMap);
        List<PermissionVo> permissionDirList = sqlSession.selectList("permission.selectList", conditionMap);

        for (PermissionVo permissionDir : permissionDirList) {
            conditionMap.clear();
            conditionMap.put("parentIdEqual", permissionDir.getId());
            conditionMap.put("menuTypeCodeEqual", ConstantEnum.menuType_menu);

            //菜单数据
            List<PermissionVo> permissionMenuList = sqlSession.selectList("permission.selectList", conditionMap);

            for (PermissionVo permissionMenu : permissionMenuList) {
                conditionMap.clear();
                conditionMap.put("parentIdEqual", permissionMenu.getId());
                conditionMap.put("menuTypeCodeEqual", ConstantEnum.menuType_btn);

                //按钮数据
                List<PermissionVo> permissionBtnList = sqlSession.selectList("permission.selectList", conditionMap);

                permissionMenu.setPermissionList(permissionBtnList);
            }

            permissionDir.setPermissionList(permissionMenuList);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", dirCount);
        resultMap.put("currentCount", permissionDirList.size());
        resultMap.put("permissionDirList", permissionDirList);

        return resultMap;
    }

    /**
     * @param roleCode
     * @author: chenglin
     * Description: 根据角色编码获取权限信息
     **/
    public Map<String, Object> getlistByCode(String roleCode) {

        List<Map<String, Object>> resultMapList = sqlSession.selectList("rolePermission.getlistByCode", roleCode);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", resultMapList.size());
        resultMap.put("currentCount", resultMapList.size());
        resultMap.put("resultMapList", resultMapList);

        return resultMap;
    }

    /**
     * @param conditionMap
     * @return int
     * @author: chenglin
     * Description: 计算符合要求的数据量
     **/
    public int selectCount(Map<String, Object> conditionMap) {
        return sqlSession.selectOne("permission.selectCount", conditionMap);
    }

    /**
     * @param permission
     * @return int
     * @author: chenglin
     * Description: 添加权限信息
     **/
    public int add(Permission permission) {
        return sqlSession.insert("permission.insert", permission);
    }

    /**
     * Description: 删除权限信息
     *
     * @param id
     * @return
     */
    public int delete(Integer id) {
        return sqlSession.delete("permission.delete", id);
    }

    /**
     * @param permission
     * @return int
     * @author: chenglin
     * Description: 更新权限信息
     **/
    public int update(Permission permission) {
        return sqlSession.update("permission.update", permission);
    }
}