package com.example.board;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private List<BoardVO> list;

    //생성자
    public BoardAdapter(List<BoardVO> list){
        this.list = list;
    }

    @NonNull
    @Override
    //ViewHolder 객체를 생성하고 초기화
    public BoardAdapter.BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    //데이터를 가져와서 ViewHolder를 채워줌
    public void onBindViewHolder(@NonNull BoardAdapter.BoardViewHolder holder, int position) {

        holder.board_title.setText(list.get(position).getTitle());
        holder.board_writer.setText(list.get(position).getWriter());
        holder.board_regdate.setText(list.get(position).getRegdate());
        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Context context = view.getContext();

                Intent boardViewActivity = new Intent(context,BoardViewActivity.class);
                boardViewActivity.putExtra("writer", list.get(position).getWriter());
                boardViewActivity.putExtra("title", list.get(position).getTitle());
                boardViewActivity.putExtra("regdate", list.get(position).getRegdate());
                boardViewActivity.putExtra("hitno", list.get(position).getHitno());
                boardViewActivity.putExtra("content", list.get(position).getContent());
                ((MainActivity)context).startActivity(boardViewActivity);
            }
        });

    }

    @Override
    //총 데이터의 갯수
    public int getItemCount() {
        return list.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {

        private TextView board_title,board_writer,board_regdate;
        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);

            this.board_title = itemView.findViewById(R.id.board_title);
            this.board_writer = itemView.findViewById(R.id.board_writer);
            this.board_regdate = itemView.findViewById(R.id.board_regdate);

        }
    }

}
