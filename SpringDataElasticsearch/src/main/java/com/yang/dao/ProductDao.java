package com.yang.dao;

import com.yang.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {

}
