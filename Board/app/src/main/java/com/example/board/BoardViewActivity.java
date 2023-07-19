package com.example.board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.board.databinding.ActivityBoardViewBinding;

public class BoardViewActivity extends AppCompatActivity {

    ActivityBoardViewBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_board_view);

        viewBinding = ActivityBoardViewBinding.inflate(getLayoutInflater());
        View view = viewBinding.getRoot();
        setContentView(view);

        Intent intent = getIntent();

        viewBinding.tvWriter.setText(intent.getExtras().getString("writer"));
        viewBinding.tvTitle.setText("제목 : " + intent.getExtras().getString("title"));
        viewBinding.tvRegdate.setText("작성일자 : " + intent.getExtras().getString("regdate"));
        viewBinding.tvHitno.setText("조회수 : " + intent.getExtras().getString("hitno"));
        viewBinding.tvContent.setText(intent.getExtras().getString("content"));
        viewBinding.btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}