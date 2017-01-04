package com.jumy.commentlist;

import com.jumy.library.commentList.BaseComment;

import java.util.Date;

/**
 * Created by Jumy on 17/1/4 11:18.
 * Copyright (c) 2016, yygutn@gmail.com All Rights Reserved.
 */
public class CommentModel implements BaseComment {

    private String comment = "";
    private Date date = new Date();
    private String creatorRealname = "";
    private String originCommentRealname = "";
    private String originCommentCreatorId = "";
    private String originCommentId = "";
    private String creatorId = "";
    private String id = "";


    @Override
    public String get2ReplyUserRealName() {
        return originCommentRealname;
    }

    @Override
    public String get2ReplyUserId() {
        return originCommentCreatorId;
    }

    @Override
    public String getCreatorName() {
        return creatorRealname;
    }

    @Override
    public String getCreatorId() {
        return creatorId;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public String getOriginCommentId() {
        return originCommentId;
    }

    @Override
    public String getCommentId() {
        return id;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCreatorRealname() {
        return creatorRealname;
    }

    public void setCreatorRealname(String creatorRealname) {
        this.creatorRealname = creatorRealname;
    }

    public String getOriginCommentRealname() {
        return originCommentRealname;
    }

    public void setOriginCommentRealname(String originCommentRealname) {
        this.originCommentRealname = originCommentRealname;
    }

    public String getOriginCommentCreatorId() {
        return originCommentCreatorId;
    }

    public void setOriginCommentCreatorId(String originCommentCreatorId) {
        this.originCommentCreatorId = originCommentCreatorId;
    }

    public void setOriginCommentId(String originCommentId) {
        this.originCommentId = originCommentId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
