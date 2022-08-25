package com.dajuancai.knowledge_plant.service;

import com.dajuancai.knowledge_plant.mapper.CategoryMapper;
import com.dajuancai.knowledge_plant.pojo.Category;
import com.dajuancai.knowledge_plant.pojo.CategoryExample;
import com.dajuancai.knowledge_plant.req.CategoryQueryReq;
import com.dajuancai.knowledge_plant.req.CategorySaveOrUpdateReq;
import com.dajuancai.knowledge_plant.resp.CategoryResp;
import com.dajuancai.knowledge_plant.resp.PageResp;
import com.dajuancai.knowledge_plant.utils.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    public PageResp<CategoryResp> list(CategoryQueryReq req) {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categories = categoryMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(categories);
        PageResp<CategoryResp> pageResp =  new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(pageInfo.getList());
        return pageResp;
    }

    public void update(CategorySaveOrUpdateReq req) {
        Category categor = CopyUtil.copy(req,Category.class);
        categoryMapper.updateByPrimaryKey(categor);
    }

    public void save(CategorySaveOrUpdateReq req) {
        Category categor = new Category();
        BeanUtils.copyProperties(req,categor);
        System.out.println(categor);
        categoryMapper.insert(categor);
    }

    public void delet(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    public List<CategoryResp> allList() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("sort asc");
        List<Category> categories = categoryMapper.selectByExample(example);
        List<CategoryResp> categoryResps = CopyUtil.copyList(categories, CategoryResp.class);

        return categoryResps;
    }

}
