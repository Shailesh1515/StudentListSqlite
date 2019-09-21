package com.example.studentlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StudentUpdateActivity extends AppCompatActivity {

    EditText edName, edEmail, edAddress, edContact, edTotalFee, edFeePaid;
    Spinner spinnerCourse;
    String[] courses = { "C++","c","Php","Kotlin", "Java", "Android", "Swift", "iOS", "Python"};
    ArrayAdapter<String> courseAdapter;
    DBHelper dbHelper;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);


        dbHelper = new DBHelper(this);
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edAddress = findViewById(R.id.edAddress);
        edContact = findViewById(R.id.edContact);
        edTotalFee = findViewById(R.id.edTotalFee);
        edFeePaid = findViewById(R.id.edFeePaid);

        Model model = (Model) getIntent().getExtras().getSerializable("STUDENT");
        id = model.getId();
        edName.setText(model.getName());
        edEmail.setText(model.getEmail());
        edAddress.setText(model.getAddress());
        edContact.setText(model.getContact());
        edTotalFee.setText(String.valueOf(model.getTotalFee()));
        edFeePaid.setText(String.valueOf(model.getFeePaid()));

        spinnerCourse = findViewById(R.id.spinnerCourse);
        courseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);
        spinnerCourse.setAdapter(courseAdapter);


        for (int i = 0; i < courses.length; i++)
        {
            if( courses[i].equals(model.getCourse()))
            {
                spinnerCourse.setSelection(i);
                break;
            }
        }
    }

    public void update (View view) {

        String name = edName.getText().toString();
        String course = spinnerCourse.getSelectedItem().toString();
        String email = edEmail.getText().toString();
        String address = edAddress.getText().toString();
        String contact = edContact.getText().toString();
        String strTotalFee = edTotalFee.getText().toString();
        String strFeePaid = edFeePaid.getText().toString();

        int totalFee = Integer.parseInt(strTotalFee);
        int feePaid = Integer.parseInt(strFeePaid);

        Model model = new Model(id, name, email, address, course, contact, totalFee, feePaid);

        int result = dbHelper.updateStudent(model);

        if( result > 0)
        {
            Toast.makeText(this, "Student Updated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

    }
}
