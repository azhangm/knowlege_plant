package com.dajuancai.knowledge_plant.service;

import com.dajuancai.knowledge_plant.mapper.DocMapper;
import com.dajuancai.knowledge_plant.pojo.Doc;
import com.dajuancai.knowledge_plant.pojo.DocExample;
import com.dajuancai.knowledge_plant.req.DocQueryReq;
import com.dajuancai.knowledge_plant.req.DocSaveOrUpdateReq;
import com.dajuancai.knowledge_plant.resp.DocResp;
import com.dajuancai.knowledge_plant.resp.PageResp;
import com.dajuancai.knowledge_plant.utils.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocService {
    @Resource
    private DocMapper docMapper;

    public PageResp<DocResp> list(DocQueryReq req) {
        DocExample example = new DocExample();
        example.setOrderByClause("sort asc");
        DocExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> categories = docMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(categories);
        PageResp<DocResp> pageResp =  new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(pageInfo.getList());
        return pageResp;
    }

    public void update(DocSaveOrUpdateReq req) {
        Doc categor = CopyUtil.copy(req,Doc.class);
        docMapper.updateByPrimaryKey(categor);
    }

    public void save(DocSaveOrUpdateReq req) {
        Doc categor = new Doc();
        BeanUtils.copyProperties(req,categor);
        System.out.println(categor);
        docMapper.insert(categor);
    }

    public void delet(Long id) {
        docMapper.deleteByPrimaryKey(id);
    }

    public List<DocResp> allList() {
        DocExample example = new DocExample();
        example.setOrderByClause("sort asc");
        List<Doc> categories = docMapper.selectByExample(example);
        List<DocResp> docResps = CopyUtil.copyList(categories, DocResp.class);

        return docResps;
    }

}
