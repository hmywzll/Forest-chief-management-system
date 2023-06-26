package com.forestchiefmanagementsystem.util;

import java.util.Map;

public class ThreadUtil {
    /**
     * 在线程中存储和取出EmployeeId和EmployeePosition
     */
    private static ThreadLocal<String> PIThread_id =new ThreadLocal<>();
    private static ThreadLocal<Integer> PIThread_position =new ThreadLocal<>();
    public static void setPIId(String id){
        PIThread_id.set(id);
    }
    public static String getPIId(){
        return PIThread_id.get();
    }
    public static void setPIPosition(Integer position){
        PIThread_position.set(position);
    }
    public static Integer getPIPosition(){
        return PIThread_position.get();
    }
}
