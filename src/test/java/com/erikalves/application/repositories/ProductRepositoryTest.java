package com.erikalves.application.repositories;


import com.erikalves.application.model.Product;
import com.erikalves.application.utils.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryTest.class);

    @Autowired(required = true)
    ProductRepository productRepository;

    Product createdProduct;


    @Before
    public void begin() {

        createdProduct = new Product();
        createdProduct.setProductParentId(1l);
        createdProduct.setProductName("Smartphone JUNIT");
        createdProduct.setProductDesc("Junit now manufactures smartphones");
        createdProduct.setProductPrice(200.00);
        createdProduct.setProductCreatedTs(Util.getCurrentDate());
        createdProduct.setProductUpdatedTs(Util.getCurrentDate());

    }


    @Test
    public void shouldCreateUpdateDeleteProduct() {

        shouldCreateProduct();
        shouldUpdateProduct();
        shouldDeleteProduct();
    }

    public void shouldCreateProduct() {

        Product savedProduct = productRepository.save(createdProduct);
        LOGGER.debug("saved product ID {}", savedProduct);
        Assert.assertNotNull(savedProduct.getProductId());

    }

    public void shouldDeleteProduct() {

        Long id = createdProduct.getProductId();
        productRepository.delete(id);
        Product deletedProduct = productRepository.findOne(id);
        Assert.assertEquals(null, deletedProduct);

    }


    public void shouldUpdateProduct() {

        createdProduct.setProductDesc("UPDATED");
        Product savedProduct = productRepository.save(createdProduct);
        Assert.assertTrue(null != savedProduct);
        Assert.assertTrue("UPDATED".equals(savedProduct.getProductDesc()));
    }



    @Test
    public void findProductIncludingRelationships() {


        List<Product> list = productRepository.findProductIncludingRelationships(1l);
        Assert.assertTrue(null != list);
        for (Product product : list) {
            Assert.assertTrue(null != product);
            LOGGER.debug(" *** RESULT *** {}", product.toString());

            Set<Product> childrenProducts = product.getProducts();
            for (Product childProduct : childrenProducts) {
                Assert.assertTrue(null != childProduct);
                Assert.assertTrue(null != childProduct.getProductId());
                LOGGER.debug(" *** RESULT (child product) *** {}", childProduct.toString());

                           }

        }
    }

    @Test
    public void findProductExcludingRelationships() {

        //Product product = productRepository.findProductExcludingRelationships(3l);
        Product product = productRepository.findProductExcludingRelationships(3l);
        Assert.assertTrue(null != product);
        Assert.assertTrue(null != product.getProductId());
        LOGGER.debug(" *** RESULT *** {}", product.toString());
        //String json = Util.toJson(product);
       // Assert.assertTrue(null != json);
        //LOGGER.debug(" *** RESULT *** {}", json);
    }


    @Test
    public void findAllIncludingRelationships()  {

        List<Product> list = productRepository.findAllProductsIncludingRelationships();

        Assert.assertTrue(null != list);
        for(Product product: list){
            Assert.assertTrue(null != product);
            Assert.assertTrue(null != product.getProductId());
            LOGGER.debug(" *** RESULT *** {}", product.toString());
        }

    }

    @Test
    public void findAllExcludingRelationships() {

        String json;
        List<Product> list = productRepository.findAllExcludingRelationships();
        Assert.assertTrue(null != list && list.size()>0);
        for(Product product: list){
            Assert.assertTrue(null != product);
            Assert.assertTrue(null != product.getProductId());
            LOGGER.debug(" *** RESULT *** {}", product.toString());
        }
        //JSONArray array = Util.toJsonArray(list);
        //LOGGER.debug(" *** RESULT *** {}", array);

    }

}