package com.dajuancai.knowledge_plant.service;

import com.dajuancai.knowledge_plant.mapper.ContentMapper;
import com.dajuancai.knowledge_plant.mapper.DocMapper;
import com.dajuancai.knowledge_plant.pojo.Content;
import com.dajuancai.knowledge_plant.pojo.ContentExample;
import com.dajuancai.knowledge_plant.pojo.Doc;
import com.dajuancai.knowledge_plant.pojo.DocExample;
import com.dajuancai.knowledge_plant.req.DocQueryReq;
import com.dajuancai.knowledge_plant.req.DocSaveOrUpdateReq;
import com.dajuancai.knowledge_plant.resp.DocResp;
import com.dajuancai.knowledge_plant.resp.PageResp;
import com.dajuancai.knowledge_plant.utils.CopyUtil;
import com.dajuancai.knowledge_plant.utils.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class DocService {
    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private ContentMapper contentMapper;
    public PageResp<DocResp> list(DocQueryReq req) {
        DocExample example = new DocExample();
        example.setOrderByClause("sort asc");
        DocExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> docs = docMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(docs);
        PageResp<DocResp> pageResp =  new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(pageInfo.getList());
        return pageResp;
    }

    public void update(DocSaveOrUpdateReq req) {
        Doc doc = CopyUtil.copy(req,Doc.class);
        Content content = CopyUtil.copy(req,Content.class);
        contentMapper.updateByPrimaryKey(content);
        docMapper.updateByPrimaryKey(doc);

    }

    public void save(DocSaveOrUpdateReq req) {
        long l = snowFlake.nextId();
        Doc doc = CopyUtil.copy(req, Doc.class);
        System.out.println(doc.getId());
        doc.setId(l);
        docMapper.insert(doc);
        Content content = CopyUtil.copy(req,Content.class);
        content.setId(doc.getId());
        System.out.println("content" +  content);
        contentMapper.insert(content);
     }

    public void delet(String id) {
        DocExample example = new DocExample();
        DocExample.Criteria criteria = example.createCriteria();
        ContentExample contentExample = new ContentExample();
        ContentExample.Criteria criteria1 = contentExample.createCriteria();
        List<String> strings = Arrays.asList(id.split(","));
        criteria.andIdIn(strings);
        criteria1.andIdIn(strings);
        docMapper.deleteByExample(example);
    }

    public List<DocResp> allList() {
        DocExample example = new DocExample();
        example.setOrderByClause("sort asc");
        List<Doc> categories = docMapper.selectByExample(example);
        List<DocResp> docResps = CopyUtil.copyList(categories, DocResp.class);

        return docResps;
    }



}
