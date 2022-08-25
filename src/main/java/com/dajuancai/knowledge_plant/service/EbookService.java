package com.dajuancai.knowledge_plant.service;

import com.dajuancai.knowledge_plant.req.EbookQueryReq;
import com.dajuancai.knowledge_plant.mapper.EbookMapper;
import com.dajuancai.knowledge_plant.pojo.Ebook;
import com.dajuancai.knowledge_plant.req.EbookSaveOrUpdateReq;
import com.dajuancai.knowledge_plant.resp.EbookResp;
import com.dajuancai.knowledge_plant.resp.PageResp;
import com.dajuancai.knowledge_plant.utils.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookResp> list(EbookQueryReq req) {
        String s ;
        String keyword = req.getKeyword();
        List<Ebook> ebooks;
        if (null == keyword || keyword.equals(" ") || keyword.length() == 0 ) {
            PageHelper.startPage(req.getPage(), req.getSize());
            ebooks = ebookMapper.selectList();
        }
        else {
         s = "%" + keyword + "%";
            PageHelper.startPage(req.getPage(), req.getSize());
            ebooks = ebookMapper.selectAll(s);
        }
        PageResp<EbookResp>  pageResp = new PageResp<>();
        PageInfo pageInfo = new PageInfo(ebooks);
        List<EbookResp> ebookResps = CopyUtil.copyList(ebooks, EbookResp.class);
        pageResp.setTotal( pageInfo.getTotal());
        System.out.println("数据数" + pageInfo.getTotal());
        System.out.println(  "页数" + pageInfo.getPageNum());
        System.out.println(pageInfo.getPages());
        pageResp.setList(ebookResps);
        return pageResp;

    }

    public void update(EbookSaveOrUpdateReq req) {
        Ebook ebook = CopyUtil.copy(req,Ebook.class);
        ebookMapper.updateByPrimaryKey(ebook);
    }
}
