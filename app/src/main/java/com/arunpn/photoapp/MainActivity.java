package com.arunpn.photoapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.arunpn.photoapp.adapter.FeedAdapter;
import com.arunpn.photoapp.model.Photo;
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


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    FeedAdapter adapter;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolBar();
        adapter = new FeedAdapter(this, new ArrayList<Photo>());
        apiService = new RestClient().getApiService();
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
        refreshPhotos();

    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.APP_TITLE));
        toolbar.setTitleTextColor(getResources().getColor(R.color.title_bar_text_color));
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshPhotos();
        }
    };

    private void refreshPhotos() {

        apiService.getPopularPhotos(Constants.CLIENT_ID, new Callback<List<Photo>>() {
            @Override
            public void success(List<Photo> photos, Response response) {

                listView.setAdapter(adapter);
                adapter.clear();
                adapter.addAll(photos);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
