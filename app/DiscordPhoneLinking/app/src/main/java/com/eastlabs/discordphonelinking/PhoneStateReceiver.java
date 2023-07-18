package com.eastlabs.discordphonelinking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
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

public class PhoneStateReceiver extends BroadcastReceiver {
    public static final String SMS_BUNDLE = "pdus";

    public void onReceive(Context context, Intent intent) {
        //Log.d("DebugData", "Phone State changed!");
        //Log.d("DebugData", intent.getAction());
        //Log.d("DebugData", intent.getExtras().getString(TelephonyManager.EXTRA_STATE));
        //Log.d("DebugData", TelephonyManager.EXTRA_STATE_RINGING);
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            if (TelephonyManager.EXTRA_STATE_RINGING.equals(intent.getExtras().getString(TelephonyManager.EXTRA_STATE))) {
                Log.d("DebugData", "The Phone is ringing");

                final String number1 = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                final String number = number1.replace("+1", "");

                //Toast.makeText(context, number, Toast.LENGTH_SHORT).show();
                Log.d("DebugData", number);

                RequestQueue queue = Volley.newRequestQueue(context);
                StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.112:10000", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DebugData", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("DebugData", error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Number", number);
                        params.put("IsCall", "true");

                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/x-www-form-urlencoded");
                        return params;
                    }
                };
                queue.add(sr);
            }
        } else {
            // Do Nothing
        }
    }
}
