package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @Program: community
 * @Auther: ldh
 * @Date: 2022/4/7
 * @Description:
 */
@Repository
@Primary    //AlphaDao.class的bean有两个，@Primary会优先使用这个
public class AlphaDaoMybatisImp implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
