package com.example.studentlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "intell.db";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_COURSE = "course";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_TOTAL_FEE = "total_fee";
    private static final String KEY_FEE_PAID = "fee_paid";

    private static final String TABLE_STUDENT = "tbl_student";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT NOT NULL,"
            + KEY_EMAIL + " TEXT NOT NULL,"
            + KEY_ADDRESS + " TEXT NOT NULL,"
            + KEY_COURSE + " TEXT NOT NULL,"
            + KEY_CONTACT + " TEXT NOT NULL,"
            + KEY_TOTAL_FEE + " INTEGER NOT NULL,"
            + KEY_FEE_PAID + " INTEGER NOT NULL )" ;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public long addStudent(Model model) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, model.getName() );
        values.put(KEY_EMAIL, model.getEmail());
        values.put(KEY_ADDRESS, model.getAddress());
        values.put(KEY_COURSE, model.getCourse());
        values.put(KEY_CONTACT, model.getContact());
        values.put(KEY_TOTAL_FEE, model.getTotalFee());
        values.put(KEY_FEE_PAID, model.getFeePaid());


        return db.insert(TABLE_STUDENT, null, values);
    }

    public List<Model> getAllStudents() {

        List<Model> studentList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_STUDENT, null);

        if(cursor.moveToFirst())
        {
            do
            {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString( 2);
                String address = cursor.getString(3);
                String course = cursor.getString(4);
                String contact = cursor.getString(5);
                int totalFee =cursor.getInt(6);
                int feePaid =cursor.getInt(7);

                Model model = new Model(id, name, email, address, course, contact, totalFee, feePaid);
                studentList.add(model);

            }while( cursor.moveToNext());
        }

        return studentList;
    }

    public int updateStudent(Model model) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, model.getName());
        values.put(KEY_EMAIL, model.getEmail());
        values.put(KEY_ADDRESS, model.getAddress());
        values.put(KEY_COURSE, model.getCourse());
        values.put(KEY_CONTACT, model.getContact());
        values.put(KEY_TOTAL_FEE, model.getTotalFee());
        values.put(KEY_FEE_PAID, model.getFeePaid());

        return db.update(TABLE_STUDENT, values, "id=?", new String[]{String.valueOf(model.getId())});
    }

    public int deleteStudent(int sid) {

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE_STUDENT, "id=?", new String[]{String.valueOf(sid)});
    }
}
