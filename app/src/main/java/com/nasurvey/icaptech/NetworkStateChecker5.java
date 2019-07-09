package com.nasurvey.icaptech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nasurvey.icaptech.DBHelper.COL1;
import static com.nasurvey.icaptech.DBHelper.COL2;
import static com.nasurvey.icaptech.DBHelper.COL3;
import static com.nasurvey.icaptech.DBHelper.COL4;
import static com.nasurvey.icaptech.DBHelper.SYNC_STATUS;
import static com.nasurvey.icaptech.DBHelper.TABLE_NAME;
import static com.nasurvey.icaptech.DBHelper.TAG;

public class NetworkStateChecker5 extends BroadcastReceiver {
    private Context context;
    private DBHelper db;
    public static final String TAG = "debug1";

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        db = new DBHelper(context);

        //db = new DBHelper(this);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        //if there is a network
        if (activeNetwork != null) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                //getting all the unsynced names
                Cursor cursor = db.getUnsyncedNames();
                if (cursor.moveToFirst()) {
                    do {
                        //calling the method to save the unsynced name to MySQL
                        userSignUp(
                                cursor.getInt(cursor.getColumnIndex(DBHelper.COL1)),
                                cursor.getString(cursor.getColumnIndex(DBHelper.COL2)),
                                cursor.getString(cursor.getColumnIndex(DBHelper.COL3)),
                                cursor.getString(cursor.getColumnIndex(DBHelper.COL4)),
                                cursor.getString(cursor.getColumnIndex(DBHelper.COL5))
                        );
                    } while (cursor.moveToNext());
                }
               // Log.d(TAG, "debug1 " + COL1 + ", " + COL2 + ", " + COL3 + ", " + COL4 + ", " + SYNC_STATUS + "  to " + TABLE_NAME);
                //Log.d(TAG, "debug1 " + COL1 + ", " + COL2 + ", " + COL3 + ", " + COL4 + ", " + SYNC_STATUS + "  to " + TABLE_NAME);
                Log.d(TAG, "debug1 " + activeNetwork + ", " + COL4 + ", " + COL2 + "  to " + TABLE_NAME);

            }
        }
    }

    private void userSignUp(final int id, String fName, String sName, String gndr, String marSt) {
        /** do user registration using api call **/
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MainActivity.URL_SAVE_NAME,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //updating the status in sqlite
                                db.updateNameStatus(id, MainActivity.SYNC_STATUS_OK);

                                //sending the broadcast to refresh the list
                                context.sendBroadcast(new Intent(MainActivity.DATA_SAVED_BROADCAST));
                            }
                        } catch (JSONException e) {
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
                params.put("fname", fName);
                params.put("sname", sName);
                params.put("gndr", gndr);
                params.put("marst", marSt);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

}


