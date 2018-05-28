package tn.esprit.legacy.monivulation.Data.DataSuppliers;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import tn.esprit.legacy.monivulation.Helpers.ServerConnection;
import tn.esprit.legacy.monivulation.Models.User;
import tn.esprit.legacy.monivulation.app.AppController;

/**
 * Created by Mohamed on 11/27/2017.
 */

public class UserDS extends ServerConnection {

    final String TAG = "User";
    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";

    //private final String URL_SERVER = "http://openstarter.000webhostapp.com/AndroidWS/web/app_dev.php";

    private final String URL_GET_BY_ID = URL_SERVER + "/users/";
    private final String URL_LOGIN = URL_SERVER + "/login";
    //private final String URL_GET_BY_EMAIL_WITH_COUNT = URL_SERVER + "/user/getProjectsCount";
    private final String URL_CREATE = URL_SERVER + "/users";
    private final String URL_UPDATE = URL_SERVER + "/user/update";
    private final String URL_UPDATE_TOKEN = URL_SERVER + "/user/updateToken";

    private Gson mGson;

    public UserDS(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd-MM-yyyy HH:mm:ss");
        mGson = gsonBuilder.create();
    }

    public void getUserById(int id, final CallbackGet callback){
        // Tag used to cancel the request

        Map<String, String> params = new HashMap<>();




        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                URL_GET_BY_ID+id, new JSONObject(params),
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

    public void login(String email,String password, final CallbackGet callback){
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
                        User createdUser = mGson.fromJson(response.toString(), User.class);
                        callback.onSuccess(createdUser);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError(error);
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
    }


    public interface CallbackAdd{
        void onSuccess(User result);
        void onError(VolleyError error);
    }

    public interface CallbackGet{
        void onSuccess(User result);
        void onError(VolleyError error);
    }

    public interface CallbackUpdate{
        void onSuccess();
        void onError();
    }

}
