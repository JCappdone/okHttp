package tops.com.okhttptest3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, AsyncTaskCallBack {

    EditText edtR;
    Button btnR;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        edtR = view.findViewById(R.id.edtR);
        btnR = view.findViewById(R.id.btnR);

        btnR.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        String uname = edtR.getText().toString();

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("user",uname);
        String url = "register.php";

        MyAsyncTask callBack = new MyAsyncTask(this,300);
        callBack.setHashMap(hashMap);
        callBack.execute(url);





    }

    @Override
    public void asyncTask(String result,int flag) {
        Toast.makeText(getContext(),result, Toast.LENGTH_SHORT).show();

    }
}
