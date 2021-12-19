/**
 *
 *
 * @author Richie Yoseph Wijaya
 * @version 19/12/2021
 */
package richieJmartDR.jmart_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import richieJmartDR.jmart_android.request.CreatePRequest;
import richieJmartDR.jmart_android.request.RegisterRequest;

public class CreateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        TextView createProduct = findViewById(R.id.textView3);
        EditText name = findViewById(R.id.editTextTextPersonName);
        EditText weight = findViewById(R.id.editTextTextPersonName2);
        EditText price = findViewById(R.id.editTextTextPersonName4);
        EditText discount = findViewById(R.id.editTextTextPersonName5);
        TextView condition = findViewById(R.id.textView4);
        RadioButton newC = findViewById(R.id.radioButton);
        RadioButton usedC = findViewById(R.id.radioButton2);
        TextView category = findViewById(R.id.textView5);
        Spinner spinnerC = findViewById(R.id.spinner);
        TextView shipmentPlan = findViewById(R.id.textView6);
        Button create = findViewById(R.id.button2);
        Button cancel = findViewById(R.id.button3);
        create.setOnClickListener(v -> {
            String nameText = name.getText().toString();
            String weightText = weight.getText().toString();
            String priceText = price.getText().toString();
            String discountText = discount.getText().toString();

            CreatePRequest crt = new CreatePRequest(
                    nameText,
                    weightText,
                    priceText,
                    discountText,
                    (Response.Listener<String>) re -> {
                        try{
                            JSONObject object = new JSONObject(re);
                            if (object != null) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Product added",
                                        Toast.LENGTH_SHORT).show();
                                Intent mainScreen = new Intent(
                                        this,
                                        MainActivity.class);
                                startActivity(mainScreen);
                            } else {
                                throw new RuntimeException();
                            }
                        }catch (Exception e) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Failed to add product",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }, er ->{}
            );

            RequestQueue q = Volley.newRequestQueue(this);
            q.add(crt);

        });
        cancel.setOnClickListener(v ->{
            Intent mainScreen = new Intent(
                    this,
                    MainActivity.class);
            startActivity(mainScreen);
        });
    }
}
