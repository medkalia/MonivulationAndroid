package tn.esprit.legacy.monivulation.Data.DataSuppliers;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tn.esprit.legacy.monivulation.Data.DataSuppliers.CustomClasses.CustomTemperatureData;
import tn.esprit.legacy.monivulation.Helpers.CustomJsonRequest;
import tn.esprit.legacy.monivulation.Helpers.ServerConnection;
import tn.esprit.legacy.monivulation.app.AppController;

/**
 * Created by Mohamed on 11/27/2017.
 */

public class TemperatureDS extends ServerConnection {

    final String TAG = "Temperature";
    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";

    //private final String URL_SERVER = "http://openstarter.000webhostapp.com/AndroidWS/web/app_dev.php";

    private final String URL_GET_TEMPERATURE_BTW = URL_SERVER + "/temperatureData/getBetween/";


    private Gson mGson;

    public TemperatureDS(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd-MM-yyyy HH:mm:ss");
        mGson = gsonBuilder.create();
    }

    public void getTemperatureBtwDates(String userId, String startDate,String endDate, final CallbackGet callback) throws JSONException {
        // Tag used to cancel the request

        Map<String, String> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);


        /*JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL_GET_TEMPERATURE_BTW+userId, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //CustomTemperatureData createdTemp = mGson.fromJson(response.toString(), CustomTemperatureData.class);
                        List<CustomTemperatureData> temperatureDataList = Arrays.asList(mGson.fromJson(response.toString(), CustomTemperatureData[].class));
                        Log.d(TAG,temperatureDataList.toString());
                        callback.onSuccess(temperatureDataList);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"error while reaching server");
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError(error);

            }
        });*/


        CustomJsonRequest jsonReq = new CustomJsonRequest(Request.Method.POST,
                URL_GET_TEMPERATURE_BTW+userId, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //CustomTemperatureData createdTemp = mGson.fromJson(response.toString(), CustomTemperatureData.class);
                        List<CustomTemperatureData> temperatureDataList = Arrays.asList(mGson.fromJson(response.toString(), CustomTemperatureData[].class));
                        Log.d(TAG,temperatureDataList.toString());
                        callback.onSuccess(temperatureDataList);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"error while reaching server");
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError(error);

            }
        });

        AppController.getInstance().addToRequestQueue(jsonReq, tag_json_obj);
    }



    public interface CallbackAdd{
        void onSuccess();
        void onError();
    }

    public interface CallbackGet{
        void onSuccess(List<CustomTemperatureData> result);
        void onError(VolleyError error);
    }

    public interface CallbackCheck{
        void onSuccess(String result);
        void onError(VolleyError error);
    }

}
