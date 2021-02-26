package com.secret.attendancesummary.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CommonUtils
 * @Author Gavin
 * @Date 2021/2/24 9:39
 * @Description 描述
 * @Version 1.0
 */
public class CommonUtils {
    public static <T> List<List<T>> splitList(List<T> list, int groupSize){
        int length = list.size();
        // 计算可以分成多少组
        int num = ( length + groupSize - 1 )/groupSize ; // TODO
        List<List<T>> newList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            // 开始位置
            int fromIndex = i * groupSize;
            // 结束位置
            int toIndex = (i+1) * groupSize < length ? ( i+1 ) * groupSize : length ;
            newList.add(list.subList(fromIndex,toIndex)) ;
        }
        return  newList ;
    }
}
