package com.terabits.utils;

import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author wangkai
 * @2016年6月2日 下午8:24:56
 * @desc:对map的key进行ASCII排序
 */
public class MapUtils {

    /**
     * 对map根据key进行排序 ASCII 顺序
     *
     * @param map
     * @return
     */
    public static SortedMap<String, Object> sortMap(Map<String, Object> map) {

        List<Entry<String, Object>> infoIds = new ArrayList<Entry<String, Object>>(
                map.entrySet());
        // 排序前
		/*
		 * for (int i = 0; i < infoIds.size(); i++) {
		 * System.out.println(infoIds.get(i).toString()); }
		 */

        // 排序
        Collections.sort(infoIds, new Comparator<Entry<String, Object>>() {
            public int compare(Entry<String, Object> o1,
                               Entry<String, Object> o2) {
                // return (o2.getValue() - o1.getValue());//value处理
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        // 排序后
        SortedMap<String, Object> sortmap = new TreeMap<String, Object>();
        for (int i = 0; i < infoIds.size(); i++) {
            String[] split = infoIds.get(i).toString().split("=");
            sortmap.put(split[0], split[1]);
        }
        return sortmap;
    }

    /**
     * map to String
     *
     * @param map
     * @return
     */
    public static String toString(Map<String, Object> map) {
        StringBuffer buf = new StringBuffer();
        buf.append("{");
        Iterator<Entry<String, Object>> i = map.entrySet().iterator();
        boolean hasNext = i.hasNext();
        while (hasNext) {
            Entry<String, Object> e = i.next();
            Object key = e.getKey();
            Object value = e.getValue();
            if (key == MapUtils.class)
                buf.append("(this Map)");
            else
                buf.append(key);
            buf.append("=");
            if (value == MapUtils.class)
                buf.append("(this Map)");
            else
                buf.append(value);
            hasNext = i.hasNext();
            if (hasNext)
                buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

}
