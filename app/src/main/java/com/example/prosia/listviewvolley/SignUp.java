package com.example.prosia.listviewvolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.prosia.listviewvolley.service.MenuUtama;
import com.example.prosia.listviewvolley.service.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private static final String TAG = SignUp.class.getSimpleName();

    private Button simpan;
    private EditText id,username,password,email,phone;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        requestQueue = Volley.newRequestQueue(SignUp.this);

        simpan = (Button)findViewById(R.id.createSimpan);
        id = (EditText)findViewById(R.id.createId); // createId ini berdasarkan view
        username = (EditText)findViewById(R.id.createUsername);
        password = (EditText)findViewById(R.id.createPassword);
        email = (EditText)findViewById(R.id.createEmail);
        phone = (EditText)findViewById(R.id.createPhone);

        simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
               saveData();

            }
        });


    }

    public void saveData(){
        stringRequest = new StringRequest(Request.Method.POST, Server.SIGN_UP, new Response.Listener<String>() {
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
                Toast.makeText(SignUp.this, "Gagal Connect", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                Adding parameters to request
                params.put(Server.KEY_ID, id.getText().toString());
                params.put(Server.KEY_USERNAME, username.getText().toString());
                params.put(Server.KEY_PASSWORD_SIGN_UP, password.getText().toString());
                params.put(Server.KEY_EMAIL_SIGN_UP, email.getText().toString());
                params.put(Server.KEY_PHONE, phone.getText().toString());

//                returning parameter
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
