package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @Program: community
 * @Auther: ldh
 * @Date: 2022/4/7
 * @Description:
 */
@Repository("alphaHibernate")
public class AlphaDaoHibernateImp implements AlphaDao{
    @Override
    public String select() {
        return "hibernate";
    }
}
