package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Program: community
 * @Auther: ldh
 * @Date: 2022/4/8
 * @Description:
 */
@Mapper
public interface CommentMapper {

    List<Comment> selectCommentsByEntity(@Param("entityType") int entityType,@Param("entityId") int entityId, @Param("offset")int offset, @Param("limit")int limit);

    int selectCountByEntity(@Param("entityType")int entityType, @Param("entityId")int entityId);

    int insertComment(Comment comment);
}
