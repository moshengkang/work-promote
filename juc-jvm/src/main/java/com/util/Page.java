package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 莫升康
 * @e-mail: 1634414600@qq.com
 * @Date: 2020/3/19 15:23
 * @Description: mysql分页逻辑
 */
public class Page {
    public static void main(String[] args) {
        //查询总数,例如：select count(1)  from table；
        Integer totalSize = 0;
        //当前页,前台传参
        Integer nowPage = 0;
        //每页条数,前台传参
        Integer pageSize = 0;
        //总页数,计算得出
        Integer totalPage = (totalSize % pageSize == 0) ? totalSize / pageSize : totalSize / pageSize + 1;
        if (nowPage < 1 || nowPage > totalPage) {
            nowPage = 1;
        }
        Integer start = 0;
        //起始条数,计算得出
        if (nowPage > 0) {
            start = (nowPage-1) * pageSize;
        }
        Map<String,Object> map = new HashMap<>();

        //limit start,pageSize
        map.put("start",start);
        map.put("pageSize",pageSize);
    }
}
