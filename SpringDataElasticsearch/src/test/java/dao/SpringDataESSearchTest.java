package dao;

import com.yang.SpringDataElasticSearchMainApplication;
import com.yang.dao.ProductDao;
import com.yang.entity.Product;
import lombok.extern.java.Log;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataElasticSearchMainApplication.class)
public class SpringDataESSearchTest {

    @Autowired
    private ProductDao productDao;

    /**
     * term 查询
     * search(termQueryBuilder) 调用搜索方法，参数查询构建器对象(该方法在 Elasticsearch: 7.8.0 中已经抛弃了)
     */
    @Test
    public void termQuery() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "小");
        Iterable<Product> products = productDao.search(termQueryBuilder);
        for (Product product : products) {
            Assertions.assertNotNull(product, "返回为空");
            log.warning(product.toString());
        }
    }
}
