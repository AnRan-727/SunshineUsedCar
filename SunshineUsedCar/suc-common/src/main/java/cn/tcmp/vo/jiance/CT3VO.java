package cn.tcmp.vo.jiance;

import cn.tcmp.entity.Testing;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: AnRan
 * Date: 2019/9/21
 */
@Data
public class CT3VO implements Serializable {

    private Integer testID;
    private String t3;
    private Integer parentID;

}
