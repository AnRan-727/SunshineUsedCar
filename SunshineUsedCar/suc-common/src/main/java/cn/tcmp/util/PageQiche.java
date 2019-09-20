package cn.tcmp.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQiche<T> implements Serializable {
    private Integer pageNum, pageSize, pages;
    private long total;
    private List<T> list;
    private int[] navigtepageNumbers;

}
