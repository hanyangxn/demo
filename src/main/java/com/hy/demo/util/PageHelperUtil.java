package com.hy.demo.util;

import com.github.pagehelper.Page;

import java.util.List;


public class PageHelperUtil {

    @SuppressWarnings("unchecked")
	public static <T> PagedResult<T> toPagedResult(List<T> datas) {
        PagedResult<T> result = new PagedResult<T>();
        if (datas instanceof Page) {
			@SuppressWarnings("rawtypes")
			Page page = (Page) datas;
            result.setPageNo(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setDataList(page.getResult());
            result.setTotal(page.getTotal());
            result.setPages(page.getPages());
        }
        else {
            result.setPageNo(1);
            result.setPageSize(datas.size());
            result.setDataList(datas);
            result.setTotal(datas.size());
        }
        return result;
    }

}
