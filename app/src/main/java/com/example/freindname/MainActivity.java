package com.example.freindname;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1;
    String getname,getfname,getnickname,getdes;
    String apiUrl="https://dummyapifriends.herokuapp.com/adddata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.name);
        ed2=(EditText) findViewById(R.id.fname);
        ed3=(EditText) findViewById(R.id.nickname);
        ed4=(EditText) findViewById(R.id.des);
        b1=(AppCompatButton) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getname=ed1.getText().toString();
                getfname=ed2.getText().toString();
                getnickname=ed3.getText().toString();
                getdes=ed4.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST,
                        apiUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        })
                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params=new HashMap<>();
                        params.put("name",getname);
                        params.put("friendName",getfname);
                        params.put("friendNickName",getnickname);
                        params.put("DescribeYourFriend",getdes);
                        return params;

                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);


            }
        });
    }
}