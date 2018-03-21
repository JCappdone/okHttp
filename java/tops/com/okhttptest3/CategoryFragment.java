package tops.com.okhttptest3;


import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener, AsyncTaskCallBack, CategoryAdapterCallBack {


    private static final int SELECT_CATRGORIES=1;
    private static final int INSERT_CATEGORY=2;
    private ArrayList<CategoryModel> categories;
    RecyclerView recyclerView;
    Button add;
    Context context;
    private AlertDialog dialog;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        add = view.findViewById(R.id.add);
        context = getActivity();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        add.setOnClickListener(this);
        preparedata();



        return view;
    }



    @Override
    public void onClick(View v) {
        showInputAlertDalog();



    }

    private void showInputAlertDalog() {
        View customView = LayoutInflater.from(context)
                .inflate(R.layout.add_category_dialog,null);

        final EditText edtCategory=customView.findViewById(R.id.edtcategory);
        Button btnaddcategory=customView.findViewById(R.id.btnaddcategory);

        btnaddcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category=edtCategory.getText().toString();
                saveCategoryOnServer(category);
                dialog.dismiss();
                recyclerView.invalidate();

//                CategoryAdapter adapter1 = new CategoryAdapter(categories,CategoryFragment.this);
//                adapter1.notifyDataSetChanged();




            }
        });

        dialog = new AlertDialog.Builder(context).setView(customView).show();




    }

    private void saveCategoryOnServer(String category) {
        String url="mycategory.php";
        HashMap<String,String> hm=new HashMap<>();
        hm.put("flag","1");
        hm.put("category",category);
        MyAsyncTask asyncTask = new MyAsyncTask(this,INSERT_CATEGORY);
        asyncTask.setHashMap(hm);
        asyncTask.execute(url);
    }


    private void preparedata() {
        String url = "mycategory.php";
        HashMap<String,String> hm = new HashMap<>();
        hm.put("flag","4");
        MyAsyncTask asyncTask=new MyAsyncTask(this,SELECT_CATRGORIES);
        asyncTask.setHashMap(hm);
        asyncTask.execute(url);
    }

    @Override
    public void asyncTask(String result, int flag) {
        switch (flag)
        {
            case SELECT_CATRGORIES:
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    categories=new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject categoryObject=jsonArray.getJSONObject(i);
                        int id= Integer.parseInt(categoryObject.getString("id"));
                        String name=categoryObject.getString("httpcategory");
                        categories.add(new CategoryModel(id,name));
                    }

                    CategoryAdapter adapter = new CategoryAdapter(categories,CategoryFragment.this);
                    recyclerView.setAdapter(adapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case INSERT_CATEGORY:
                Toast.makeText(context,result, Toast.LENGTH_SHORT).show();
                preparedata();
                break;

        }



    }




    @Override
    public void changeFragment(CategoryModel categoryModel, int flag) {

    }
}
