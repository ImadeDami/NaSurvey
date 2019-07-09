package com.nasurvey.icaptech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;

public class NetworkMonitor extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {

        if (checkNetworkConnection(context)) {

            final DBHelper dbHelper = new DBHelper(context);
            //SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = dbHelper.getData();
            while (cursor.moveToNext()) {
                int sync_status = cursor.getInt(cursor.getColumnIndex(DbContract.SYNC_STATUS));
                if (sync_status == DbContract.SYNC_STATUS_FAILED) {
                    final String fname = cursor.getString(cursor.getColumnIndex(DbContract.COL2));
                    final String sname = cursor.getString(cursor.getColumnIndex(DbContract.COL3));
                    final String gend = cursor.getString(cursor.getColumnIndex(DbContract.COL4));
                    final String marst = cursor.getString(cursor.getColumnIndex(DbContract.COL5));
                    final String phNo = cursor.getString(cursor.getColumnIndex(DbContract.COL6));

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_URL,
                            new com.android.volley.Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String Response = jsonObject.getString("response");
                                        if (Response.equals("OK")) {
                                            dbHelper.updateLocalDatabase(fname,sname,marst,gend,phNo,DbContract.SYNC_STATUS_OK);
                                            context.sendBroadcast(new Intent(DbContract.UI_UPDATE_BROADCAST));
                                        }
                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }

                                }
                            }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("fname", fname);
                            params.put("sname", sname);
                            params.put("gend", gend);
                            params.put("marst", marst);
                            params.put("phNo", phNo);
                            return params;
                        }
                    };
                    MySingleton.getInstance(context).addToRequestQueue(stringRequest);
                    dbHelper.close();
                }
            }



        }
       // if(checkNetworkConnection(context)) {



           /** Cursor cursor = dbHelper.getData();
            while (cursor.moveToNext()){
                int sync_status = cursor.getInt(cursor.getColumnIndex(DbContract.SYNC_STATUS));
                if(sync_status=DbContract.SYNC_STATUS_FAILED)
                {
                    String fname = cursor.getString(cursor.getColumnIndex(DbContract.NAME));
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_URL,
                            new Response.Listener<String>(){
                        @Override
                                public void onResponse(String response) {
                        }
                        }, new Response.ErrorListener(){
                        @Override
                        public void
                            });
                }
            }

        }
        } **/

    }
    public boolean checkNetworkConnection(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
