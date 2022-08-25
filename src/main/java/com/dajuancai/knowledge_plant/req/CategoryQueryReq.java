package com.dajuancai.knowledge_plant.req;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * category
 * @author 
 */
@Table(name="category")
@Data
public class CategoryQueryReq  extends PageReq {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 父id
     */
    private Long parent;

    /**
     * 名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer sort;

    private static final long serialVersionUID = 1L;
}