package andreyskakunenko.rssfeed;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import andreyskakunenko.rssfeed.adapter.FeedAdapter;
import andreyskakunenko.rssfeed.common.HTTPDataHandler;
import andreyskakunenko.rssfeed.model.RSSObject;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    RSSObject mRSSObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.news);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));

        loadRSS();
    }

    private void loadRSS() {
        LoadRSS rssLoad = new LoadRSS();
        String RSS_link = "http://news.infocar.ua/rss/news.php";
        String RSS_to_JSON_api = "https://api.rss2json.com/v1/api.json?rss_url=";
        rssLoad.execute(RSS_to_JSON_api + RSS_link);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_refresh){
            loadRSS();
        }
        return true;
    }
    public class LoadRSS extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result;
            HTTPDataHandler http = new HTTPDataHandler();
            result = http.GetHTTPData(strings[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            mRSSObject = new Gson().fromJson(s,RSSObject.class);
            FeedAdapter adapter = new FeedAdapter(MainActivity.this,mRSSObject);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
