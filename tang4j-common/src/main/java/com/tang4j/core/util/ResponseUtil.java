package com.tang4j.core.util;

import com.alibaba.fastjson.JSON;
import com.tang4j.core.support.http.HttpCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ResponseUtil {

    /**
     * 使用response输出JSON
     *
     * @param response  请求体
     * @param resultMap 响应体
     */
    public static void out(ServletResponse response, Map<String, Object> resultMap) {

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(resultMap));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public static Map<String, Object> resultMap(Integer code, String msg) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", msg);
        resultMap.put("code", code);
        resultMap.put("timestamp", new Date());
        return resultMap;
    }

    public static Map<String, Object> resultMap(HttpCode httpCode) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", httpCode.getMsg());
        resultMap.put("code", httpCode.getCode());
        resultMap.put("timestamp", new Date());
        return resultMap;
    }


    public static Map<String, Object> resultMap(Integer status, String msg, Object data) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", msg);
        resultMap.put("code", status);
        resultMap.put("data", data);
        resultMap.put("timestamp", new Date());
        return resultMap;
    }

    public static Map<String, Object> resultMap(HttpCode httpCode, Object data) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", httpCode.getCode());
        resultMap.put("code", httpCode.getCode());
        resultMap.put("data", data);
        resultMap.put("timestamp", new Date());
        return resultMap;
    }

    public static Map<String, Object> resultMap(HttpCode httpCode, Map<String,Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", httpCode.getCode());
        resultMap.put("code", httpCode.getCode());
        resultMap.put("data", map.get("data"));
        resultMap.put("random", map.get("random"));
        resultMap.put("timestamp", new Date());
        return resultMap;
    }
}
