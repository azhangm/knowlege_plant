package com.dajuancai.knowledge_plant.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageReq {

    @NotNull(message = "页码不能为空")
    private Integer page;

    @NotNull (message = "每页条数不能为空")
    @Max(value = 1000 , message = "每页条数不能超过1000" )
    private Integer size;
}
