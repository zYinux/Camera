package com.againstsky.camera.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Member
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_member")
public class MemberEntity extends BaseEntity {

    @TableId
    @JsonIgnore
    private String uid;

    private String name;

    private String phone;

    @JsonIgnore
    private String password;

    private String avatar;


}
