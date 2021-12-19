package richieJmartDR.jmart_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import richieJmartDR.jmart_android.model.Product;

public class MainActivity extends AppCompatActivity {
    protected TextView hello;
    String searchQuery = "";
    String[] cat = {
            "BOOK",
            "KITCHEN",
            "ELECTRONIC",
            "FASHION",
            "GAMING",
            "GADGET",
            "MOTHERCARE",
            "COSMETICS",
            "HEALTHCARE",
            "FURNITURE",
            "JEWELRY",
            "TOYS",
            "FNB",
            "STATIONERY",
            "SPORTS",
            "AUTOMOTIVE",
            "PETCARE",
            "ART_CRAFT",
            "CARPENTRY",
            "MISCELLANEOUS",
            "PROPERTY",
            "TRAVEL",
            "WEDDING"};

    Gson gson = new Gson();

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

        /* Product View */
        Button prevButton = findViewById(R.id.button4);
        Button nextButton = findViewById(R.id.button8);
        EditText pageNumber = findViewById(R.id.editTextTextPersonName9);
        Button goButton = findViewById(R.id.button9);
        EditText lowPrice = findViewById(R.id.editTextTextPersonName11);
        EditText highPrice = findViewById(R.id.editTextTextPersonName13);
        Spinner category = findViewById(R.id.spinner3);

        prevButton.setOnClickListener(view->{
            Integer pageNumber_ = Integer.valueOf(pageNumber.getText().toString());
            if (pageNumber_ <= 0){
                pageNumber_ = 0;
                pageNumber.setText(String.format("%d", pageNumber_));
            }else {
                pageNumber.setText(String.format("%d", pageNumber_ - 1));
            }
        });

        nextButton.setOnClickListener(view->{
            Integer pageNumber_ = Integer.valueOf(pageNumber.getText().toString());
            pageNumber.setText(String.format("%d", pageNumber_+1));
        });

        goButton.setOnClickListener(view->{
            Integer pageNumber_ = Integer.valueOf(pageNumber.getText().toString());
            if (pageNumber_ <= 0) {
                pageNumber_ = 0;
                pageNumber.setText(String.format("%d", pageNumber_));
            }

            Integer minPrice = Integer.valueOf(lowPrice.getText().toString());
            Integer maxPrice = Integer.valueOf(highPrice.getText().toString());

            StringRequest products = new StringRequest(
                    Request.Method.GET,
                        String.format(
                                "http://10.0.2.2:6969/Product/getFiltered?page=%d&pageSize=10&search=%s&category=FNB&minPrice=%d&maxPrice=%d"
                                ,pageNumber_, searchQuery, minPrice, maxPrice
                        ),
                    resp->{
                        ArrayList<Product> products_ = gson.fromJson(resp, new TypeToken<ArrayList<Product>>(){}.getType());
                        ListView list = findViewById(R.id.productsList);
                        ProductsAdapter customAdapter = new ProductsAdapter(this, R.layout.row_product_item, products_);
                        list.setAdapter(customAdapter);
                    },
                    err->{
                        Log.e("product", err.toString());
                    }
            );
            RequestQueue q = Volley.newRequestQueue(getApplicationContext());
            q.add(products);
        });

        /* Filter View */
        EditText searchText = findViewById(R.id.editTextTextPersonName10);
        Button applyButton = findViewById(R.id.button10);

        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                cat);

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        category.setAdapter(ad);

        applyButton.setOnClickListener(view->{
            searchQuery = searchText.getText().toString();
        });

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void onItemSelected(View arg1,
                               int position,
                               long id)
    {

        Toast.makeText(getApplicationContext(),
                cat[position],
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.about) {
            Intent aboutMe = new Intent(this, AboutMeActivity.class);
            this.startActivity(aboutMe);
            return true;
        }

        if (itemId == R.id.createP) {
            Intent createProduct = new Intent(this, CreateProductActivity.class);
            this.startActivity(createProduct);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}