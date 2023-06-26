package com.forestchiefmanagementsystem.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forestchiefmanagementsystem.pojo.Task;
import com.forestchiefmanagementsystem.pojo.dto.Dto;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DtoUtil<T,M> {
    @FunctionalInterface
    public interface GetId<T>{
        public String getId(T t);
    }
    public static <T,M> ArrayList<Dto> getDtoList(List<T> list1,GetId<T> id1,List<M> list2,GetId<M> id2){
        ArrayList<Dto> dtoList = new ArrayList<>();
        for (T t : list1) {
            Dto dto = new Dto();
            HashMap<String, Object> dtoMap = dto.getDtoMap();
            dtoMap.put(t.getClass().getSimpleName(),t);
            for (M m : list2) {
                if (id1.getId(t).equals(id2.getId(m))){
                    dtoMap.put(m.getClass().getSimpleName(),m);
                }
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static <T,M> ArrayList<Dto> getDtoList(ArrayList<Dto> dtoList,Class<T> tClass,GetId<T> id1,List<M> list,GetId<M> id2){
        for (Dto dto : dtoList) {
            HashMap<String, Object> dtoMap = dto.getDtoMap();
            T o = (T)dtoMap.get(tClass.getSimpleName());
            for (M m : list) {
                if (id1.getId(o).equals(id2.getId(m))){
                    dtoMap.put(m.getClass().getSimpleName(),m);
                }
            }
        }
        return dtoList;
    }
}
