package com.arunpn.photoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.arunpn.photoapp.adapter.CommentsAdapter;
import com.arunpn.photoapp.model.CommentDetail;
import com.arunpn.photoapp.rest.ApiService;
import com.arunpn.photoapp.rest.Constants;
import com.arunpn.photoapp.rest.RestClient;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CommentsActivity extends AppCompatActivity {

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    CommentsAdapter adapter;

    ApiService apiService;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        setupToolBar();
        apiService = new RestClient().getApiService();
        adapter = new CommentsAdapter(this,new ArrayList<CommentDetail>());

        Intent intent = getIntent();
        String mediaId = intent.getStringExtra("mediaId");
        refreshComments(mediaId);
    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("COMMENTS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.title_bar_text_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void refreshComments(String mediaId) {

        apiService.getComments(mediaId, Constants.CLIENT_ID, new Callback<List<CommentDetail>>() {
            @Override
            public void success(List<CommentDetail> commentDetails, Response response) {
                Log.e("x","All the comments");
                listView.setAdapter(adapter);
                adapter.clear();
                adapter.addAll(commentDetails);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("x","some error");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_comments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
