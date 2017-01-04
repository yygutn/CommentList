package com.jumy.library.commentList;

/**
 * Created by Jumy on 17/1/4 11:19.
 * Copyright (c) 2017, yygutn@gmail.com All Rights Reserved.
 */
public interface BaseComment {
    String get2ReplyUserRealName();

    String get2ReplyUserId();

    String getCreatorName();

    String getCreatorId();

    String getComment();

    String getOriginCommentId();

    String getCommentId();
}
