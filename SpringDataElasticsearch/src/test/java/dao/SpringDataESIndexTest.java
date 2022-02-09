package dao;

import com.yang.SpringDataElasticSearchMainApplication;
import com.yang.entity.Product;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataElasticSearchMainApplication.class)
@Log
public class SpringDataESIndexTest {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void createIndex() {
        //创建索引，系统初始化会自动创建索引
        boolean flg = elasticsearchRestTemplate.indexOps(Product.class).exists();
        Assertions.assertTrue(flg);
        log.warning("索引存在");
    }


    @Test
    public void deleteIndex() {
        boolean flg = elasticsearchRestTemplate.indexOps(Product.class).delete();
        Assertions.assertTrue(flg);
        log.warning("删除索引成功");
    }
}
