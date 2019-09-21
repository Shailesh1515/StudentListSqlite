package com.example.studentlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    ListView lvStudents;
    List<Model> list;
    ArrayAdapter<Model> arrayAdapter;
    DBHelper dbHelper;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        button = (ImageButton)findViewById(R.id.fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentListActivity.this,StudentAddActivity.class);
                startActivity(i);
            }
        });

        lvStudents = findViewById(R.id.lvStudent);
        dbHelper = new DBHelper(this);



        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Model model = list.get(position);

                Intent intent = new Intent(StudentListActivity.this, StudentUpdateActivity.class);
                intent.putExtra("STUDENT", model);
                startActivity(intent);
            }
        });

        lvStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StudentListActivity.this);
                builder.setMessage("Are you sure to delete ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Model model = list.get(position);
                        int sid = model.getId();

                        int result = dbHelper.deleteStudent(sid);

                        if( result > 0)
                        {
                            Toast.makeText(StudentListActivity.this, "Student deleted", Toast.LENGTH_SHORT).show();
                            list.remove(model);
                            arrayAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(StudentListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                builder.setNegativeButton("No", null);
                builder.show();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list = dbHelper.getAllStudents();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvStudents.setAdapter(arrayAdapter);
    }
}

