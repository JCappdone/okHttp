package tops.com.okhttptest3;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shriji on 18/1/18.
 */

public class MyAsyncTask extends AsyncTask<String,Void,String> {

    AsyncTaskCallBack asyncTaskCallBack;
    private int flag;

    HashMap<String,String> hashMap;

    public MyAsyncTask(AsyncTaskCallBack asyncTaskCallBack, int flag) {
        this.asyncTaskCallBack = asyncTaskCallBack;
        this.flag = flag;
    }


    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        asyncTaskCallBack.asyncTask(s,flag);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url(UtilityHelper.BASE_URL+strings[0]);
        if(hashMap!=null) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            Set<String> keySet = hashMap.keySet();
            for (String key : keySet) {
                bodyBuilder.add(key, hashMap.get(key));
            }

            FormBody formBody = bodyBuilder.build();
            builder.post(formBody);
        }
        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
