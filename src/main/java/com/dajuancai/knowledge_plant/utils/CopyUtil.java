package com.dajuancai.knowledge_plant.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CopyUtil {
   /*
   * 单体复制
   * */

    public static <T> T copy (Object source , Class<T> tClass) {
        if (source == null) return  null;
        T obj = null;
        try {
            obj = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source,obj);
        return obj;
    }

    /*
    * 列表复制
    * */

    public static <T> List<T> copyList(List source , Class<T> tClass) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)) {
            for (Object c : source) {
                T obj = copy(c,tClass);
                target.add(obj);
            }
        }
        return target;
    }
}
