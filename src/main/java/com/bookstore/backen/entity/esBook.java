package com.bookstore.backen.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "book")
public class esBook {

    /**keyword*/
    @Id
    @Field(type = FieldType.Integer,index = true,store = true)//，属于keyword不分词，建索引，存储
    private int id;

    @Field(analyzer = "ik_max_word",index = true,store = true)//同上
    private String isbn;
    /**text*/
    @Field(type = FieldType.Text,index = true,store = true)//作为text，分词，建立索引,存储
    private String name;

    @Field(type = FieldType.Text,index = true,store = true)//同上
    private String title;

    /**unIndexed*/
    @Field(type = FieldType.Integer,index = false,store = true)//作为unIndexed，和keystore搜索出来下信息一起带出来，不需要建立索引，比较小，要存储
    private int inventory;

    @Field(analyzer = "ik_max_word")//同上，某些情境下也可以作为索引
    private String author;

    @Field(type = FieldType.Integer,index = false)//同inventory
    private int price;

    @Field(type = FieldType.Integer,index = false)//同上
    private int sellnum;

    @Field(type = FieldType.Text,index = false)//同上，图片信息
    private String image;
    @Field(type = FieldType.Text,searchAnalyzer = "ik_max_word")//同上
    private String type;
/**stored*/
    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_max_word")//作为unStored，信息量大，不需要存储，但是需要分词建立索引，便于搜索
    private String description;



}
