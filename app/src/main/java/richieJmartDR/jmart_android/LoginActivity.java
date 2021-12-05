package richieJmartDR.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import richieJmartDR.jmart_android.model.Account;
import richieJmartDR.jmart_android.request.LoginRequest;

public class LoginActivity extends AppCompatActivity {
    protected EditText email;
    protected EditText password;
    protected Button login;
    protected TextView regis;
    public static final Gson gson = new Gson();
    public static Account loggedAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.LOGIN);
        regis = findViewById(R.id.regis);

        login.setOnClickListener(view -> {
            String email_text = email.getText().toString();
            String password_text = password.getText().toString();

            LoginRequest req = new LoginRequest(
                    email_text,
                    password_text,
                    re -> {
                        loggedAccount = gson.fromJson(
                                re,
                                Account.class);
                        if (loggedAccount == null) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Login failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // toast
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Login successful",
                                    Toast.LENGTH_SHORT).show();
                            Intent mainScreen = new Intent(
                                    this,
                                    MainActivity.class);
                            startActivity(mainScreen);
                        }
                    }, er -> {
                Toast.makeText(
                        getApplicationContext(),
                        er.toString(),
                        Toast.LENGTH_SHORT).show();
            });
            RequestQueue q = Volley.newRequestQueue(getApplicationContext());
            q.add(req);
        });
        regis.setOnClickListener(this::onRegister);
    }

    public void onRegister(View view) {
        //
        Intent registerScreen = new Intent(
                this,
                RegisterActivity.class);
        startActivity(registerScreen);
    }

    static Account getLoggedAccount(){
        return loggedAccount;
    }

}