# CommentList
评论列表
# 效果图
![截图](/img.png)
# 使用方法
- 实现BaseComment接口的以下方法即可快速实现评论列表
<pre><code>String get2ReplyUserRealName();
    String get2ReplyUserId();
    String getCreatorName();
    String getCreatorId();
    String getComment();
    String getOriginCommentId();
    String getCommentId();
</pre></code>
- 调用CommentListView的setData方法

- 如有不明白，请看Demo
