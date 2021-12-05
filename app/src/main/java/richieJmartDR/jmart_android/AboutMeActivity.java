package richieJmartDR.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import richieJmartDR.jmart_android.model.Account;
import richieJmartDR.jmart_android.model.Store;
import richieJmartDR.jmart_android.request.AccountRequest;
import richieJmartDR.jmart_android.request.LoginRequest;

public class AboutMeActivity extends AppCompatActivity {

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        TextView aboutMe = findViewById(R.id.textView2);
        TextView name = findViewById(R.id.textView3);
        TextView balance = findViewById(R.id.textView4);
        Button register = findViewById(R.id.button5);
        TextView registerStore = findViewById(R.id.textView7);
        EditText storeName = findViewById(R.id.editTextTextPersonName6);
        EditText storeAddress = findViewById(R.id.editTextTextPersonName7);
        EditText storePhoneNumber = findViewById(R.id.editTextTextPersonName8);
        Button register1 = findViewById(R.id.button6);
        Button cancel = findViewById(R.id.button7);
        TextView store = findViewById(R.id.textView8);
        TextView nameStore = findViewById(R.id.textView9);
        TextView addressStore = findViewById(R.id.textView10);
        TextView phoneNumberStore = findViewById(R.id.textView11);
        CardView registerS = findViewById(R.id.registerStore);
        CardView registeredS = findViewById(R.id.registeredStore);

        register.setVisibility(View.GONE);
        registerS.setVisibility(View.GONE);
        registeredS.setVisibility(View.GONE);

        if(LoginActivity.getLoggedAccount().store == null){
            register.setVisibility(View.VISIBLE);
        }

        register.setOnClickListener( view -> {
            registerS.setVisibility(View.VISIBLE);
            register.setVisibility(View.GONE);
        });

        cancel.setOnClickListener( v -> {
            registerS.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
        });

        register1.setOnClickListener(v -> {
            String nameInput = storeName.getText().toString();
            String addressInput = storeAddress.getText().toString();
            String phoneInput = storePhoneNumber.getText().toString();

            StringRequest req = AccountRequest.createStore(
                    LoginActivity.getLoggedAccount().id,
                    nameInput,
                    addressInput,
                    phoneInput,
                    re -> {
                        Store st = gson.fromJson(re, Store.class);

                        if (store == null) {
                            Toast.makeText(getApplicationContext(), "Store create failed", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        registerS.setVisibility(View.GONE);

                        nameStore.setText(st.name);
                        addressStore.setText(st.address);
                        phoneNumberStore.setText(st.phoneNumber);
                        registeredS.setVisibility(View.VISIBLE);
                    }, er -> {
                Toast.makeText(
                        getApplicationContext(),
                        er.toString(),
                        Toast.LENGTH_SHORT).show();
            });
            RequestQueue q = Volley.newRequestQueue(getApplicationContext());
            q.add(req);
        });
    }

}