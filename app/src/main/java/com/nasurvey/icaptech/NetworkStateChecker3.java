package com.nasurvey.icaptech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nasurvey.icaptech.DBHelper.TABLE_NAME;
import static com.nasurvey.icaptech.DBHelper.TAG;

public class NetworkStateChecker3 extends BroadcastReceiver {
    private Context context;
    private DataBHelper db;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        db = new DataBHelper(context);

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
                                cursor.getInt(cursor.getColumnIndex(DataBHelper.COL1)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL55)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL2)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL3)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL4)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL5)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL54)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL6)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL7)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL8)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL9)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL10)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL11)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL12)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL13)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL14)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL15)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL16)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL17)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL18)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL19)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL20)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL21)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL22)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL23)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL24)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL25)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL26)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL27)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL28)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL29)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL30)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL31)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL32)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL33)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL34)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL35)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL36)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL37)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL38)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL39)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL40)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL41)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL42)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL43)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL44)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL45)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL46)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL47)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL48)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL49)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL50)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL51)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL52)),
                                cursor.getString(cursor.getColumnIndex(DataBHelper.COL53))
                        );
                    } while (cursor.moveToNext());
                }
                Log.d(TAG, "add data: adding ");
            }
        }
    }


    private void userSignUp(final int id, String agentNm, String fName, String sta, String localG, String tow, String phoneNm, String fmMr, String cbRg, String memSiz, String cggNam, String cFarmZz, String fcRg, String inFarm, String riceVarr, String qttyHarr, String afRg,
                            String fertTldd, String noRzz, String qtyApll, String alRg, String szLoaa, String srcRicc, String qtyPdyRicc, String majByrss, String comSolcc, String qttyByy, String howMcc, String prcBb, String incDs,
                            String reasnn, String hwSpyy, String stRg, String ysSptrr, String fm1, String t11, String cstCs11, String fm2, String t22, String cstCs22, String fm3, String t33, String cstCs33, String infPrcc, String salesDmm,
                            String avgQtFcc, String whnCp, String whyCp, String whnEx, String whyExx, String mjCh, String adCh, String timSe, String othSe, String adInn) {
        /** do user registration using api call **/
        Call<ResponseBody> call = RetrofitClient2
                .getInstance()
                .getNaSurvey()
                .submitResponse(agentNm, fName, sta, localG, tow, phoneNm, fmMr, cbRg, memSiz, cggNam, cFarmZz, fcRg, inFarm, riceVarr, qttyHarr, afRg, fertTldd, noRzz, qtyApll, alRg, szLoaa, srcRicc, qtyPdyRicc, majByrss, comSolcc, qttyByy, howMcc, prcBb, incDs, reasnn, hwSpyy, stRg, ysSptrr, fm1, t11, cstCs11,
                        fm2, t22, cstCs22, fm3, t33, cstCs33, infPrcc, salesDmm, avgQtFcc, whnCp, whyCp, whnEx, whyExx, mjCh, adCh, timSe, othSe, adInn);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    if (!obj.getBoolean("error")) {
                        //updating the status in sqlite
                        db.updateNameStatus(id, SurveyActivity.SYNC_STATUS_OK);

                        //sending the broadcast to refresh the list
                        context.sendBroadcast(new Intent(SurveyActivity.DATA_SAVED_BROADCAST));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Map<String, String> params = new HashMap<>();
                params.put("agentNm", agentNm);
                params.put("fname", fName);
                params.put("state", sta);
                params.put("lga", localG);
                params.put("town", tow);
                params.put("phoneNm", phoneNm);
                params.put("farmer", fmMr);
                params.put("cogp", cbRg);
                params.put("memsz", memSiz);
                params.put("cgnam", cggNam);
                params.put("cofmsz", cFarmZz);
                params.put("frmcol", fcRg);
                params.put("invfz", inFarm);
                params.put("ricev", riceVarr);
                params.put("qtyhv", qttyHarr);
                params.put("apfert", afRg);
                params.put("ysyld", fertTldd);
                params.put("norz", noRzz);
                params.put("qtyap", qtyApll);
                params.put("aploa", alRg);
                params.put("szloa", szLoaa);
                params.put("srcric", srcRicc);
                params.put("qtypdrc", qtyPdyRicc);
                params.put("majby", majByrss);
                params.put("comso", comSolcc);
                params.put("qtby", qttyByy);
                params.put("hwmch", howMcc);
                params.put("pricbf", prcBb);
                params.put("indcr", incDs);
                params.put("rsn", reasnn);
                params.put("hwspl", hwSpyy);
                params.put("chsp", stRg);
                params.put("yssptr", ysSptrr);
                params.put("frm1", fm1);
                params.put("to1", t11);
                params.put("costc1", cstCs11);
                params.put("frm2", fm2);
                params.put("to2", t22);
                params.put("costcs2", cstCs22);
                params.put("frm3", fm3);
                params.put("to3", t33);
                params.put("costcs3", cstCs33);
                params.put("infpr", infPrcc);
                params.put("salsdm", salesDmm);
                params.put("avqtf", avgQtFcc);
                params.put("wncp", whnCp);
                params.put("wycp", whyCp);
                params.put("wnex", whnEx);
                params.put("wyex", whyExx);
                params.put("majch", mjCh);
                params.put("addch", adCh);
                params.put("timsl",timSe);
                params.put("othsl",othSe);
                params.put("adin",adInn);

                return;
            }
        });

    }

}



