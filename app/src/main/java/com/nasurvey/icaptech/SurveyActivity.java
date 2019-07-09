package com.nasurvey.icaptech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyActivity extends AppCompatActivity implements View.OnClickListener {

    EditText agN, fullNam, stat, lg, twn, phNo, farmMSPin, cooGpRG1, mmSz, cgNam, cFarmZ, farmCoRG2, iFarmZ, riceVr, qtyHar, applyFerRG3, fertYld, noRz, qtyApl, applyLoaRG4, szLoa, srcRic;
    EditText qtyPdyRic, majorByrs, comeSolct, qttyBy, howMuc, priceB, incrDsc, reasn, howSply, chrgSpGB5, ysSpTr, frm1, t1, cstCS1, frm2, t2, cstCS2, frm3, t3, cstCS3, inflPric;
    EditText salesDm, avgQtyFc, whenChp, whyChp, whenEx, whyEx, majorCha, addrCha, timeSe, otherSe, adIn;
    RadioGroup radioGroup, radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    RadioButton radioButton, radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    Spinner farmMr, buyCo, geSal, whnCh, whnX, yrSe, CBSpinner, FCSpinner, AFSpinner, ALSpinner, STSpinner;
    DataBHelper dataBHelper;
    Button submitBtn, viewBtn;
    AutoCompleteTextView listView, localGvt, agntNam;

    BroadcastReceiver broadcastReceiver;
    private static final String TAG = "ListDataActivity3";
    public static final int SYNC_STATUS_OK = 1;
    public static final int SYNC_STATUS_FAILED = 0;
    //a broadcast to know weather the data is synced or not
    public static final String DATA_SAVED_BROADCAST = "net.simplifiedcoding.datasaved";
    //public static final String DATA_SAVED_BROADCAST = "namarkets.com/nasurvey/nasurvey.php";
    public static final String UI_UPDATE_BROADCAST = "com.nasurvey.icaptech.uiupdatebroadcast";
    public static final String URL_SAVE_NAME = "http://namarkets.com/nasurvey/nasurvey.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBHelper = new DataBHelper(this);
        //registering the broadcast receiver to update sync status
        registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));
        registerReceiver(new NetworkStateChecker3(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        dataBHelper.getData();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
dataBHelper.getData();
            }
        };

        agntNam = findViewById(R.id.agentName);
        fullNam = findViewById(R.id.fullNam);
        listView = findViewById(R.id.stat);
        localGvt = findViewById(R.id.lg);
        twn = findViewById(R.id.twn);
        phNo = findViewById(R.id.phoneNum);
        farmMr = findViewById(R.id.farmMr);
        mmSz = findViewById(R.id.memSz);
        cgNam = findViewById(R.id.cooNam);
        cFarmZ = findViewById(R.id.cfmSz);
        iFarmZ = findViewById(R.id.indvFmSz);
        riceVr = findViewById(R.id.varRice);
        qtyHar = findViewById(R.id.qtyHarv);
        fertYld = findViewById(R.id.yield);
        noRz = findViewById(R.id.noApp);
        qtyApl = findViewById(R.id.qtyFert);
        szLoa = findViewById(R.id.szLoan);
        srcRic = findViewById(R.id.srcRice);
        qtyPdyRic = findViewById(R.id.qtyPdRice);
        majorByrs = findViewById(R.id.majBuy);
        buyCo = findViewById(R.id.buyCom);
        qttyBy = findViewById(R.id.qtyBuy);
        howMuc = findViewById(R.id.sellRice);
        priceB = findViewById(R.id.priceBf);
        incrDsc = findViewById(R.id.incDec);
        reasn = findViewById(R.id.reaz);
        howSply = findViewById(R.id.hwSupp);
        ysSpTr = findViewById(R.id.ysSepTrans);
        frm1 = findViewById(R.id.avgCostF1);
        t1 = findViewById(R.id.avgCostT1);
        cstCS1 = findViewById(R.id.avgCostAmt1);
        frm2 = findViewById(R.id.avgCostF2);
        t2 = findViewById(R.id.avgCostT2);
        cstCS2 = findViewById(R.id.avgCostAmt2);
        frm3 = findViewById(R.id.avgCostF3);
        t3 = findViewById(R.id.avgCostT3);
        cstCS3 = findViewById(R.id.avgCostAmt3);
        inflPric = findViewById(R.id.costPdy);
        geSal = findViewById(R.id.genSales);
        avgQtyFc = findViewById(R.id.avgQty);
        whnCh = findViewById(R.id.pdyChp);
        whyChp = findViewById(R.id.why1);
        whnX = findViewById(R.id.expRice);
        whyEx = findViewById(R.id.why2);
        majorCha = findViewById(R.id.majChal);
        addrCha = findViewById(R.id.addrChal);
        yrSe = findViewById(R.id.yearSell);
        otherSe = findViewById(R.id.othSell);
        adIn = findViewById(R.id.addIn);

        radioGroup = findViewById(R.id.CBradioGroup);
        radioGroup1 = findViewById(R.id.FCradioGroup);
        radioGroup2 = findViewById(R.id.AFradioGroup);
        radioGroup3 = findViewById(R.id.ALradioGroup);
        radioGroup4 = findViewById(R.id.STradioGroup);

       /* CBSpinner = findViewById(R.id.CBSpinner);
        FCSpinner = findViewById(R.id.FCSpinner);
        AFSpinner = findViewById(R.id.AFSpinner);
        ALSpinner = findViewById(R.id.ALSpinner);
        STSpinner = findViewById(R.id.STSpinner); */
        populateListView();
        populateLG();
        populateAgentName();

        findViewById(R.id.submitBtn).setOnClickListener(this);
        viewBtn = findViewById(R.id.viewBtn);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurveyActivity.this, ListDataActivity3.class);
                startActivity(intent);
            }
        });

    }
    // autocomplete code 06 21 2019 start
    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        dataBHelper = new DataBHelper(this);
        List<String> lables = dataBHelper.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(dataAdapter);

    }

    private void populateLG() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        dataBHelper = new DataBHelper(this);
        List<String> lables = dataBHelper.getLG();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localGvt.setAdapter(dataAdapter);

    }

    private void populateAgentName() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        dataBHelper = new DataBHelper(this);
        List<String> lables = dataBHelper.getAgentName();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agntNam.setAdapter(dataAdapter);

    }

    private void dataSubmit() {

        String fName = fullNam.getText().toString();
        String agentNm = agntNam.getText().toString();
        String sta = listView.getText().toString();
        String localG = localGvt.getText().toString();
        String tow = twn.getText().toString();
        String phoneNm = phNo.getText().toString();
        String memSiz = mmSz.getText().toString();
        String cggNam = cgNam.getText().toString();
        String cFarmZz = cFarmZ.getText().toString();
        String inFarm = iFarmZ.getText().toString();
        String riceVarr = riceVr.getText().toString();
        String qttyHarr = qtyHar.getText().toString();
        String fertTldd = fertYld.getText().toString();
        String noRzz = noRz.getText().toString();
        String qtyApll = qtyApl.getText().toString();
        String szLoaa = szLoa.getText().toString();
        String srcRicc = srcRic.getText().toString();
        String qtyPdyRicc = qtyPdyRic.getText().toString();
        String majByrss = majorByrs.getText().toString();
        String comSolcc = buyCo.getSelectedItem().toString();
        String qttyByy = qttyBy.getText().toString();
        String howMcc = howMuc.getText().toString();
        String prcBb = priceB.getText().toString();
        String incDs = incrDsc.getText().toString();
        String reasnn = reasn.getText().toString();
        String hwSpyy = howSply.getText().toString();
        String ysSptrr = ysSpTr.getText().toString();
        String fm1 = frm1.getText().toString();
        String t11 = t1.getText().toString();
        String cstCs11 = cstCS1.getText().toString();
        String fm2 = frm2.getText().toString();
        String t22 = t2.getText().toString();
        String cstCs22 = cstCS2.getText().toString();
        String fm3 = frm3.getText().toString();
        String t33 = t3.getText().toString();
        String cstCs33 = cstCS3.getText().toString();
        String infPrcc = inflPric.getText().toString();
        String salesDmm = geSal.getSelectedItem().toString();
        String avgQtFcc = avgQtyFc.getText().toString();
        String whnCp = whnCh.getSelectedItem().toString();
        String whyCp = whyChp.getText().toString();
        String whnEx = whnX.getSelectedItem().toString();
        String whyExx = whyEx.getText().toString();
        String mjCh = majorCha.getText().toString();
        String adCh = addrCha.getText().toString();
        String timSe = yrSe.getSelectedItem().toString();
        String othSe = otherSe.getText().toString();
        String adInn = adIn.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        int selectedId1 = radioGroup1.getCheckedRadioButtonId();
        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
        int selectedId3 = radioGroup3.getCheckedRadioButtonId();
        int selectedId4 = radioGroup4.getCheckedRadioButtonId();

        radioButton = findViewById(selectedId);
        radioButton1 = findViewById(selectedId1);
        radioButton2 = findViewById(selectedId2);
        radioButton3 = findViewById(selectedId3);
        radioButton4 = findViewById(selectedId4);

        Log.d("debug", "radio result" +" "+ radioButton);

        String cbRg = radioButton.getText().toString();
        String fcRg = radioButton1.getText().toString();
        String afRg = radioButton2.getText().toString();
        String alRg = radioButton3.getText().toString();
        String stRg = radioButton4.getText().toString();

        String fmMr = farmMr.getSelectedItem().toString();
        /*String cbRg = CBSpinner.getSelectedItem().toString();
        String fcRg = FCSpinner.getSelectedItem().toString();
        String afRg = AFSpinner.getSelectedItem().toString();
        String alRg = ALSpinner.getSelectedItem().toString();
        String stRg = STSpinner.getSelectedItem().toString();*/


        if(agentNm.isEmpty()){
            agntNam.setError("enter your full name as an agent");
            agntNam.requestFocus();
            return;
        }
        if(fName.isEmpty()){
            fullNam.setError("some fields are empty");
            fullNam.requestFocus();
            return;
        }

        if(sta.isEmpty()){
            stat.setError("some fields are empty");
            stat.requestFocus();
            return;
        }

        if(localG.isEmpty()){
            lg.setError("some fields are empty");
            lg.requestFocus();
            return;
        }
        if(tow.isEmpty()){
            twn.setError("some fields are empty");
            twn.requestFocus();
            return;
        }
        if(phoneNm.isEmpty()){
            phNo.setError("please enter phone number");
            phNo.requestFocus();
            return;
        }
        if(memSiz.isEmpty()){
            mmSz.setError("some fields are empty");
            mmSz.requestFocus();
            return;
        }
        if(cggNam.isEmpty()){
            cgNam.setError("some fields are empty");
            cgNam.requestFocus();
            return;
        }

        if(cFarmZz.isEmpty()){
            cFarmZ.setError("some fields are empty");
            cFarmZ.requestFocus();
            return;
        }
        if(inFarm.isEmpty()){
            iFarmZ.setError("some fields are empty");
            iFarmZ.requestFocus();
            return;
        }

        if(riceVarr.isEmpty()){
            riceVr.setError("some fields are empty");
            riceVr.requestFocus();
            return;
        }
        if(qttyHarr.isEmpty()){
            qtyHar.setError("some fields are empty");
            qtyHar.requestFocus();
            return;
        }
        if(fertTldd.isEmpty()){
            fertYld.setError("some fields are empty");
            fertYld.requestFocus();
            return;
        }

        if(noRzz.isEmpty()){
            noRz.setError("some fields are empty");
            noRz.requestFocus();
            return;
        }
        if(qtyApll.isEmpty()){
            qtyApl.setError("some fields are empty");
            qtyApl.requestFocus();
            return;
        }

        if(szLoaa.isEmpty()){
            szLoa.setError("some fields are empty");
            szLoa.requestFocus();
            return;
        }
        if(srcRicc.isEmpty()){
            srcRic.setError("some fields are empty");
            srcRic.requestFocus();
            return;
        }
        if(qtyPdyRicc.isEmpty()){
            qtyPdyRic.setError("some fields are empty");
            qtyPdyRic.requestFocus();
            return;
        }

        if(majByrss.isEmpty()){
            majorByrs.setError("some fields are empty");
            majorByrs.requestFocus();
            return;
        }
        if(comSolcc.isEmpty()){
            comeSolct.setError("some fields are empty");
            comeSolct.requestFocus();
            return;
        }

        if(qttyByy.isEmpty()){
            qttyBy.setError("some fields are empty");
            qttyBy.requestFocus();
            return;
        }
        if(howMcc.isEmpty()){
            howMuc.setError("some fields are empty");
            howMuc.requestFocus();
            return;
        }
        if(prcBb.isEmpty()){
            priceB.setError("some fields are empty");
            priceB.requestFocus();
            return;
        }

        if(incDs.isEmpty()){
            incrDsc.setError("some fields are empty");
            incrDsc.requestFocus();
            return;
        }
        if(reasnn.isEmpty()){
            reasn.setError("some fields are empty");
            reasn.requestFocus();
            return;
        }

        if(hwSpyy.isEmpty()){
            howSply.setError("some fields are empty");
            howSply.requestFocus();
            return;
        }

        if(ysSptrr.isEmpty()){
            ysSpTr.setError("some fields are empty");
            ysSpTr.requestFocus();
            return;
        }
        if(fm1.isEmpty()){
            frm1.setError("some fields are empty");
            frm1.requestFocus();
            return;
        }

        if(t11.isEmpty()){
            t1.setError("some fields are empty");
            t1.requestFocus();
            return;
        }
        if(cstCs11.isEmpty()){
            cstCS1.setError("some fields are empty");
            cstCS1.requestFocus();
            return;
        }

        if(fm2.isEmpty()){
            frm2.setError("some fields are empty");
            frm2.requestFocus();
            return;
        }
        if(t22.isEmpty()){
            t2.setError("some fields are empty");
            t2.requestFocus();
            return;
        }
        if(cstCs22.isEmpty()){
            cstCS2.setError("some fields are empty");
            cstCS2.requestFocus();
            return;
        }

        if(fm3.isEmpty()){
            frm3.setError("some fields are empty");
            frm3.requestFocus();
            return;
        }
        if(t33.isEmpty()){
            t3.setError("some fields are empty");
            t3.requestFocus();
            return;
        }

        if(cstCs33.isEmpty()){
            cstCS3.setError("some fields are empty");
            cstCS3.requestFocus();
            return;
        }
        if(infPrcc.isEmpty()){
            inflPric.setError("some fields are empty");
            inflPric.requestFocus();
            return;
        }
        if(salesDmm.isEmpty()){
            salesDm.setError("some fields are empty");
            salesDm.requestFocus();
            return;
        }

        if(avgQtFcc.isEmpty()){
            avgQtyFc.setError("some fields are empty");
            avgQtyFc.requestFocus();
            return;
        }
        if(whnCp.isEmpty()){
            whenChp.setError("some fields are empty");
            whenChp.requestFocus();
            return;
        }
        if(whyCp.isEmpty()){
            whyChp.setError("some fields are empty");
            whyChp.requestFocus();
            return;
        }
        if(whnEx.isEmpty()){
            whenEx.setError("some fields are empty");
            whenEx.requestFocus();
            return;
        }
        if(whyExx.isEmpty()){
            whyEx.setError("some fields are empty");
            whyEx.requestFocus();
            return;
        }
        if(mjCh.isEmpty()){
            majorCha.setError("some fields are empty");
            majorCha.requestFocus();
            return;
        }
        if(adCh.isEmpty()){
            addrCha.setError("some fields are empty");
            addrCha.requestFocus();
            return;
        }
        if(timSe.isEmpty()){
            timeSe.setError("some fields are empty");
            timeSe.requestFocus();
            return;
        }
        if(othSe.isEmpty()){
            otherSe.setError("some fields are empty");
            otherSe.requestFocus();
            return;
        }
        if(adInn.isEmpty()){
            adIn.setError("some fields are empty");
            adIn.requestFocus();
            return;
        }
        if(cbRg.isEmpty()){
            radioButton.setError("some fields are empty");
            radioButton.requestFocus();
            return;
        }
        if(fcRg.isEmpty()){
            radioButton1.setError("some fields are empty");
            radioButton1.requestFocus();
            return;
        }

        if(afRg.isEmpty()){
            radioButton2.setError("some fields are empty");
            radioButton2.requestFocus();
            return;
        }
        if(alRg.isEmpty()){
            radioButton3.setError("some fields are empty");
            radioButton3.requestFocus();
            return;
        }
        if(stRg.isEmpty()){
            radioButton4.setError("some fields are empty");
            radioButton4.requestFocus();
            return;
        }
        if(fmMr.isEmpty()){
            Toast.makeText(SurveyActivity.this, "some fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }
     /* boolean insertData = dataBHelper.addData(fName, sta, localG, tow, phoneNm, fmMr, cbRg, memSiz, cggNam, cFarmZz, fcRg, inFarm, riceVarr, qttyHarr, afRg, fertTldd, noRzz, qtyApll, alRg, szLoaa, srcRicc, qtyPdyRicc, majByrss, comSolcc, qttyByy, howMcc, prcBb, incDs, reasnn, hwSpyy, stRg, ysSptrr, fm1, t11, cstCs11,
                fm2, t22, cstCs22, fm3, t33, cstCs33, infPrcc, salesDmm, avgQtFcc, whnCp, whyCp, whnEx, whyExx, mjCh, adCh, timSe, othSe, adInn, SYNC_STATUS_OK);

        if(insertData == true){
            Toast.makeText(SurveyActivity.this, "saved...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SurveyActivity.this, "oops...", Toast.LENGTH_LONG).show();
        }*/

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
                        //if there is a success
                        //storing the name to sqlite with status synced
                        dataBHelper.addData(fName, agentNm, sta, localG, tow, phoneNm, fmMr, cbRg, memSiz, cggNam, cFarmZz, fcRg, inFarm, riceVarr, qttyHarr, afRg, fertTldd, noRzz, qtyApll, alRg, szLoaa, srcRicc, qtyPdyRicc, majByrss, comSolcc, qttyByy, howMcc, prcBb, incDs, reasnn, hwSpyy, stRg, ysSptrr, fm1, t11, cstCs11,
                                fm2, t22, cstCs22, fm3, t33, cstCs33, infPrcc, salesDmm, avgQtFcc, whnCp, whyCp, whnEx, whyExx, mjCh, adCh, timSe, othSe, adInn, SYNC_STATUS_OK);
                    } else {
                        //if there is some error
                        //saving the name to sqlite with status unsynced
                        dataBHelper.addData(fName, agentNm, sta, localG, tow, phoneNm, fmMr, cbRg, memSiz, cggNam, cFarmZz, fcRg, inFarm, riceVarr, qttyHarr, afRg, fertTldd, noRzz, qtyApll, alRg, szLoaa, srcRicc, qtyPdyRicc, majByrss, comSolcc, qttyByy, howMcc, prcBb, incDs, reasnn, hwSpyy, stRg, ysSptrr, fm1, t11, cstCs11,
                                fm2, t22, cstCs22, fm3, t33, cstCs33, infPrcc, salesDmm, avgQtFcc, whnCp, whyCp, whnEx, whyExx, mjCh, adCh, timSe, othSe, adInn, SYNC_STATUS_FAILED);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                try {
                   // String s = response.body().toString();
                    Toast.makeText(SurveyActivity.this, "Submitted...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SurveyActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dataBHelper.addData(fName, agentNm, sta, localG, tow, phoneNm, fmMr, cbRg, memSiz, cggNam, cFarmZz, fcRg, inFarm, riceVarr, qttyHarr, afRg, fertTldd, noRzz, qtyApll, alRg, szLoaa, srcRicc, qtyPdyRicc, majByrss, comSolcc, qttyByy, howMcc, prcBb, incDs, reasnn, hwSpyy, stRg, ysSptrr, fm1, t11, cstCs11,
                        fm2, t22, cstCs22, fm3, t33, cstCs33, infPrcc, salesDmm, avgQtFcc, whnCp, whyCp, whnEx, whyExx, mjCh, adCh, timSe, othSe, adInn, SYNC_STATUS_FAILED);
                //Toast.makeText(SurveyActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(SurveyActivity.this,"data has been saved on phone and will submitted once there is internet connection", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SurveyActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.submitBtn:
                dataSubmit();

                break;
        }

    }

    public void onStart(){
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(UI_UPDATE_BROADCAST));
    }

    /**@Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    } **/


    /**@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                startActivity(new Intent(this, WelcomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    } **/

}
