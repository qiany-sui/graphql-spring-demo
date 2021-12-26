package qyq.example.graphql.kickstart.bean.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户类
 *
 * @author qianyuqi
 * @since 2021-12-26 16:13
 */
@Data
public class UserInfo {

    // 用户编号
    private String userId;
    // 用户名称
    private String name;
    // 年龄
    private Integer age;
    // 工资
    private double salary;
    // 地址
    private String address;
    // 职业
    private Integer profession;
    // 创建时间
    private LocalDateTime createTime;
    // 更新时间
    private LocalDateTime updateTime;
    // 是否删除的状态
    private Byte isDelete;

}
