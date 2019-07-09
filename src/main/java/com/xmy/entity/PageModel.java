package com.xmy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p> 分页数据类型模板</p>
 */
public class PageModel<T> implements Serializable{

    /**
     * 当前页数
     **/
    private int pageIndex = 1;
    /**
     * 页面展示记录数
     **/
    private int pageSize = 10;
    /**
     * 数据总数
     **/
    private int dataCount = 0;
    /**
     * 当前页数据
     **/
    private List<T> data;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}