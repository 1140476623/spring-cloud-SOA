package com.ljt.spring.cloud.feign.person.service.provider.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Person实体类
 */
@Entity
@Table(name = "persons")
public class Person{

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "VARCHAR(128) NOT NULL")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
