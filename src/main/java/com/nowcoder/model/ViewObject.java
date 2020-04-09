package com.nowcoder.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther：侯赛音
 * @Date：2020/4/9 0009
 * @Description:
 * @Version:1.0
 */
public class ViewObject {
    Map<String,Object> objs=new HashMap<>();
    public void setObjs(String key,Object value){
        objs.put(key,value);
    }
    public Object get(String key){
        return objs.get(key);
    }
}
