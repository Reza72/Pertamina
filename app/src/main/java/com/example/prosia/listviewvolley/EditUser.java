package com.example.prosia.listviewvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prosia.listviewvolley.service.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditUser extends AppCompatActivity {

    private static final String TAG = EditUser.class.getSimpleName();

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Button update,cari;
    private EditText id,username,password;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        requestQueue = Volley.newRequestQueue(EditUser.this);

        list_data = new ArrayList<HashMap<String, String>>();

        update = (Button)findViewById(R.id.btnUpdate);
        cari = (Button)findViewById(R.id.updateCari);

        id = (EditText)findViewById(R.id.cariId);
        username = (EditText)findViewById(R.id.updateUsername);
        password = (EditText)findViewById(R.id.updatePassword);

        cari.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                cariAdmin();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                updateUser();

            }
        });

    }

    public void cariAdmin(){
        stringRequest = new StringRequest(Request.Method.POST, Server.FIND_USER_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("user");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("username", json.getString("username"));
                        map.put("password", json.getString("password"));
                        list_data.add(map);
                    }
                    username.setText(list_data.get(0).get("username"));
                    password.setText(list_data.get(0).get("password"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditUser.this, "Gagal Connect", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                Adding parameters to request
                params.put(Server.FIND_ID, id.getText().toString());
//                returning parameter
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void updateUser(){
        stringRequest = new StringRequest(Request.Method.POST, Server.UPDATE_ADMIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "response: " +response);
                    JSONObject json = new JSONObject(response);
                    Log.d(TAG, "response signup: " +json);

                    // Mengambil variable status pada response

                    //JSONArray jsonArray = json.getJSONArray("status");
                    String status = json.getString("status");
                    Log.e(TAG, "JSON Array " + status);
                    if (status.equals("success")) {
                        Toast.makeText(getApplicationContext(), "Succesfully saving data", Toast.LENGTH_LONG).show();
                    } else {
                        // Jika Login Gagal Maka mengeluarkan Toast dengan message.
                        Toast.makeText(getApplicationContext(), "Failed saving data", Toast.LENGTH_LONG).show();
                        //  pDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditUser.this, "Gagal Connect", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                Adding parameters to request
                params.put(Server.KEY_ID, id.getText().toString());
                params.put(Server.KEY_USERNAME, username.getText().toString());
                params.put(Server.KEY_PASSWORD_SIGN_UP, password.getText().toString());

//                returning parameter
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
