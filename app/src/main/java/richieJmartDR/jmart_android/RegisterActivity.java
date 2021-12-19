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

import org.json.JSONException;
import org.json.JSONObject;

import richieJmartDR.jmart_android.request.LoginRequest;
import richieJmartDR.jmart_android.request.RegisterRequest;
/**
 *
 *
 * @author Richie Yoseph Wijaya
 * @version preSBA
 */
public class RegisterActivity extends AppCompatActivity {
    protected EditText name;
    protected EditText email;
    protected EditText password;
    protected Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.RegisterName);
        email = findViewById(R.id.RegisterEmail);
        password = findViewById(R.id.RegisterPassword);
        register = findViewById(R.id.RegisterButton);
        register.setOnClickListener(v -> {
            String nameText = name.getText().toString();
            String emailText = email.getText().toString();
            String passwordInput = password.getText().toString();

            RegisterRequest req = new RegisterRequest(
                    nameText,
                    emailText,
                    passwordInput,
                    (Response.Listener<String>) re -> {
                        try{
                            JSONObject object = new JSONObject(re);
                            if (object != null) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Registered successfully",
                                        Toast.LENGTH_SHORT).show();

                                Intent loginScreen = new Intent(this,LoginActivity.class);
                                startActivity(loginScreen);
                            } else {
                                throw new RuntimeException();
                            }
                        }catch (Exception e) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Failed to be registered",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }, er ->{}
                    );

            RequestQueue q = Volley.newRequestQueue(this);
            q.add(req);

        });
    }

    public void onClick(View view) {
    }
}