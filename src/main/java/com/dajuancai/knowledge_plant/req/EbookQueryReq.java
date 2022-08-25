package com.dajuancai.knowledge_plant.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbookQueryReq extends PageReq{
    /*
    *
    * */
    private Long id;
    /**
     * 名称
     */
    private String keyword;

}
