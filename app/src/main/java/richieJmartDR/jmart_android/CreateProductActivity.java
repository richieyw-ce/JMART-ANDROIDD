package richieJmartDR.jmart_android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    }


}
