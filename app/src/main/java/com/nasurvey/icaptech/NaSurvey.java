package com.nasurvey.icaptech;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NaSurvey {

    @FormUrlEncoded
    @POST("nasurvey/nasurvey.php")
    Call<ResponseBody> submitResponse(
            @Field("agentName") String agentName,
            @Field("fullName") String fullName,
            @Field("state") String state,
            @Field("lga") String lga,
            @Field("town") String town,
            @Field("phone_number") String phone_number,
            @Field("farmMerc") String farmMerc,
            @Field("coopGroup") String coopGroup,
            @Field("memSize") String memSize,
            @Field("CGName") String CGName,
            @Field("coopFarmSz") String coopFarmSz,
            @Field("farmColl") String farmColl,
            @Field("indvFarmSize") String indvFarmSize,
            @Field("riceVar") String riceVar,
            @Field("qtyHarv") String qtyHarv,
            @Field("applyFert") String applyFert,
            @Field("yesYield") String yesYield,
            @Field("noReason") String noReason,
            @Field("qtyApply") String qtyApply,
            @Field("applyLoan") String applyLoan,
            @Field("szLoan") String szLoan,
            @Field("srcRice") String srcRice,
            @Field("qtyPdyRice") String qtyPdyRice,
            @Field("majorBuyers") String majorBuyers,
            @Field("comeSolicit") String comeSolicit,
            @Field("qttyBuy") String qttyBuy,
            @Field("howMuch") String howMuch,
            @Field("priceBf") String priceBf,
            @Field("incrDesc") String incrDesc,
            @Field("reason") String reason,
            @Field("howSupply") String howSupply,
            @Field("chrgSep") String chrgSep,
            @Field("yesSepTrans") String yesSepTrans,
            @Field("from1") String from1,
            @Field("to1") String to1,
            @Field("costCS1") String costCS1,
            @Field("from2") String from2,
            @Field("to2") String to2,
            @Field("costCS2") String costCS2,
            @Field("from3") String from3,
            @Field("to3") String to3,
            @Field("costCS3") String costCS3,
            @Field("inflPrice") String inflPrice,
            @Field("salesDem") String salesDem,
            @Field("avgQtyFac") String avgQtyFac,
            @Field("whenCheapest") String whenCheapest,
            @Field("whyCheap") String whyCheap,
            @Field("whenExp") String whenExp,
            @Field("whyExp") String whyExp,
            @Field("majorChall") String majorChall,
            @Field("addrChall") String addrChall,
            @Field("timeSell") String timeSell,
            @Field("otherSell") String otherSell,
            @Field("addInfo") String addInfo);


}
