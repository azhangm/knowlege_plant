package com.dajuancai.knowledge_plant.mapper;

import com.dajuancai.knowledge_plant.pojo.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TestMapper {
    List<Test> list();
}
