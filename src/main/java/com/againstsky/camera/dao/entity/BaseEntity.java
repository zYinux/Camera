package com.againstsky.camera.dao.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * BaseEntity
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/21
 */
@Data
public class BaseEntity {

    private Date createTime;

    private Date updateTime;

    private Integer logicDeleted;
}
