package richieJmartDR.jmart_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    protected TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("JMART ANDROID");
        setSupportActionBar(toolbar);

        TabLayout mainTabs = findViewById(R.id.mainTabs);
        CardView productView = findViewById(R.id.productView);
        CardView filterView = findViewById(R.id.filterView);

        mainTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        productView.setVisibility(View.VISIBLE);
                        filterView.setVisibility(View.GONE);
                        break;
                    case 1:
                        productView.setVisibility(View.GONE);
                        filterView.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

//        hello = findViewById(R.id.MainHello);
//        hello.setText("Hello " + LoginActivity.getLoggedAccount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.about) {
            Intent aboutMe = new Intent(this, AboutMeActivity.class);
            this.startActivity(aboutMe);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}