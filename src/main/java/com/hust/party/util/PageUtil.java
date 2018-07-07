package com.hust.party.util;


import com.hust.party.common.Page;

/**
 * @author liyue
 * 封装page设置
 */
public class PageUtil {
    /**
     * 默认四条
     *
     * @param pageNumber
     * @return
     */
    public static Page setPage(Integer pageNumber) {
        return setPage(4, pageNumber);
    }

    /**
     * 可以指定每页多少条
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public static Page setPage(Integer pageSize, Integer pageNumber) {
        Page page = new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        return page;
    }

    /**
     * 不指定条数和页数时，返回所有数据
     *
     * @return
     */
    public static Page setPage() {
        //TODO
        Page page = new Page();
        return page;
    }


}