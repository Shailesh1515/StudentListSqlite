package com.example.studentlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StudentAddActivity extends AppCompatActivity {

    Spinner spinnerCourse;
    String[] courses = { "C++","c","Php","Kotlin", "Java", "Android", "Swift", "iOS", "Python"};
    ArrayAdapter<String> courseAdapter;

    EditText edName, edEmail, edAddress, edContact, edTotalFee, edFeePaid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);

        spinnerCourse = findViewById(R.id.spinnerCourse);
        courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        spinnerCourse.setAdapter(courseAdapter);

        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edAddress = findViewById(R.id.edAddress);
        edContact = findViewById(R.id.edContact);
        edTotalFee = findViewById(R.id.edTotalFee);
        edFeePaid = findViewById(R.id.edFeePaid);
    }

    public void process(View view) {

        switch (view.getId())
        {
            case R.id.btnSave:

                String name = edName.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    edName.setError("Please provide Name");
                    return;
                }

                String course = spinnerCourse.getSelectedItem().toString();

                String email = edEmail.getText().toString();
                if (TextUtils.isEmpty(email))
                {
                    edEmail.setError("Please provide Email");
                    return;
                }

                String address = edAddress.getText().toString();
                if (TextUtils.isEmpty(address))
                {
                    edAddress.setError("Please provide Address");
                    return;
                }

                String contact = edContact.getText().toString();
                if( TextUtils.isEmpty(contact))
                {
                    edContact.setError("Please provide contact number");
                    return;
                }
                String strTotalFee = edTotalFee.getText().toString();
                String strFeePaid = edFeePaid.getText().toString();

                int totalFee = Integer.parseInt(strTotalFee);
                int feePaid = Integer.parseInt(strFeePaid);

                DBHelper dbHelper = new DBHelper(this);

                Model model = new Model(name, email, address, course, contact, totalFee, feePaid);

                long result = dbHelper.addStudent(model);

                if( result != -1 )
                {
                    Toast.makeText(this, "Student Saved", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }

                break;
         /**   case R.id.btnShowAll:

                startActivity(new Intent(StudentAddActivity.this, StudentListActivity.class));
                break;**/
        }
    }
}
