package com.example.gifthavenbackend.order;

import com.example.gifthavenbackend.entity.CustomerEntity;
import com.example.gifthavenbackend.entity.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/26
 */
public class OrderCrud {
    private static final Session session;

    /*
      创建session连接
     */
    static {
        //加载 Hibernate 核心配置文件
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        //创建一个 SessionFactory 用来获取 Session 连接对象
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //获取session 连接对象
        session = sessionFactory.openSession();
    }

    /**
     * 查询所有订单
     */
    @Test
    public void testHqlQueryAll(){
        String hql = "from OrdersEntity oe join fetch oe.customerEntity where oe.deleted = '0'";
        Query query = session.createQuery(hql);
        List<OrdersEntity> resultList = query.getResultList();
        for (OrdersEntity result : resultList) {
            System.out.println(result);
        }
    }

    /**
     * 新增订单
     */
    @Test
    public void testInsert() {
        //开始事务
        Transaction transaction = session.beginTransaction();

        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderStatus("未开始");

        CustomerEntity customerEntity = session.get(CustomerEntity.class, 1);
        ordersEntity.setCustomerEntity(customerEntity);

        ordersEntity.setDeleted("0");
        ordersEntity.setName("黎锦斌123");
        ordersEntity.setAddress("广西壮族自治区 慧泊市 邕宁区");
        ordersEntity.setRegion("南宁学院");
        session.save(ordersEntity);

        transaction.commit();
    }

    /**
     * 修改订单
     */
    @Test
    public void testUpdate() {
        Transaction transaction = session.beginTransaction();
        OrdersEntity ordersEntity = session.get(OrdersEntity.class, 1);
        ordersEntity.setOrderStatus("运输中");
        session.update(ordersEntity);
        transaction.commit();
    }

    /**
     * 根据id查询订单
     */
    @Test
    public void testHqlQueryById() {
        //hql         来自  订单表        别名  连接       订单表的定义的实体对象 找 订单表的订单id  ?是占位符 1是第一个占位符  请忽略我的(and  oe.deleted = '0')
        String hql = "from OrdersEntity oe join fetch oe.customerEntity where oe.orderId = ?1 and  oe.deleted = '0'";
        //创建sql语句
        Query query = session.createQuery(hql);
        //设置第 1 个 占位符  的值为  1
        query.setParameter(1, 1);
        //执行查询返回对象
        OrdersEntity ordersEntity = (OrdersEntity) query.uniqueResult();
        System.out.println(ordersEntity);
    }


    /**
     * 删除订单
     */
    @Test
    public void testDelete(){
        Transaction transaction = session.beginTransaction();
        OrdersEntity ordersEntity = session.get(OrdersEntity.class, 5);
        ordersEntity.setDeleted("1");
        session.update(ordersEntity);
        transaction.commit();
    }
}
