package cn.qzwxixi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/10/11 16:03
 */
@NoArgsConstructor //提供无参构造函数
@Data //提供类的get、set、equals、hashCode、canEqual、toString方法
@Accessors(chain = true)  //可以链式访问构造器
public class Dept implements Serializable {
    private Integer deptNo;
    private String deptName;
    private String dbSource;
}
