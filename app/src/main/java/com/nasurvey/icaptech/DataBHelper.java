package com.nasurvey.icaptech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBHelper extends SQLiteOpenHelper {
    public static final String TAG = "DataBHelper";
    public static final String TABLE_NAME = "nasurvey6";
    public static final String COL1 = "id";
    public static final String COL2 = "FULLNAM";
    public static final String COL55 = "AGENTNAME";
    public static final String COL3 = "STATE";
    public static final String COL4 = "LGA";
    public static final String COL5 = "TOWN";
    public static final String COL54 = "PHONENUM";
    public static final String COL6 = "FARMMERC";

    public static final String COL7 = "COOPGROUP";
    public static final String COL8 = "MEMSIZE";
    public static final String COL9 = "CGNAME";
    public static final String COL10 = "COOPFARMSZ";
    public static final String COL11 = "FARMCOLL";

    public static final String COL12 = "INDVFARMSZ";
    public static final String COL13 = "RICEVAR";
    public static final String COL14 = "QTYHARV";
    public static final String COL15 = "APPLYFERT";
    public static final String COL16 = "YESYIELD";

    public static final String COL17 = "NOREASON";
    public static final String COL18 = "QTYAPPLY";
    public static final String COL19 = "APPLYLOAN";
    public static final String COL20 = "SZLOAN";
    public static final String COL21 = "SRCRICE";

    public static final String COL22 = "QTYPDYRICE";
    public static final String COL23 = "MAJORBUYERS";
    public static final String COL24 = "COMESOLICIT";
    public static final String COL25 = "QTTYBUY";
    public static final String COL26 = "HOWMUCH";

    public static final String COL27 = "PRICEBF";
    public static final String COL28 = "INCRDESC";
    public static final String COL29 = "REASON";
    public static final String COL30 = "HOWSUPPLY";
    public static final String COL31 = "CHRGSEP";
    public static final String COL32 = "YESSEPTRANS";

    public static final String COL33 = "FROM1";
    public static final String COL34 = "TO1";
    public static final String COL35 = "COSTCS1";
    public static final String COL36 = "FROM2";

    public static final String COL37 = "TO2";
    public static final String COL38 = "COSTCS2";
    public static final String COL39 = "FROM3";
    public static final String COL40 = "TO3";
    public static final String COL41 = "COSTCS3";

    public static final String COL42 = "INFLPRICE";
    public static final String COL43 = "SALSDEM";
    public static final String COL44 = "AVGQTYFAC";
    public static final String COL45 = "WHENCHEAPEST";
    public static final String COL46 = "WHYCHEAP";

    public static final String COL47 = "WHENEXP";
    public static final String COL48 = "WHYEXP";
    public static final String COL49 = "MAJORCHALL";
    public static final String COL50 = "ADDRCHALL";
    public static final String COL51 = "TIMESELL";
    public static final String COL52 = "OTHERSELL";
    public static final String COL53 = "ADDINFO";
    public static final String SYNC_STATUS = "syncstatus";

    public DataBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "AGENTNAME TEXT, FULLNAM TEXT, STATE TEXT, LGA TEXT, TOWN TEXT, PHONENUM TEXT, FARMMERC TEXT, COOPGROUP TEXT, MEMSIZE TEXT, CGNAME TEXT, COOPFARMSZ TEXT, FARMCOLL TEXT, INDVFARMSZ TEXT, RICEVAR TEXT, QTYHARV TEXT, APPLYFERT TEXT, " +
                "YESYIELD TEXT, NOREASON TEXT, QTYAPPLY TEXT, APPLYLOAN TEXT, SZLOAN TEXT, SRCRICE TEXT, QTYPDYRICE TEXT, MAJORBUYERS TEXT, COMESOLICIT TEXT, QTTYBUY TEXT, HOWMUCH TEXT, PRICEBF TEXT, INCRDESC TEXT, REASON TEXT, HOWSUPPLY TEXT, CHRGSEP TEXT, YESSEPTRANS TEXT, FROM1 TEXT, TO1 TEXT, COSTCS1 TEXT," +
                "FROM2 TEXT, TO2 TEXT, COSTCS2 TEXT, FROM3 TEXT, TO3 TEXT, COSTCS3 TEXT, INFLPRICE TEXT, SALSDEM TEXT, AVGQTYFAC TEXT, WHENCHEAPEST TEXT, WHYCHEAP TEXT, WHENEXP TEXT, WHYEXP TEXT, MAJORCHALL TEXT, ADDRCHALL TEXT, TIMESELL TEXT, OTHERSELL TEXT, ADDINFO TEXT, syncstatus integer)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String fName, String agentNm, String sta, String localG, String tow, String phoneNm, String fmMr, String cbRg, String memSiz, String cggNam, String cFarmZz, String fcRg, String inFarm, String riceVarr, String qttyHarr, String afRg,
                           String fertTldd, String noRzz, String qtyApll, String alRg, String szLoaa, String srcRicc, String qtyPdyRicc, String majByrss, String comSolcc, String qttyByy, String howMcc, String prcBb, String incDs,
                           String reasnn, String hwSpyy, String stRg, String ysSptrr, String fm1, String t11, String cstCs11, String fm2, String t22, String cstCs22, String fm3, String t33, String cstCs33, String infPrcc, String salesDmm,
                           String avgQtFcc, String whnCp, String whyCp, String whnEx, String whyExx, String mjCh, String adCh, String timSe, String othSe, String adInn, int sync_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, fName);
        contentValues.put(COL55, agentNm);
        contentValues.put(COL3, sta);
        contentValues.put(COL4, localG);
        contentValues.put(COL5, tow);
        contentValues.put(COL54, phoneNm);
        contentValues.put(COL6, fmMr);
        contentValues.put(COL7, cbRg);
        contentValues.put(COL8, memSiz);
        contentValues.put(COL9, cggNam);
        contentValues.put(COL10, cFarmZz);
        contentValues.put(COL11, fcRg);
        contentValues.put(COL12, inFarm);
        contentValues.put(COL13, riceVarr);
        contentValues.put(COL14, qttyHarr);
        contentValues.put(COL15, afRg);
        contentValues.put(COL16, fertTldd);
        contentValues.put(COL17, noRzz);
        contentValues.put(COL18, qtyApll);
        contentValues.put(COL19, alRg);
        contentValues.put(COL20, szLoaa);
        contentValues.put(COL21, srcRicc);
        contentValues.put(COL22, qtyPdyRicc);
        contentValues.put(COL23, majByrss);
        contentValues.put(COL24, comSolcc);
        contentValues.put(COL25, qttyByy);
        contentValues.put(COL26, howMcc);
        contentValues.put(COL27, prcBb);
        contentValues.put(COL28, incDs);
        contentValues.put(COL29, reasnn);
        contentValues.put(COL30, hwSpyy);
        contentValues.put(COL31, stRg);
        contentValues.put(COL32, ysSptrr);
        contentValues.put(COL33, fm1);
        contentValues.put(COL34, t11);
        contentValues.put(COL35, cstCs11);
        contentValues.put(COL36, fm2);
        contentValues.put(COL37, t22);
        contentValues.put(COL38, cstCs22);
        contentValues.put(COL39, fm3);
        contentValues.put(COL40, t33);
        contentValues.put(COL41, cstCs33);
        contentValues.put(COL42, infPrcc);
        contentValues.put(COL43, salesDmm);
        contentValues.put(COL44, avgQtFcc);
        contentValues.put(COL45, whnCp);
        contentValues.put(COL46, whyCp);
        contentValues.put(COL47, whnEx);
        contentValues.put(COL48, whyExx);
        contentValues.put(COL49, mjCh);
        contentValues.put(COL50, adCh);
        contentValues.put(COL51, timSe);
        contentValues.put(COL52, othSe);
        contentValues.put(COL53, adInn);
        contentValues.put(SYNC_STATUS, sync_status);

        Log.d(TAG, "add data: adding " + fName + ", " + sta + ", " + localG + ", " + tow + ", " + sync_status + "  to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data is inserted correctly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * return all data from database
     **/

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        /*if(data.getCount()>0){ //new down
            if(data.moveToFirst()){
                do {

                }while (data.moveToNext());
            }
        }*/
        return data;
    }


    public boolean updateNameStatus(int id, int sync_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SYNC_STATUS, sync_status);
        db.update(TABLE_NAME, contentValues, COL1 + "=" + id, null);
        db.close();
        return true;
    }

    /*
     * this method is for getting all the unsynced name
     * so that we can sync it with database
     * */
    public Cursor getUnsyncedNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + SYNC_STATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public List<String> getAllLabels() {
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
             do {
               labels.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }

            // closing connection
            cursor.close();
            db.close();

            // returning lables
            return labels;
        }

    public List<String> getLG() {
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(4));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    public List<String> getAgentName() {
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }


}
