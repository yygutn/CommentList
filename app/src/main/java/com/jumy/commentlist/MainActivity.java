package com.jumy.commentlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jumy.library.commentList.CommentListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CommentListView mCommentListView;
    private Button mRefreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        initEvents();
    }


    private void assignViews() {
        mCommentListView = (CommentListView) findViewById(R.id.mCommentList);
        mRefreshBtn = (Button) findViewById(R.id.mRefresh);
    }

    private void initEvents() {
        mCommentListView.setData(createComments());
        mRefreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommentListView.setData(createComments());
            }
        });
    }

    public static List<CommentModel> createComments() {
        List<CommentModel> list = new ArrayList<>();
        Random random = new Random();
        int len = random.nextInt(8) + 1;
        for (int i = 0; i < len; i++) {
            list.add(createCommentModel());
        }
        Log.w("Size", list.size() + "");
        return list;
    }

    public static CommentModel createCommentModel() {
        Random random = new Random();
        CommentModel item = new CommentModel();
        int c_size = COMMENTS.length;
        int n_size = NAMES.length;
        item.setComment(COMMENTS[random.nextInt(c_size)]);
        item.setCreatorId(NAMES[random.nextInt(n_size)]);
        item.setCreatorRealname(item.getCreatorId());
        item.setOriginCommentRealname(random.nextBoolean() ? NAMES[random.nextInt(n_size)] : "");
        item.setOriginCommentId(item.getOriginCommentRealname());
        return item;
    }

    public static final String[] COMMENTS = {
            "可以的",
            "又跑哪儿玩去了",
            "这是在哪儿啊",
            "漂亮",
            "带上我！",
            "......",
            "嘿哈",
    };

    public static final String[] NAMES = {
            "自己",
            "Jumy",
            "强强",
            "WBBBBB",
            "一叶知秋"
    };
}
