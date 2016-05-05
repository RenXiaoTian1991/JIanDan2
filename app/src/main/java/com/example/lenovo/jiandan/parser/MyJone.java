package com.example.lenovo.jiandan.parser;

import android.util.Log;

import com.example.lenovo.jiandan.Bean.FreshNews;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/1/26.
 */
public abstract class MyJone extends Callback<ArrayList<FreshNews>>
{
    @Override
    public ArrayList<FreshNews> parseNetworkResponse(Response response) throws IOException
    {
        ArrayList<FreshNews> user = null;
        try {
            String body = response.body().string();
            JSONObject resultObj = new JSONObject(body);
            //Log.i("abc",body);
            JSONArray postsArray = resultObj.optJSONArray("posts");
            user = new Gson().fromJson(new String(postsArray.toString()), new TypeToken<ArrayList<FreshNews>>() {
            }.getType());
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }


}
