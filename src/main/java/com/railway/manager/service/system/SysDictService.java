package com.railway.manager.service.system;

import com.google.common.collect.Maps;
import com.railway.manager.Enum.ConstantEnum;
import com.railway.manager.entity.SysDictAdd;
import com.railway.manager.model.SysDict;
import com.railway.manager.service.AbstractService;
import com.railway.manager.utils.ResultMapUtil;
import com.railway.manager.vo.SysDictVo;
import org.apache.commons.lang3.StringUtils;
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

    /***
     * @author: chenglin
     * Description: 添加字典数据
     * @param sysDictAdd
     * @return map
     * @createDate
     **/
    public Map<String, Object> add(SysDictAdd sysDictAdd) {

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();

        //检查dictType是否正确
        if(StringUtils.isBlank(sysDictAdd.getDictType())) {
            resultMap.put("code", "201");
            resultMap.put("description", "字典类型不能为空");

            return resultMap;
        }
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("dictTypeEqual", ConstantEnum.dict_type);
        conditionMap.put("dictNameEqual", sysDictAdd.getDictType().trim());

        List<SysDictVo> dictVoList = getList(conditionMap);

        if(dictVoList==null || dictVoList.size()<1) {
            resultMap.put("code", "202");
            resultMap.put("description", "字典类型不存在");

            return resultMap;
        } else if(dictVoList.size() > 1) {
            resultMap.put("code", "203");
            resultMap.put("description", "存在多种字典类型，请检查字典数据表");

            return resultMap;
        }

        //检查字典编码是否已被使用
        if(sysDictAdd.getDictCode() == null) {
            resultMap.put("code", "301");
            resultMap.put("description", "字典编码不能为空");

            return resultMap;
        }

        conditionMap.clear();
        conditionMap.put("dictTypeEqual", sysDictAdd.getDictType().trim());
        conditionMap.put("dictCodeEqual", sysDictAdd.getDictCode().intValue());

        int countCondition = selectCount(conditionMap);

        if(countCondition > 0) {
            resultMap.put("code", "302");
            resultMap.put("description", "字典编码已被使用");

            return resultMap;
        }

        //检查字典名称是否为空
        if(StringUtils.isBlank(sysDictAdd.getDictName())) {
            resultMap.put("code", "303");
            resultMap.put("description", "字典名称不能为空");

            return resultMap;
        }

        //检查状态是否正确
        if(sysDictAdd.getStatus() == null) {
            resultMap.put("code", "304");
            resultMap.put("description", "状态编码不能为空");

            return resultMap;
        }

        if(sysDictAdd.getStatus().intValue()!=ConstantEnum.status_no
                && sysDictAdd.getStatus().intValue()!=ConstantEnum.status_yes) {
            resultMap.put("code", "305");
            resultMap.put("description", "状态编码不符合要求");

            return resultMap;
        }

        SysDict dict = new SysDict(sysDictAdd);
        dict.setDescription(dictVoList.get(0).getDescription());

        int countAdd = sqlSession.insert("sysDict.insert", dict);

        resultMap = ResultMapUtil.getAddResult(countAdd);

        return resultMap;
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
    public List<SysDictVo> getList(Map<String,Object> map) {
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