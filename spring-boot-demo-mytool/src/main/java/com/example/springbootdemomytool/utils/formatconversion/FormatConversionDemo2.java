package com.example.springbootdemomytool.utils.formatconversion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @ClassName FormatConversionDemo2
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/20 18:52
 * @Version 1.0
 */
public class FormatConversionDemo2 {

    public static void main(String[] args) {
        String str = "[{\"termNo\":3,\"repayDate\":\"20291006\",\"principal\":1326.53,\"interest\":53.86,\"penaltyAmount\":0.0,\"paidPrincipal\":0.0," +
                "\"paidInterest\":0.0,\"paidPenaltyAmount\":0.0,\"intefine\":0.0,\"paidIntefine\":0.0,\"isOverdue\":\"0\"},{\"termNo\":2," +
                "\"repayDate\":\"20290906\",\"principal\":1313.39,\"interest\":67.0,\"penaltyAmount\":0.0,\"paidPrincipal\":0.0,\"paidInterest\":0.0," +
                "\"paidPenaltyAmount\":0.0,\"intefine\":0.0,\"paidIntefine\":0.0,\"isOverdue\":\"0\"},{\"termNo\":1,\"repayDate\":\"20290806\"," +
                "\"principal\":1300.39,\"interest\":48.0,\"penaltyAmount\":0.0,\"paidPrincipal\":0.0,\"paidInterest\":0.0,\"paidPenaltyAmount\":0.0," +
                "\"intefine\":0.0,\"paidIntefine\":0.0,\"isOverdue\":\"0\"},{\"termNo\":6,\"repayDate\":\"20300106\",\"principal\":1366.71," +
                "\"interest\":13.67,\"penaltyAmount\":0.0,\"paidPrincipal\":0.0,\"paidInterest\":0.0,\"paidPenaltyAmount\":0.0,\"intefine\":0.0," +
                "\"paidIntefine\":0.0,\"isOverdue\":\"0\"},{\"termNo\":5,\"repayDate\":\"20291206\",\"principal\":1353.19,\"interest\":27.2," +
                "\"penaltyAmount\":0.0,\"paidPrincipal\":0.0,\"paidInterest\":0.0,\"paidPenaltyAmount\":0.0,\"intefine\":0.0,\"paidIntefine\":0.0," +
                "\"isOverdue\":\"0\"},{\"termNo\":4,\"repayDate\":\"20291106\",\"principal\":1339.79,\"interest\":40.6,\"penaltyAmount\":0.0," +
                "\"paidPrincipal\":0.0,\"paidInterest\":0.0,\"paidPenaltyAmount\":0.0,\"intefine\":0.0,\"paidIntefine\":0.0,\"isOverdue\":\"0\"}]";
        List<Object> repayPlan = new Gson().fromJson(
                str,
                new TypeToken<List<Object>>() {
                }.getType());

        System.out.println(new Gson().toJson(repayPlan));


    }
}
