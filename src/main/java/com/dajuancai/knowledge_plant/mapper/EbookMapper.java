package com.dajuancai.knowledge_plant.mapper;

import com.dajuancai.knowledge_plant.pojo.Ebook;
import com.dajuancai.knowledge_plant.pojo.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface EbookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ebook record);

    int insertSelective(Ebook record);

    Ebook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ebook record);

    int updateByPrimaryKey(Ebook record);

    List<Ebook> selectAll(String s);
    List<Ebook>  selectList();

    List<Ebook> selectByCategoryId(Long categoryId2);
}