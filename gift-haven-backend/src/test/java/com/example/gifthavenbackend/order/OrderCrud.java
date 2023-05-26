package com.example.gifthavenbackend.order;

import com.example.gifthavenbackend.entity.OrdersEntity;
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
     * 新增订单
     */
    @Test
    public void testInsert() {
        //开始事务
        Transaction transaction = session.beginTransaction();

        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setCustomerId(3);
        ordersEntity.setOrderStatus("未开始");
        ordersEntity.setDeleted("0");
        ordersEntity.setName("黎锦斌");
        ordersEntity.setAddress("广西壮族自治区 慧泊市 邕宁区");
        ordersEntity.setRegion("南宁学院");

        Serializable save = session.save(ordersEntity);
        System.out.println("新增数据的主键 id:" + save);

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
        String hql = "from OrdersEntity oe join fetch oe.customerEntity where oe.orderId = ?1 and  oe.deleted = '0'";
        Query query = session.createQuery(hql);
        query.setParameter(1, 1);
        OrdersEntity ordersEntity = (OrdersEntity) query.uniqueResult();
        System.out.println(ordersEntity);
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
