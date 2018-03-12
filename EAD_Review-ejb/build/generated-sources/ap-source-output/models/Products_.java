package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Manufacturers;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-13T04:16:21")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, String> productImage;
    public static volatile SingularAttribute<Products, String> productId;
    public static volatile SingularAttribute<Products, String> productDetail;
    public static volatile SingularAttribute<Products, Manufacturers> manufacturerId;
    public static volatile SingularAttribute<Products, String> productName;
    public static volatile SingularAttribute<Products, Integer> productPrice;

}