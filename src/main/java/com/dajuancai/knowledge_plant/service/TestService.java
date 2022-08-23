package com.dajuancai.knowledge_plant.service;

import com.dajuancai.knowledge_plant.mapper.TestMapper;
import com.dajuancai.knowledge_plant.pojo.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
