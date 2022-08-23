package com.dajuancai.knowledge_plant.service;

import com.dajuancai.knowledge_plant.req.EbookReq;
import com.dajuancai.knowledge_plant.mapper.EbookMapper;
import com.dajuancai.knowledge_plant.pojo.Ebook;
import com.dajuancai.knowledge_plant.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        String s ;
        String keyword = req.getKeyword();
        List<Ebook> ebooks;
        if (null == keyword || keyword.equals(" ") || keyword.length() == 0 ) {
            ebooks = ebookMapper.selectList();
        }
        else {
         s = "%" + keyword + "%";
        ebooks = ebookMapper.selectAll(s);
        }
        List<EbookResp> ebookResps = new ArrayList<>();
        for (Ebook ebook : ebooks) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook , ebookResp);
            ebookResps.add(ebookResp);
        }
        return ebookResps;
    }
}
