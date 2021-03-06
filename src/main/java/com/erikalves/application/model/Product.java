package com.erikalves.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "PRODUCT_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long productId;

    @Column (name = "PRODUCT_PARENT_ID")
    private Long productParentId;

    @NotNull
    @Column (name = "PRODUCT_NAME")
    private String productName;

    @NotNull
    @Column (name = "PRODUCT_DESC")
    private String productDesc;

    @NotNull
    @Column (name = "PRODUCT_PRICE")
    private Double productPrice;


    @JsonFormat(pattern="yyyy-MM-dd")
    @Column (name = "PRODUCT_CREATED_TS")
    private Date productCreatedTs;


    @JsonFormat(pattern="yyyy-MM-dd")
    @Column (name = "PRODUCT_UPDATED_TS")
    private Date productUpdatedTs;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="productParentId")
    private Set<Product> products = new LinkedHashSet<>();

    public Product() {
    }

    public Product(Long productId, Long productParentId, String productName, String productDesc, Double productPrice, Date productCreatedTs, Date productUpdatedTs) {
        this.productId = productId;
        this.productParentId = productParentId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productCreatedTs = productCreatedTs;
        this.productUpdatedTs = productUpdatedTs ;

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductParentId() {
        return productParentId;
    }

    public void setProductParentId(Long productParentId) {
        this.productParentId = productParentId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Date getProductCreatedTs() {
        return productCreatedTs;
    }

    public void setProductCreatedTs(Date productCreatedTs) {
        this.productCreatedTs = productCreatedTs;
    }

    public Date getProductUpdatedTs() {
        return productUpdatedTs;
    }

    public void setProductUpdatedTs(Date productUpdatedTs) {
        this.productUpdatedTs = productUpdatedTs;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                "productParentId='" + productParentId + '\'' +
                "productName='" + productName + '\'' +
                "productDesc='" + productDesc + '\'' +
                "productPrice='" + productPrice + '\'' +
                "productCreatedTs='" + productCreatedTs + '\'' +
                "productUpdatedTs='" + productUpdatedTs + '\'' +
                "products count='" + products.toString() +
                '}';
    }
}
