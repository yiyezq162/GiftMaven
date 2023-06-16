package com.example.gifthavenbackend.order;

import com.example.gifthavenbackend.entity.GiftsEntity;
import com.example.gifthavenbackend.entity.OrderProductEntity;
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
public class OrderProductCrud {

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
     * 插入订单商品表的数据
     */
    @Test
    public void testInsert(){
        Transaction transaction = session.beginTransaction();
        OrderProductEntity orderProductEntity = new OrderProductEntity();
        orderProductEntity.setOrderId(1);

        GiftsEntity giftsEntity = session.get(GiftsEntity.class, 5);
        orderProductEntity.setGiftsEntity(giftsEntity);

        orderProductEntity.setNumber(3);
        orderProductEntity.setDeleted("0");
        session.save(orderProductEntity);
        transaction.commit();
    }

    /**
     * 根据订单id查询订单商品详情
     */
    @Test
    public void testHqlQueryByOrderId(){
        String hql = "from OrderProductEntity ope join fetch ope.giftsEntity where ope.orderId = ?1 and  ope.deleted = '0'";
        Query query = session.createQuery(hql);
        query.setParameter(1,1);
        List<OrderProductEntity> resultList = query.getResultList();
        for (OrderProductEntity result : resultList) {
            System.out.println(result);
        }
    }

    /**
     * 修改订单商品详情
     */
    @Test
    public void testUpdate(){
        Transaction transaction = session.beginTransaction();
        OrderProductEntity orderProductEntity = session.get(OrderProductEntity.class, 1);
        orderProductEntity.setNumber(3);
        session.update(orderProductEntity);
        transaction.commit();
    }
}
