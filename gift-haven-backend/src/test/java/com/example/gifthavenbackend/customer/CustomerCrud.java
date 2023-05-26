package com.example.gifthavenbackend.customer;

import com.example.gifthavenbackend.entity.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/25
 */
public class CustomerCrud {

    private static final Session session;

    /*
      创建session连接
     */
     static  {
        //加载 Hibernate 核心配置文件
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        //创建一个 SessionFactory 用来获取 Session 连接对象
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //获取session 连接对象
        session = sessionFactory.openSession();
    }

    /**
     * 测试添加顾客数据
     */
    @Test
    public void testInsert() {
        //开始事务
        Transaction transaction = session.beginTransaction();
        //创建实体类对象
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("测试");
        customerEntity.setPassword("123456");
        customerEntity.setPhone("13481092344");
        customerEntity.setAddress("广西南宁");
        customerEntity.setAvatar(" ");
        customerEntity.setDeleted("0");
        //向 user 表中插入数据,返回值为新增数据的主键 id
        Serializable save = session.save(customerEntity);
        System.out.println("新增数据的主键 id:"+save);
        //提交事务
        transaction.commit();
    }

    /**
     * 测试修改顾客数据
     */
    @Test
    public void testUpdate(){
        //开始事务
        Transaction transaction = session.beginTransaction();
        //查找实体对象
        CustomerEntity customerEntity = session.get(CustomerEntity.class, 1);
        customerEntity.setName("测试2");
        customerEntity.setPassword("123");
        //直接调用 update() 方法进行修改
        session.update(customerEntity);
        //提交事务
        transaction.commit();
    }

    /**
     * 查询地址是广西的模糊查询
     */
    @Test
    public void testHqlQuery() {
        //创建 HQL 语句，语法与 SQL 类似，但操作的是实体类及其属性
        //1为占位符
        Query query = session.createQuery("from CustomerEntity where address like ?1 and deleted = '0'");
        //查询所有地址为广西的用户
        query.setParameter(1, "%广西%");
        //获取结果集
        List<CustomerEntity> resultList = query.getResultList();
        //遍历结果集
        for (CustomerEntity user : resultList) {
            System.out.println(user);
        }
    }

    /**
     * 测试删除数据
     */
    @Test
    public void testDelete(){
        //开始事务
        Transaction transaction = session.beginTransaction();
        //查找对象
        CustomerEntity customerEntity = session.get(CustomerEntity.class, 10);
        //设置删除
        customerEntity.setDeleted("1");
        //保存
        session.update(customerEntity);
        //提交事务
        transaction.commit();

    }
}
