package com.androidlab.trainingcenterapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper{

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE INSTRUCTOR (EMAIL TEXT PRIMARY KEY, " +
                "FNAME TEXT,  LNAME TEXT, PASSWORD TEXT, PHOTO BLOB, NUMBER TEXT DEFAULT 'NULL', " +
                "ADDRESS TEXT DEFAULT 'NULL', SPECIALIZATION TEXT DEFAULT 'NULL', " +
                "DEGREE TEXT DEFAULT 'NULL', COURSESTOTEACH TEXT DEFAULT 'NULL')");

        sqLiteDatabase.execSQL("CREATE TABLE TRAINEE (EMAIL TEXT PRIMARY KEY, " +
                "FNAME TEXT,  LNAME TEXT, PASSWORD TEXT, PHOTO BLOB, NUMBER TEXT DEFAULT 'NULL', " +
                "ADDRESS TEXT DEFAULT 'NULL')");

        sqLiteDatabase.execSQL("CREATE TABLE ADMIN (EMAIL TEXT PRIMARY KEY, " +
                "FNAME TEXT,  LNAME TEXT, PASSWORD TEXT, PHOTO BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertInstructor(InstructorUser user) throws IOException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FNAME", user.getFirstName());
        contentValues.put("LNAME", user.getLastName());
        contentValues.put("PASSWORD", user.getPassword());

        // To store the image in the database
        FileInputStream in = new FileInputStream(user.getPhoto().toString());
        BufferedInputStream buf = new BufferedInputStream(in);
        byte[] bMapArray= new byte[buf.available()];
        buf.read(bMapArray);
        Bitmap bMap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
        byte[] data = getBitmapAsByteArray(bMap);
        contentValues.put("PHOTO", data);

        contentValues.put("NUMBER", user.getMobileNumber());
        contentValues.put("ADDRESS", user.getAddress());
        contentValues.put("SPECIALIZATION", user.getSpecialization());
        contentValues.put("DEGREE", user.getDegree());
        contentValues.put("COURSESTOTEACH", user.getCoursesCanTeach());

        if(sqLiteDatabase.insert("INSTRUCTOR", null, contentValues) > -1)
            return true;
        return false;
    }

    public boolean insertAdmin(AdminUser user) throws IOException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FNAME", user.getFirstName());
        contentValues.put("LNAME", user.getLastName());
        contentValues.put("PASSWORD", user.getPassword());

        // To store the image in the database
        FileInputStream in = new FileInputStream(user.getPhoto().toString());
        BufferedInputStream buf = new BufferedInputStream(in);
        byte[] bMapArray= new byte[buf.available()];
        buf.read(bMapArray);
        Bitmap bMap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
        byte[] data = getBitmapAsByteArray(bMap);
        contentValues.put("PHOTO", data);

        if(sqLiteDatabase.insert("ADMIN", null, contentValues) > -1)
            return true;
        return false;
    }

    public boolean insertTrainee(TraineeUser user) throws IOException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FNAME", user.getFirstName());
        contentValues.put("LNAME", user.getLastName());
        contentValues.put("PASSWORD", user.getPassword());

        // To store the image in the database
        FileInputStream in = new FileInputStream(user.getPhoto().toString());
        BufferedInputStream buf = new BufferedInputStream(in);
        byte[] bMapArray= new byte[buf.available()];
        buf.read(bMapArray);
        Bitmap bMap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
        byte[] data = getBitmapAsByteArray(bMap);
        contentValues.put("PHOTO", data);

        contentValues.put("NUMBER", user.getMobileNumber());
        contentValues.put("ADDRESS", user.getAddress());

        if(sqLiteDatabase.insert("TRAINEE", null, contentValues) > -1)
            return true;
        return false;
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
