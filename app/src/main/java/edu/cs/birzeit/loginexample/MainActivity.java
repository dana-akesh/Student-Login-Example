package edu.cs.birzeit.loginexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private RadioGroup rbGender;
    private Switch switchFinalYear;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        setListeners();
    }

    private void initComponents() {
        edtName = findViewById(R.id.editText_name);
        edtEmail = findViewById(R.id.editText_email);
        rbGender = findViewById(R.id.rgGender);
        switchFinalYear = findViewById(R.id.switch_final_year);
        btnSave = findViewById(R.id.button_save);
    }

    private void setListeners() {
        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString();
            int selectedId = rbGender.getCheckedRadioButtonId();
            boolean isFinalYear = switchFinalYear.isChecked();
            RadioButton radioButton = findViewById(selectedId);

            if (name.isEmpty()) {
                edtName.setError("Please enter your name");
                return;
            }
            if (email.isEmpty()) {
                edtEmail.setError("Please enter your email");
                return;
            }
            if (!email.contains("@")) {
                edtEmail.setError("Please enter a valid email");
                return;
            }
            if (selectedId == -1) {
                Toast.makeText(MainActivity.this, "Please select a gender", Toast.LENGTH_LONG).show();
                return;
            }

            Student student = new Student(name, email, radioButton.getText().toString(), isFinalYear);
            Toast.makeText(MainActivity.this, "Student Added", Toast.LENGTH_LONG).show();
            saveToSharedPreferences(student);
        });
    }

    private void saveToSharedPreferences(Student student){
        // creating a GSON object
        Gson gson = new Gson();
        String serializedObject = gson.toJson(student);

        SharedPreferences sharedPreferences = getSharedPreferences("student", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("objectKey", serializedObject);
        editor.apply();
        editor.commit();

        Log.d("SAVED", serializedObject);
    }


}
