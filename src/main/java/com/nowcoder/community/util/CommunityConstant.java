package com.nowcoder.community.util;

/**
 * @Program: community
 * @Auther: ldh
 * @Date: 2022/4/7
 * @Description:
 */
public interface CommunityConstant {
    //激活成功
    int ACTVIATION_SUCCESS = 0;

    //激活失败
    int ACTVIATION_FAILURE = 2;

    //重复激活
    int ACTVIATION_REPEAT = 1;

    // 默认状态的登录凭证的超时时间
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    // 记住状态的登录凭证的超时时间
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 *100;

    // 实体类型：帖子
    int ENTITY_TYPE_POST = 1;

    // 实体类型：评论
    int ENTITY_TYPE_COMMENT = 2;

    // 实体类型: 用户
    int ENTITY_TYPE_USER = 3;

}
