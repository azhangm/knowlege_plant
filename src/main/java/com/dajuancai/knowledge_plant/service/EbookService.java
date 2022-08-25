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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
        if (ObjectUtils.isEmpty(req.getKeyword()) &&ObjectUtils.isEmpty(req.getCategoryId2())) {
            PageHelper.startPage(req.getPage(), req.getSize());
            ebooks = ebookMapper.selectList();
        }
        else  if (ObjectUtils.isEmpty(req.getCategoryId2()) && !ObjectUtils.isEmpty(req.getKeyword())){
         s = "%" + keyword + "%";
            PageHelper.startPage(req.getPage(), req.getSize());
            ebooks = ebookMapper.selectAll(s);
        }else {
            System.out.println("分类查询开始");
            PageHelper.startPage(req.getPage(), req.getSize());
            ebooks = ebookMapper.selectByCategoryId(req.getCategoryId2());
            System.out.println(ebooks);
        }

        System.out.println(ebooks);
        PageResp<EbookResp>  pageResp = new PageResp<>();
        PageInfo pageInfo = new PageInfo(ebooks);
        List<EbookResp> ebookResps = CopyUtil.copyList(ebooks, EbookResp.class);
        pageResp.setTotal( pageInfo.getTotal());
        pageResp.setList(ebookResps);
        return pageResp;

    }

    public void update(EbookSaveOrUpdateReq req) {
        Ebook ebook = CopyUtil.copy(req,Ebook.class);
        ebookMapper.updateByPrimaryKey(ebook);
    }

    public void save(EbookSaveOrUpdateReq req) {
        Ebook ebook = new Ebook();
        BeanUtils.copyProperties(req,ebook);
        System.out.println(ebook);
        ebookMapper.insert(ebook);
    }

    public void delet(Long id) {
        ebookMapper.deleteByPrimaryKey(id);
    }
}
