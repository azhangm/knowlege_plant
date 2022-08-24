package com.dajuancai.knowledge_plant.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResp<T> {
    private Long total;

    private List<T> list;

}
