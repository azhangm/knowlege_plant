package com.dajuancai.knowledge_plant.mapper;

import com.dajuancai.knowledge_plant.pojo.Ebook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EbookMapperTest {

    @Resource
    private EbookMapper mapper;
    @Test
    void selectList() {
        List<Ebook> ebooks = mapper.selectList();
        System.out.println(ebooks);
    }
}