package com.dajuancai.knowledge_plant.req;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * category
 * @author 
 */
@Table(name="category")
@Data
public class DocSaveOrUpdateReq {
    /**
     * id
     */
    private Long id;

    /**
     * 电子书id
     */
    private Long ebookId;

    /**
     * 父id
     */
    @NotNull(message = "父文档不能为空")
    private Long parent;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空")
    private String name;

    /**
     * 顺序
     */
    @NotNull(message = "第几页不能为空")
    private Integer sort;

    /**
     * 阅读数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer voteCount;

}