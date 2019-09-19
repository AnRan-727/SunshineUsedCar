package cn.tcmp.util;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PageUtils<T> implements Serializable {
    //一页多少条
    private Integer pageSize;
    //第几页
    private Integer pageNumber;
    //总共多少数据
    private Integer pageTotal;
    //总共多少页
    private Integer pageCount;
    //分页完的集合
    private List<T> list;
    //所有导航页号
    private int[] navigatepageNums;

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public PageUtils() {
    }

    public PageUtils(Object object) {
        try {
            Method number = object.getClass().getMethod("getPageNum", null);
            Method size = object.getClass().getMethod("getPageSize", null);
            Method total = object.getClass().getMethod("getTotal", null);
            Method count = object.getClass().getMethod("getPages", null);
            Method list = object.getClass().getMethod("getList", null);
            this.pageSize = Integer.valueOf(size.invoke(object).toString());
            this.pageNumber =Integer.valueOf(number.invoke(object).toString());
            this.pageTotal = Integer.valueOf(total.invoke(object).toString());
            this.pageCount = Integer.valueOf(count.invoke(object).toString());
            this.list = (List<T>) list.invoke(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
