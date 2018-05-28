package tn.esprit.legacy.monivulation.Data.DataSuppliers;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import tn.esprit.legacy.monivulation.Helpers.ServerConnection;
import tn.esprit.legacy.monivulation.Models.Cycle;
import tn.esprit.legacy.monivulation.app.AppController;

/**
 * Created by Mohamed on 11/27/2017.
 */

public class CycleDS extends ServerConnection {

    final String TAG = "Cycle";
    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";

    //private final String URL_SERVER = "http://openstarter.000webhostapp.com/AndroidWS/web/app_dev.php";

    private final String URL_GET_CYCLE_INFO = URL_SERVER + "/cycle/info/";
    private final String URL_CYCLE_FIRST = URL_SERVER + "/cycle/first/";
    private final String URL_CHECK_FIRST_CYCLE = URL_SERVER + "/cycle/checkFirst/";
    /*private final String URL_LOGIN = URL_SERVER + "/login";
    private final String URL_GET_BY_EMAIL_WITH_COUNT = URL_SERVER + "/user/getProjectsCount";
    private final String URL_CREATE = URL_SERVER + "/users";
    private final String URL_UPDATE = URL_SERVER + "/user/update";
    private final String URL_UPDATE_TOKEN = URL_SERVER + "/user/updateToken";*/

    private Gson mGson;

    public CycleDS(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd-MM-yyyy HH:mm:ss");
        mGson = gsonBuilder.create();
    }

    public void getCycleInfo(String userId, final CallbackGet callback){
        // Tag used to cancel the request

        Map<String, String> params = new HashMap<>();
        //params.put("email", email);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL_GET_CYCLE_INFO+userId, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Cycle createdCycle = mGson.fromJson(response.toString(), Cycle.class);
                        Log.d(TAG,createdCycle.toString());
                        callback.onSuccess(createdCycle);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"error while reaching server");
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError(error);

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    public void checkForFirstCycle(String userId, final CallbackCheck callback){


        StringRequest stringReq = new StringRequest(Request.Method.GET,
                URL_CHECK_FIRST_CYCLE+userId,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        callback.onSuccess(response);
                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError(error);
            }
        }) {


        };

        AppController.getInstance().addToRequestQueue(stringReq, tag_json_obj);
    }

    public void firstCycle(final String userId, final String length, final String periodLength, String startDate, final CallbackAdd callback) {

        Map<String, String> params = new HashMap<>();
        params.put("startDate", startDate+" 00:00:00");
        params.put("length", length);
        params.put("periodLength", periodLength);



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL_CYCLE_FIRST+userId, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String result = response.toString();
                        callback.onSuccess();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError();
            }
        }) {


        };






        /*StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                URL_CYCLE_FIRST+userId,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //String result = mGson.fromJson(response.toString(), String.class);
                        callback.onSuccess();
                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError();
            }
        }) {


        };*/



// Adding request to request queue
        Log.d("TAG", Arrays.toString(jsonObjReq.getBody()));
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    /*public void login(String email,String password, final CallbackGet callback){
        // Tag used to cancel the request

        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL_LOGIN, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        User createdUser = mGson.fromJson(response.toString(), User.class);
                        Log.d(TAG,createdUser.toString());
                        callback.onSuccess(createdUser);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"error while reaching server");
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError(error);

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    public void addUser(final String email, final String firstname, final String lastname, String birthdate, String password, final CallbackAdd callback) {

        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("username", "testusername");
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("password", password);
        params.put("birthdate", birthdate+" 00:00:00");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL_CREATE, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError();
            }
        }) {


        };

// Adding request to request queue
        Log.d("TAG", Arrays.toString(jsonObjReq.getBody()));
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    public void updateUser(final String email, final String firstname, final String lastname, String birthdate, String bio, final CallbackUpdate callback) {

        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("birthdate", birthdate+" 00:00:00");
        params.put("bio", bio);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL_UPDATE, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError();
            }
        }) {


        };

// Adding request to request queue
        Log.d("TAG", Arrays.toString(jsonObjReq.getBody()));
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    public void updateToken(final String email, final String token, final CallbackUpdate callback) {

        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("token", token);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL_UPDATE_TOKEN, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError();
            }
        }) {


        };

// Adding request to request queue
        Log.d("TAG", Arrays.toString(jsonObjReq.getBody()));
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }*/


    public interface CallbackAdd{
        void onSuccess();
        void onError();
    }

    public interface CallbackGet{
        void onSuccess(Cycle result);
        void onError(VolleyError error);
    }

    public interface CallbackCheck{
        void onSuccess(String result);
        void onError(VolleyError error);
    }

}
