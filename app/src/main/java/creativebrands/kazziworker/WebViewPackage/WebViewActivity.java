package creativebrands.kazziworker.WebViewPackage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import creativebrands.kazziworker.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Fragment Webviewfragment=new WebViewFragment();
        loadFragment(Webviewfragment);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.webview_frame_container, fragment,"ONE");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
