package com.tang4j.core.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public class PageUtil {

    private PageUtil() {
    }

    public static Page<Long> getPage(Map<String, Object> params) {
        Integer current = 1;
        Integer size = 10;
        String orderBy = "id_";
        String sortAsc = null;
        String openSort = "Y";
        String asc = (String) params.get("asc");
        String desc = (String) params.get("desc");
        if (DataUtil.isNotEmpty(params.get("pageNumber"))) {
            current = Integer.valueOf(params.get("pageNumber").toString());
        }

        if (DataUtil.isNotEmpty(params.get("pageIndex"))) {
            current = Integer.valueOf(params.get("pageIndex").toString());
        }

        if (DataUtil.isNotEmpty(params.get("pageSize"))) {
            size = Integer.valueOf(params.get("pageSize").toString());
        }

        if (DataUtil.isNotEmpty(params.get("limit"))) {
            size = Integer.valueOf(params.get("limit").toString());
        }

        if (DataUtil.isNotEmpty(params.get("offset"))) {
            current = Integer.valueOf(params.get("offset").toString()) / size + 1;
        }

        if (DataUtil.isNotEmpty(params.get("sort"))) {
            orderBy = (String) params.get("sort");
            params.remove("sort");
        }

        if (DataUtil.isNotEmpty(params.get("orderBy"))) {
            orderBy = (String) params.get("orderBy");
            params.remove("orderBy");
        }

        if (DataUtil.isNotEmpty(params.get("sortAsc"))) {
            sortAsc = (String) params.get("sortAsc");
            params.remove("sortAsc");
        }

        if (DataUtil.isNotEmpty(params.get("openSort"))) {
            openSort = (String) params.get("openSort");
            params.remove("openSort");
        }

        Object filter = params.get("filter");
        if (filter != null) {
            params.putAll(JSON.parseObject(filter.toString(), Map.class));
        }

        Page page;
        if (size == -1) {
            page = new Page();
            if ("Y".equals(openSort)) {
                if (DataUtil.isEmpty(asc) && DataUtil.isEmail(desc)) {
                    if ("Y".equals(sortAsc)) {
                        page.setAsc(orderBy.split(","));
                    } else {
                        page.setDesc(orderBy.split(","));
                    }
                } else {
                    if (DataUtil.isNotEmpty(asc)) {
                        page.setAsc(asc.split(","));
                    }

                    if (DataUtil.isNotEmpty(desc)) {
                        page.setDesc(desc.split(","));
                    }
                }
            }

            return page;
        } else {
            page = new Page((long) current, (long) size);
            if ("Y".equals(openSort)) {
                if (DataUtil.isEmpty(asc) && DataUtil.isEmail(desc)) {
                    if ("Y".equals(sortAsc)) {
                        page.setAsc(orderBy.split(","));
                    } else {
                        page.setDesc(orderBy.split(","));
                    }
                } else {
                    if (DataUtil.isNotEmpty(asc)) {
                        page.setAsc(asc.split(","));
                    }

                    if (DataUtil.isNotEmpty(desc)) {
                        page.setDesc(desc.split(","));
                    }
                }
            }

            return page;
        }
    }

}
