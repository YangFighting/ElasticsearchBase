package dao;

import com.yang.SpringDataElasticSearchMainApplication;
import com.yang.dao.ProductDao;
import com.yang.entity.Product;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataElasticSearchMainApplication.class)
public class SpringDataESProductDaoTest {

    @Resource
    private ProductDao productDao;

    /**
     * 新增
     */
    @Test
    public void save() {
        Product product = new Product();
        product.setId(2L);
        product.setTitle("华为手机");
        product.setCategory("手机");
        product.setPrice(2999.0);
        product.setImages("http://www.atguigu/hw.jpg");
        Product res = productDao.save(product);
        Assertions.assertNotNull(res, "返回为空");
    }

    //修改
    @Test
    public void update() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("小米 2 手机");
        product.setCategory("手机");
        product.setPrice(9999.0);
        product.setImages("http://www.atguigu/xm.jpg");
        Product res = productDao.save(product);
        Assertions.assertNotNull(res, "返回为空");
        log.warning(res.toString());
    }

    //根据 id 查询
    @Test
    public void findById() {
        Product product = productDao.findById(1L).orElse(null);
        Assertions.assertNotNull(product, "返回为空");
        log.warning(product.toString());
    }

    //查询所有
    @Test
    public void findAll() {
        Iterable<Product> products = productDao.findAll();
        for (Product product : products) {
            Assertions.assertNotNull(product, "返回为空");
            log.warning(product.toString());
        }
    }

//    //删除
//    @Test
//    public void delete(){
//        Product product = new Product();
//        product.setId(1L);
//        productDao.delete(product);
//    }


    //批量新增
    @Test
    public void saveAll() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setTitle("[" + i + "]小米手机");
            product.setCategory("手机");
            product.setPrice(1999.0 + i);
            product.setImages("http://www.atguigu/xm.jpg");
            productList.add(product);
        }
        Iterable<Product> res = productDao.saveAll(productList);
        Assertions.assertNotNull(res, "返回为空");
    }

    //分页查询
    @Test
    public void findByPageable() {
        //设置排序(排序方式，正序还是倒序，排序的 id)
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        int currentPage = 0;//当前页，第一页从 0 开始， 1 表示第二页
        int pageSize = 5;//每页显示多少条
        //设置查询分页
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);
        //分页查询
        Page<Product> productPage = productDao.findAll(pageRequest);
        for (Product product : productPage.getContent()) {
            Assertions.assertNotNull(product, "返回为空");
            log.warning(product.toString());
        }
    }
}
