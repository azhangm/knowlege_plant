package com.dajuancai.knowledge_plant.service;

import com.dajuancai.knowledge_plant.mapper.EbookMapper;
import com.dajuancai.knowledge_plant.pojo.Ebook;
import com.dajuancai.knowledge_plant.pojo.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list() {
        return ebookMapper.selectAll();
    }
}
