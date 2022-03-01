package com.example.showroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "LILL.db";


    public static final String TABLE_NAME = "cars_main_table"; //cars
    public static final String COL_1 = "CAR_ID";
    public static final String COL_2 = "MODEL";
    public static final String COL_3 = "COMPANY";
    public static final String COL_4 = "TYPE";

    public static final String TABLE_NAME_1 = "SP_TABLE"; // Specification
    public static final String COL1 = "CAR_ID";
    public static final String COL2 = "MODEL";
    public static final String COL3 = "MILEAGE";
    public static final String COL4 = "Engine_cc";
    public static final String COL5 = "Fuel";
    public static final String COL6 = "Trans";
    public static final String COL7 = "Seater";
    public static final String COL8 = "Color";

    public static final String TABLE_NAME_2 = "Show_table"; //Showroom Table
    public static final String COL12 = "Showroom_ID";
    public static final String COL22 = "Company";
    public static final String COL32 = "Location";
    public static final String COL42 = "Address";
    public static final String COL52 = "Pincode";
    public static final String COL62 = "PHno";

    public static final String TABLE_NAME_3 = "emp_table"; //Employee Table
    public static final String COL11 = "SSN";
    public static final String COL21 = "F_NAME";
    public static final String COL31 = "L_NAME";
    public static final String COL41 = "COMPANY";
    public static final String COL51 = "Gender";
    public static final String COL61 = "Showroom_ID";

    public static final String TABLE_NAME_4 = "Price_table"; // Pricing
    public static final String COL13 = "CAR_ID";
    public static final String COL23 = "Showroom_Price";
    public static final String COL33 = "Road_TAX";
    public static final String COL43 = "OtherTAX";
    public static final String COL53 = "TOTAL";

    public static final String TABLE_NAME_5 = "rating_table"; //Rating Table
    public static final String COL14 = "car_id";
    public static final String COL24 = "Model";
    public static final String COL34 = "Comfort";
    public static final String COL44 = "Performance";
    public static final String COL54 = "Fuel_Economy";
    public static final String COL64 = "Value_for_Money";
    public static final String COL74 = "Exterior";


    public static final String TABLE_NAME_6 = "Images_table"; // Images Table
    public static final String COL15= "car_id";
    public static final String COL25 = "images";
    public static final String COL35 = "videos";

    public static final String TABLE_NAME_7 = "test_drive"; // Test_Drive Table
    public static final String COL16= "cust_id";
    public static final String COL26 = "Fname";
    public static final String COL36 = "Lname";
    public static final String COL46= "Date";
    public static final String COL56 = "DL_NO";
    public static final String COL66 = "Ph_no";
    public static final String COL76 = "Showroom";
    public static final String COL86 = "timming";
    public static final String COL96 = "Model";
    public static final String COL101 = "Company";
    public static final String COL102 = "SSN";
    public static final String COL103 = "Showroom_id";

    public static final String TABLE_NAME_8 = "Car_driving"; // Test_Drive Table
    public static final String COL18= "cust_id";
    public static final String COL28 = "Fname";
    public static final String COL38 = "Lname";
    public static final String COL48= "Date";
    public static final String COL58 = "DL_NO";
    public static final String COL68 = "Ph_no";
    public static final String COL78 = "Showroom";
    public static final String COL88 = "timming";
    public static final String COL98 = "Model";
    public static final String COL1001 = "Company";
    public static final String COL1002 = "emp_id";
    public static final String COL1003 = "Showroom_id";

    public DBHelper(Context context) {
        super(context, DATABASE,null,10);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(CAR_ID INTEGER PRIMARY KEY ,MODEL TEXT,COMPANY TEXT,TYPE TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_NAME_1 + "(" + COL1
                + " INTEGER PRIMARY KEY, "
                + COL2 + " text not null,"
                + COL3 + " text not null, "
                + COL4 + " text not null, "
                + COL5 + " text not null, "
                + COL6 + " text not null, "
                + COL7 + " text not null, "
                + COL8 + " text not null, "
                + " FOREIGN KEY (" + COL1 + ") REFERENCES " + TABLE_NAME + "(" + COL_1 + "));");

        db.execSQL("CREATE TABLE " + TABLE_NAME_2 + "(" + COL12
                + " INTEGER PRIMARY KEY, "
                + COL22 + " text not null,"
                + COL32 + " text not null, "
                + COL42 + " text not null, "
                + COL52 + " text not null, "
                + COL62 + " text not null)");

        db.execSQL("CREATE TABLE " + TABLE_NAME_3 + "(" + COL11
                + " INTEGER PRIMARY KEY, "
                + COL21 + " text not null,"
                + COL31 + " text not null,"
                + COL41 + " text not null, "
                + COL51 + " text not null, "
                + COL61 + " INTEGER ,"
                + " FOREIGN KEY (" + COL61 + ") REFERENCES " + TABLE_NAME_2 + "(" + COL12 + "));");

        db.execSQL("CREATE TABLE " + TABLE_NAME_4 + "(" + COL13
                + " INTEGER PRIMARY KEY, "
                + COL23 + " INTEGER not null,"
                + COL33 + " INTEGER not null, "
                + COL43 + " INTEGER not null, "
                + COL53 + " BIGINT not null,"
                + " FOREIGN KEY (" + COL1 + ") REFERENCES " + TABLE_NAME + "(" + COL_1 + "));");

        db.execSQL("CREATE TABLE " + TABLE_NAME_5 + "(" + COL14
                + " INTEGER PRIMARY KEY, "
                + COL24 + " text not null,"
                + COL34 + " text not null, "
                + COL44 + " text not null, "
                + COL54 + " text not null, "
                + COL64 + " text not null, "
                + COL74 + " text not null, "
                + " FOREIGN KEY (" + COL14 + ") REFERENCES " + TABLE_NAME + "(" + COL_1 + "));");

        db.execSQL("CREATE TABLE " + TABLE_NAME_6 + "(" + COL15
                + " INTEGER PRIMARY KEY, "
                + COL25 + " text not null,"
                + COL35 + " text not null,"
                + " FOREIGN KEY (" + COL15 + ") REFERENCES " + TABLE_NAME + "(" + COL_1 + "));");

        db.execSQL("CREATE TABLE " + TABLE_NAME_7 + "(" + COL16
                + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + COL26 + " text not null,"
                + COL36 + " text not null, "
                + COL46 + " text not null, "
                + COL56 + " text not null, "
                + COL66 + " text not null, "
                + COL76 + " text not null, "
                + COL86 + " text not null, "
                + COL96 + " text not null, "
                + COL101 + " text not null, "
                + COL102 + "INTEGER ,"
                + COL103 + "INTEGER , "
                + " FOREIGN KEY (" + COL102 + ") REFERENCES " + TABLE_NAME_3 + "(" + COL11 + "), "
                + " FOREIGN KEY (" + COL103 + ") REFERENCES " + TABLE_NAME_2 + "(" + COL12 + "));");

        db.execSQL("CREATE TABLE " + TABLE_NAME_8 + "(" + COL18
                + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + COL28 + " text not null,"
                + COL38 + " text not null, "
                + COL48 + " text not null, "
                + COL58 + " text not null, "
                + COL68 + " text not null, "
                + COL78 + " text not null, "
                + COL88 + " text not null, "
                + COL98 + " text not null, "
                + COL1001 + " text not null, "
                + COL1002 + "INTEGER ,"
                + COL1003 + "INTEGER , "
                + " FOREIGN KEY (" + COL1002 + ") REFERENCES " + TABLE_NAME_3 + "(" + COL11 + "), "
                + " FOREIGN KEY (" + COL1003 + ") REFERENCES " + TABLE_NAME_2 + "(" + COL12 + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_4);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_5);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_6);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_7);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_8);
        onCreate(db);
    }


    // Car Table
   public boolean InsertCar(Integer car_id,String model,String company,String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,car_id);
        contentValues.put(COL_2,model);
        contentValues.put(COL_3,company);
        contentValues.put(COL_4,type);
        long res = db.insert(TABLE_NAME,null,contentValues);
       return res != -1;
    }

    public Cursor getCar() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public boolean UpdateCar(Integer car_id,String model,String company,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,car_id);
        contentValues.put(COL_2,model);
        contentValues.put(COL_3,company);
        contentValues.put(COL_4,type);
        long res = db.update(TABLE_NAME,contentValues,"car_id = ?",new String[] {car_id.toString()});
        if(res == -1){
            return false;
        }else
            return true;
    }

    public Integer deleteCar(Integer car_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"car_id = ?",new String[] {car_id.toString()});
    }


    // Spec Table
    public boolean InsertSpec(Integer car_id ,String model,String  mileage,String engine,String fuel,String trans,String seater,String color){
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,car_id);
        contentValues.put(COL2,model);
        contentValues.put(COL3,mileage);
        contentValues.put(COL4,engine);
        contentValues.put(COL5,fuel);
        contentValues.put(COL6,trans);
        contentValues.put(COL7,seater);
        contentValues.put(COL8,color);
        long res = db1.insert(TABLE_NAME_1,null,contentValues);
        if(res == -1){
            return false;
        }else
            return true;
    }

    public Cursor getSpec() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_1,null);
        return res;
    }


    public boolean Updatespec(Integer car_id ,String model,String  mileage,String engine,String fuel,String trans,String seater,String color){
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,car_id);
        contentValues.put(COL2,model);
        contentValues.put(COL3,mileage);
        contentValues.put(COL4,engine);
        contentValues.put(COL5,fuel);
        contentValues.put(COL6,trans);
        contentValues.put(COL7,seater);
        contentValues.put(COL8,color);
        long res = db1.update(TABLE_NAME_1,contentValues,"car_id = ?",new String[] {car_id.toString()});
        if(res == -1){
            return false;
        }else
            return true;
    }

    public Integer deletespec(Integer car_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NAME_1,"car_id=?",new String[] {car_id.toString()});
    }

    // Employee Table
    public boolean InsertEmp(Integer ssn,String fname,String lname,String company,String gender,Integer showroon_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL11,ssn);
        contentValues.put(COL21,fname);
        contentValues.put(COL31,lname);
        contentValues.put(COL41,company);
        contentValues.put(COL51,gender);
        contentValues.put(COL61,showroon_no);
        long res = db.insert(TABLE_NAME_3,null,contentValues);
        if(res == -1){
            return false;
        }else
            return true;
    }
    public Cursor getEmp() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_3,null);
        return res;
    }
    public boolean UpdateEmp(Integer ssn,String fname,String lname,String company,String gender,Integer showroon_no) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL11,ssn);
        contentValues.put(COL21,fname);
        contentValues.put(COL31,lname);
        contentValues.put(COL41,company);
        contentValues.put(COL51,gender);
        contentValues.put(COL61, showroon_no);
        long res = db.update(TABLE_NAME_3, contentValues, "ssn = ?", new String[]{ssn.toString()});
        if (res == -1) {
            return false;
        }
        return true;
    }
    public Boolean deleteEmp(Integer ssn) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME_3+" where ssn = ?", new String[]{ssn.toString()});
        if(cursor.getCount() > 0) {
            long result = db.delete(TABLE_NAME_3,"ssn = ?", new String[]{ssn.toString()});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    // Showroom Table
    public Boolean InsertShow(Integer Showroom_id,String Company,String Location,String Address,String Pincode,String PHno)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL12,Showroom_id);
        contentValues.put(COL22,Company);
        contentValues.put(COL32,Location);
        contentValues.put(COL42,Address);
        contentValues.put(COL52,Pincode);
        contentValues.put(COL62,PHno);
        long result=DB.insert(TABLE_NAME_2,null,contentValues);
        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }
    public Cursor getShow() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_2,null);
        return res;
    }

    public boolean UpdateShow(Integer Showroom_id,String Company,String Location,String Address,String Pincode,String PHno)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL12,Showroom_id);
        contentValues.put(COL22,Company);
        contentValues.put(COL32,Location);
        contentValues.put(COL42,Address);
        contentValues.put(COL52,Pincode);
        contentValues.put(COL62,PHno);
        long res = DB.update(TABLE_NAME_2,contentValues,"Showroom_id = ?",new String[] {Showroom_id.toString()});
        if(res == -1){
            return false;
        }else
            return true;
    }

    public Integer deleteShow(Integer showroom_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NAME_2,"showroom_id=?",new String[] {showroom_id.toString()});
    }

    // Price Table
    public Boolean InsertPrice(Integer car_id,Integer Showroom_Price,Integer road_tax,Integer other_tax)
    {
        float per = (((Integer)road_tax + (Integer)other_tax)*(float)0.01) * Showroom_Price;
        float total = per + Showroom_Price;
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL13,car_id);
        contentValues.put(COL23,Showroom_Price);
        contentValues.put(COL33,road_tax);
        contentValues.put(COL43,other_tax);
        contentValues.put(COL53,total);
        long result=DB.insert(TABLE_NAME_4,null,contentValues);
        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }
    public Cursor getPrice() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_4,null);
        return res;
    }

    public boolean UpdatePrice(Integer car_id,Integer Showroom_Price,Integer road_tax,Integer other_tax)
    {
        float per = (((Integer)road_tax + (Integer)other_tax)*(float)0.01) * Showroom_Price;
        float total = per + Showroom_Price;
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL13,car_id);
        contentValues.put(COL23,Showroom_Price);
        contentValues.put(COL33,road_tax);
        contentValues.put(COL43,other_tax);
        contentValues.put(COL53,total);
        long res = DB.update(TABLE_NAME_4,contentValues,"car_id  = ?",new String[] {car_id.toString()});
        if(res == -1){
            return false;
        }else
            return true;
    }

    public Integer deletePrice(Integer car_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NAME_4,"car_id=?",new String[] {car_id.toString()});
    }

    //Rating Table

    public boolean insertRating(Integer car_id,String model,String comfort,String performance,String fuel_economy,String value_for_money,String exterior){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL14,car_id);
        contentValues.put(COL24,model);
        contentValues.put(COL34,performance);
        contentValues.put(COL44,comfort);
        contentValues.put(COL54,fuel_economy);
        contentValues.put(COL64,value_for_money);
        contentValues.put(COL74,exterior);
        long res = db.insert(TABLE_NAME_5,null,contentValues);
        if(res == -1){
            return false;
        }else
            return true;
    }

    public Cursor getRating(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_5,null);
        return res;
    }

    public boolean updateRating(Integer car_id,String model,String comfort,String performance,String fuel_economy,String value_for_money,String exterior){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL14,car_id);
        contentValues.put(COL24,model);
        contentValues.put(COL34,performance);
        contentValues.put(COL44,comfort);
        contentValues.put(COL54,fuel_economy);
        contentValues.put(COL64,value_for_money);
        contentValues.put(COL74,exterior);
        long res = db.update(TABLE_NAME_5,contentValues,"car_id = ?",new String[] {car_id.toString()});
        if(res == -1){
            return false;
        }else
            return true;
    }

    public Integer deleteRating(Integer car_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_5,"car_id = ?",new String[] {car_id.toString()});
    }

    //Images Table

    public boolean insertImg(Integer car_id,String images,String videos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL15,car_id);
        contentValues.put(COL25,images);
        contentValues.put(COL35,videos);
        long res = db.insert(TABLE_NAME_6,null,contentValues);
        if(res == -1){
            return false;
        }else
            return true;
    }
    public Cursor getImg(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_6,null);
        return res;
    }

    public boolean updateImg(Integer car_id,String images,String videos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL15,car_id);
        contentValues.put(COL25,images);
        contentValues.put(COL35,videos);
        long res = db.update(TABLE_NAME_6,contentValues,"car_id = ?",new String[] {car_id.toString()});
        if(res == -1){
            return false;
        }else
            return true;
    }

    public Integer deleteImg(Integer car_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_6,"car_id = ?",new String[] {car_id.toString()});
    }

    //Test-Drive Table

    public Boolean InsertTest_Drive(String Fname,String Lname,String Date,String DL,String PHno,String Showroom_name,String timmings,String Model,String Company,String Emp_id,String Showroom_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL26,Fname);
        contentValues.put(COL36,Lname);
        contentValues.put(COL46,Date);
        contentValues.put(COL56,DL);
        contentValues.put(COL66,PHno);
        contentValues.put(COL76,Showroom_name);
        contentValues.put(COL86,timmings);
        contentValues.put(COL96,Model);
        contentValues.put(COL101,Company);
        contentValues.put(COL102,Emp_id);
        contentValues.put(COL103,Showroom_id);
        long result = db.insert(TABLE_NAME_7,null,contentValues);
        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public Cursor getApp() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_7,null);
        return res;
    }

    public Integer delv(Integer cust_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_7,"cust_id = ?",new String[] {cust_id.toString()});
    }

    // Car_booking
    public Boolean InsertCar_booking(String Fname,String Lname,String Date,String DL,String PHno,String Showroom_name,String timmings,String Model,String Company,String Emp_id,String Showroom_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL28,Fname);
        contentValues.put(COL38,Lname);
        contentValues.put(COL48,Date);
        contentValues.put(COL58,DL);
        contentValues.put(COL68,PHno);
        contentValues.put(COL78,Showroom_name);
        contentValues.put(COL88,timmings);
        contentValues.put(COL98,Model);
        contentValues.put(COL1001,Company);
        contentValues.put(COL1002,Emp_id);
        contentValues.put(COL1003,Showroom_id);
        long result = db.insert(TABLE_NAME_8,null,contentValues);
        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public Cursor getbook() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME_8,null);
        return res;
    }

    public Integer delbook(Integer cust_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_8,"cust_id = ?",new String[] {cust_id.toString()});
    }


    // QUERY PART
    public Cursor get_detail(){
        String MY_QUERY = "SELECT a.CAR_ID,a.MODEL,a.COMPANY,a.TYPE ,b.Total,c.images " +
                          " FROM "+TABLE_NAME + " a INNER JOIN "+TABLE_NAME_6+" c ON a.CAR_ID=c.car_id "+
                            "INNER JOIN "+TABLE_NAME_4+" b ON a.CAR_ID=b.CAR_ID";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(MY_QUERY,new String[] {});
        return res;
    }


    public Cursor get_detail1(String data){
        String MY_QUERY = "SELECT a.CAR_ID,a.MODEL,a.COMPANY,a.TYPE ,b.Total,c.images " +
                " FROM "+TABLE_NAME + " a INNER JOIN "+TABLE_NAME_6+" c ON a.CAR_ID=c.car_id "+
                "INNER JOIN "+TABLE_NAME_4+" b ON a.CAR_ID=b.CAR_ID WHERE a.COMPANY = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(MY_QUERY,new String[] {data});
        return res;
    }
    public Cursor get_detail2(String data){
        String MY_QUERY = "SELECT a.CAR_ID,a.MODEL,a.COMPANY,a.TYPE ,b.Total,c.images " +
                " FROM "+TABLE_NAME + " a INNER JOIN "+TABLE_NAME_6+" c ON a.CAR_ID=c.car_id "+
                " LEFT JOIN "+TABLE_NAME_4+" b ON a.CAR_ID=b.CAR_ID WHERE a.TYPE = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(MY_QUERY,new String[] {data});
        return res;
    }

    public Cursor get_detail_main(String data,String data2){
        String MY_QUERY = "SELECT a.CAR_ID,a.MODEL,a.COMPANY,a.TYPE ,b.Total,c.images " +
                " FROM "+TABLE_NAME + " a INNER JOIN "+TABLE_NAME_6+" c ON a.CAR_ID=c.car_id "+
                "INNER JOIN "+TABLE_NAME_4+" b ON a.CAR_ID=b.CAR_ID WHERE a.TYPE = ? AND a.COMPANY = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(MY_QUERY,new String[] {data,data2});
        return res;
    }

    public Cursor get_detail_car(String data){
        String MY_QUERY = "SELECT a.MODEL,a.COMPANY,a.TYPE ,c.videos,d."+COL3+",d."+COL4+",d."+COL5+",d."+COL6+",d."+COL7+",d."+COL8+",b.Total"+
                " FROM "+TABLE_NAME + " a LEFT JOIN "+TABLE_NAME_6+" c ON a.CAR_ID=c.car_id "+
                "LEFT JOIN "+TABLE_NAME_4+" b ON a.CAR_ID=b.CAR_ID " +
                "LEFT JOIN " +TABLE_NAME_1+" d ON a.CAR_ID=d.CAR_ID WHERE a.CAR_ID = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(MY_QUERY,new String[] {data});
        return res;
    }

    public Cursor Book_test(String data,String data1){
        String MY_QUERY = "SELECT a.Showroom_ID,b.SSN ,c.MODEL,c.COMPANY,c.TYPE FROM "
                +TABLE_NAME_2 + " a LEFT JOIN "+TABLE_NAME_3+" b ON a.Showroom_ID = b.Showroom_ID " +
                "LEFT JOIN "+TABLE_NAME+" c WHERE Location = ? AND CAR_ID = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(MY_QUERY,new String[] {data,data1});
        return res;
    }

    public Cursor Final(String data,String data1){
        String MY_QUERY = "SELECT a.F_NAME,a.L_NAME,b.Address,b.Pincode ,b.PHno FROM "
                +TABLE_NAME_3 +" a LEFT JOIN "+TABLE_NAME_2+" b  WHERE b.Showroom_ID = ? AND a.SSN = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(MY_QUERY,new String[] {data,data1});
        return res;
    }

    public Cursor rating(String car_id){
        String MY_QUERY = "SELECT Comfort, Performance ,Fuel_Economy, Value_for_Money , Exterior FROM " +TABLE_NAME_5 + " WHERE car_id = ? ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(MY_QUERY,new String[] {car_id});
        return res;
    }
}


