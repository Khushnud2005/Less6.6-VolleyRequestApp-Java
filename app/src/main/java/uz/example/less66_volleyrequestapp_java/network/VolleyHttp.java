package uz.example.less66_volleyrequestapp_java.network;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uz.example.less66_volleyrequestapp_java.App;
import uz.example.less66_volleyrequestapp_java.model.Employee;
import uz.example.less66_volleyrequestapp_java.utils.Logger;

public class VolleyHttp {
    public static String TAG = VolleyHttp.class.getSimpleName();
    public static boolean IS_TESTER = true;
    private static String SERVER_DEVELOPMENT = "https://dummy.restapiexample.com/api/v1/";
    private static String SERVER_PRODUCTION = "https://dummy.restapiexample.com/api/v1/";

    static String server(String api) {
        if (IS_TESTER)
            return SERVER_DEVELOPMENT + api;
        return SERVER_PRODUCTION + api;
    }

    static HashMap<String, String> headers() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-type", "application/json; charset=UTF-8");
        return headers;
    }

    public static void get(String api, HashMap<String, String> params, VolleyHandler handler) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, server(api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(TAG, response);
                        handler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                handler.onError(error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams(){
                return params;
            }
        };
        App.instance.addToRequestQueue(stringRequest);
    }

    public static void post(String api, HashMap<String, String> body, VolleyHandler handler) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server(api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(TAG, response);
                        handler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                handler.onError(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders(){
                return headers();
            }

            @Override
            public byte[] getBody(){
                return new JSONObject(body).toString().getBytes();
            }
        };
        App.instance.addToRequestQueue(stringRequest);
    }

    public static void put(String api, HashMap<String, String> body, VolleyHandler handler) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, server(api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(TAG, response);
                        handler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                handler.onError(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return headers();
            }

            @Override
            public byte[] getBody(){
                return new JSONObject(body).toString().getBytes();
            }
        };
        App.instance.addToRequestQueue(stringRequest);
    }

    public static void del(String api, VolleyHandler handler) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, server(api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(TAG, response);
                        handler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                handler.onError(error.toString());
            }
        });
        App.instance.addToRequestQueue(stringRequest);
    }

    /**
     * Request Api`s
     */
    public static String API_LIST_EMPLOYEES = "employees";
    public static String API_SINGLE_EMPLOYEE = "employee/"; //id
    public static String API_CREATE_EMPLOYEE = "create";
    public static String API_UPDATE_EMPLOYEE = "update/"; //id
    public static String API_DELETE_EMPLOYEE = "delete/"; //id

    /**
     * Request Param`s
     */

    public static HashMap<String, String> paramsEmpty() {
        HashMap<String, String> params = new HashMap<String, String>();
        return params;
    }

    public static HashMap<String, String> paramsCreate(Employee employee) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("employee_name", employee.get_name());
        params.put("employee_salary", String.valueOf(employee.get_salary()));
        params.put("employee_age", String.valueOf(employee.get_age()));
        return params;
    }

    public static HashMap<String, String> paramsUpdate(Employee employee) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(employee.getId()));
        params.put("employee_name", employee.get_name());
        params.put("employee_salary", String.valueOf(employee.get_salary()));
        params.put("employee_age", String.valueOf(employee.get_age()));
        return params;
    }
}
