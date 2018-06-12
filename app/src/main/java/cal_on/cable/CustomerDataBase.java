package cal_on.cable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cal_on on 12/26/2017.
 */

public class CustomerDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CustomerDB.db";
    public static final String CAT_TABLE_NAME = "customerdetails";
    public static final String CAT_COLUMN_ID = "id";
    public static final String CAT_COLUMN_NAME = "cusname";
    public static final String CAT_COLUMN_Village = "cusvillage";
    public static final String CAT_COLUMN_mobileNum = "cusmoblienum";
      public static final String CAT_COLUMN_SetUpBox = "box";
      public static final String CAT_COLUMN_Amount = "amount";
     /* public static final String CAT_COLUMN_ItemQuantity = "itemQuantity";
      public static final String CAT_COLUMN_ItemNAME = "itemname";*/
    private static final int DATABASE_VERSION = 1;



    private HashMap hp;

    public CustomerDataBase(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table customerdetails " +
                        "(id integer primary key, cusname text,cusvillage text,cusmoblienum text,box text,amount text)"
        );

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS customerdetails");
       // db.execSQL("DROP TABLE IF EXISTS item");
        onCreate(db);
    }

    public String insertContact(String cusname, String cusvillage, String cusmoblienum,String box, String amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cusname", cusname);
        contentValues.put("cusvillage", cusvillage);
        contentValues.put("cusmoblienum", cusmoblienum);
        contentValues.put("box", box);
        contentValues.put("amount", amount);

        db.insert("customerdetails", null, contentValues);
        return cusname;



    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from category where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CAT_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String cusname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cusname", cusname);
        db.update("category", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("category",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }


    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from category  ", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CAT_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getAllCotacts1()
    {
        ArrayList<String> array_list1 = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from category  ", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list1.add(res.getString(res.getColumnIndex(CAT_COLUMN_Village)));
            res.moveToNext();
        }
        return array_list1;
    }
    public ArrayList<String> getAllCotacts2()
    {
        ArrayList<String> array_list2 = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from category  ", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list2.add(res.getString(res.getColumnIndex(CAT_COLUMN_mobileNum)));
            res.moveToNext();
        }
        return array_list2;
    }
    public ArrayList<String> getAllCotacts3()
    {
        ArrayList<String> array_list3 = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from category  ", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list3.add(res.getString(res.getColumnIndex(CAT_COLUMN_SetUpBox)));
            res.moveToNext();
        }
        return array_list3;
    }
    public Cursor getdata(String Custname,SQLiteDatabase sqLiteDatabase){
        String[] add={CAT_COLUMN_Village,CAT_COLUMN_mobileNum,CAT_COLUMN_SetUpBox,CAT_COLUMN_Amount};
        String select=CAT_COLUMN_NAME+" LIKE ?";
        String[] selected={Custname};
        Cursor cursor=sqLiteDatabase.query(CAT_TABLE_NAME,add,select,selected,null,null,null);
        return  cursor;
    }
    public Cursor getdata1(String cusvillage,SQLiteDatabase sqLiteDatabase){
        String[] add={CAT_COLUMN_NAME,CAT_COLUMN_mobileNum,CAT_COLUMN_SetUpBox,CAT_COLUMN_Amount};
        String select=CAT_COLUMN_Village+" LIKE ?";
        String[] selected={cusvillage};
        Cursor cursor=sqLiteDatabase.query(CAT_TABLE_NAME,add,select,selected,null,null,null);
        return  cursor;
    }
    public Cursor getdata2(String cusmoblienum,SQLiteDatabase sqLiteDatabase){
        String[] add={CAT_COLUMN_NAME,CAT_COLUMN_Village,CAT_COLUMN_SetUpBox,CAT_COLUMN_Amount};
        String select=CAT_COLUMN_mobileNum+" LIKE ?";
        String[] selected={cusmoblienum};
        Cursor cursor=sqLiteDatabase.query(CAT_TABLE_NAME,add,select,selected,null,null,null);
        return  cursor;
    }
    public Cursor getdata3(String box,SQLiteDatabase sqLiteDatabase){
        String[] add={CAT_COLUMN_NAME,CAT_COLUMN_Village,CAT_COLUMN_mobileNum,CAT_COLUMN_Amount};
        String select=CAT_COLUMN_SetUpBox+" LIKE ?";
        String[] selected={box};
        Cursor cursor=sqLiteDatabase.query(CAT_TABLE_NAME,add,select,selected,null,null,null);
        return  cursor;
    }

}
