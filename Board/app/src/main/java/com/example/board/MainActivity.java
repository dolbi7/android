package com.example.board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BoardAdapter boardAdapter;
    private List<BoardVO> list;
    private List<BoardVO> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        data = new ArrayList<>();
        boardAdapter = new BoardAdapter(data);
        recyclerView.setAdapter(boardAdapter);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                String page = "http://10.10.14.150:8080/restapi/list";

                try{

                    URL url = new URL(page);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                    StringBuilder stringBuilder = new StringBuilder();

                    if(conn != null) {

                        conn.setConnectTimeout(10000);
                        conn.setRequestMethod("GET");
                        conn.setUseCaches(false);

                        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                            while(true){
                                String line = bufferedReader.readLine();
                                if(line == null) break;
                                stringBuilder.append(line + "\n");
                            }

                            bufferedReader.close();
                        }
                        conn.disconnect();

                    }

                Gson gson = new Gson();

                Type type = new TypeToken<List<BoardVO>>() {}.getType();
                list = gson.fromJson(String.valueOf(stringBuilder),type);

                for(int i =0; i<list.size(); i++)
                    data.add(list.get(i));

                boardAdapter.notifyDataSetChanged();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }
}