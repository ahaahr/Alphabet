package golf.alphabet.com.alphabet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerTabStrip tabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabStrip = (PagerTabStrip) findViewById(R.id.pagerTabStrip);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return SessionsFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0: return "Tuesday";
                    case 1: return "Wednesday";
                    case 2: return "Thursday";
                    case 3: return "Friday";
                    default: return "??";
                }
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://oredev.eu-west-2.elasticbeanstalk.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<Session>> sessionsCall = service.getSessions("monday");
        Call<List<Session>> sessionsCall2 = service.getSessions("tuesday");
        Call<List<Session>> sessionsCall3 = service.getSessions("wednesday");
        Call<List<Session>> sessionsCall4 = service.getSessions("thursday");
        Call<List<Session>> sessionsCall5 = service.getSessions("friday");
        Call<List<Session>> sessionsCall6 = service.getSessions("saturday");
        Call<List<Session>> sessionsCall7 = service.getSessions("sunday");

        Callback<List<Session>> callback = new Callback<List<Session>>() {
            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                List<Session> sessions = response.body();
                if (sessions != null) {
                    for (Session session : sessions) {
                        Log.d("Alphabet", "Session : " + session.title);
                    }
                } else {
                    Log.d("Alphabet", "No sessions for " + call.request().url());
                }
            }

            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {
                Log.d("Alphabet", call.request().url() + "Error : " + t.getMessage());
            }
        };

        sessionsCall.enqueue(callback);
        sessionsCall2.enqueue(callback);
        sessionsCall3.enqueue(callback);
        sessionsCall4.enqueue(callback);
        sessionsCall5.enqueue(callback);
        sessionsCall6.enqueue(callback);
        sessionsCall7.enqueue(callback);



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
