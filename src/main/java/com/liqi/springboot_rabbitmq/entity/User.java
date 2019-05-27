package com.liqi.springboot_rabbitmq.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author liqi
 * @since 2019-05-15
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    private String aliasName;

    private Integer isDelete;

    private String sex;

    /**
     * 版本
     */

    private Integer version;


}
