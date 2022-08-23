package com.dajuancai.knowledge_plant;

import com.dajuancai.knowledge_plant.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class KnowledgePlantApplicationTests {

    @Resource
    private TestMapper mapper;
    @Test
    void contextLoads() {
        List<com.dajuancai.knowledge_plant.pojo.Test> list = mapper.list();
    }

}
