package com.example.prosia.listviewvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.example.prosia.listviewvolley.service.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailResep extends AppCompatActivity {

    private static final String TAG = DetailResep.class.getSimpleName();
    private String judulResep;
    private ImageView detailicon;
    private TextView detailjudul, detailketerangan;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resep);

        if(getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            judulResep = bundle.getString("judulResep");

        }

        Log.d(TAG, "Bundle: " +judulResep);
        detailicon = (ImageView)findViewById(R.id.detailicon);
        detailjudul = (TextView)findViewById(R.id.detailjudul);
        detailketerangan = (TextView)findViewById(R.id.detailketerangan);

        requestQueue = Volley.newRequestQueue(DetailResep.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.POST, Server.DETAILRESEP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG, "jsonObject :  " +jsonObject);
                    JSONArray jsonArray = jsonObject.getJSONArray("resep");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("id", json.getString("id"));
                        map.put("nama", json.getString("nama"));
                        map.put("gambar", json.getString("gambar"));
                        map.put("keterangan", json.getString("keterangan"));
                        list_data.add(map);
                    }
                    Glide.with(getApplicationContext())
                            .load("http://fahevizha72.000webhostapp.com/img/"+list_data.get(0).get("gambar"))
                            .crossFade()
                            .placeholder(R.mipmap.ic_launcher)
                            .into(detailicon);
                    detailjudul.setText("Judul Resep : "+ list_data.get(0).get("nama"));
                    detailketerangan.setText("Cara Masak dan Bahan : " +list_data.get(0).get("keterangan"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailResep.this, "Gagal Connect", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                Adding parameters to request
                params.put(Server.KEY_DETAIL_RESEP, judulResep);

//                returning parameter
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
