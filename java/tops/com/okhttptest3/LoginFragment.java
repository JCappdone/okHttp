package tops.com.okhttptest3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, AsyncTaskCallBack {

    EditText edtL;
    Button btnL, btnNR;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        edtL = view.findViewById(R.id.edtL);
        btnL = view.findViewById(R.id.btnL);
        btnNR = view.findViewById(R.id.btnNR);

        btnL.setOnClickListener(this);
        btnNR.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnL:

                String uname = edtL.getText().toString();
                String url = "userverificaion.php?"+"userlogin="+uname;

                MyAsyncTask asyncTask =  new MyAsyncTask(this,300);
                asyncTask.execute(url);



                break;

            case R.id.btnNR:

                break;


        }
    }



    @Override
    public void asyncTask(String result,int flag) {
        Toast.makeText(getContext(),result, Toast.LENGTH_SHORT).show();

        try {
            JSONObject jsonObject = new JSONObject(result);
            jsonObject.getJSONArray("result");
//            JSONObject userJsonObject = jsonArray.getJSONObject(0);

//            UtilityHelper helper =  new UtilityHelper();
//            helper.writeUser(getContext(),userJsonObject.toString());

            CategoryFragment fragment = new CategoryFragment();
            getFragmentManager().beginTransaction().replace(R.id.container,fragment).addToBackStack(LoginFragment.class.getName()).commit();


        } catch (JSONException e) {
            e.printStackTrace();
        }





    }


}
