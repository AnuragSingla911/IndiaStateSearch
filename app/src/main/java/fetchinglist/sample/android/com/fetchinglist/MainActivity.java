package fetchinglist.sample.android.com.fetchinglist;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment == null){
            fragment = new MainFragment();
            fragment.setRetainInstance(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, fragment , TAG).commit();
        }
    }
}
