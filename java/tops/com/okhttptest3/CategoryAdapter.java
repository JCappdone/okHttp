package tops.com.okhttptest3;

import android.icu.util.ULocale;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shriji on 18/1/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private ArrayList<CategoryModel> categories;
    CategoryAdapterCallBack categoryAdapterCallBack;

    public CategoryAdapter(ArrayList<CategoryModel> categories, CategoryAdapterCallBack categoryAdapterCallBack) {
        this.categories = categories;
        this.categoryAdapterCallBack = categoryAdapterCallBack;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adpterlayout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position) {

        final CategoryModel categoryModel = categories.get(position);
        holder.textView.setText(categoryModel.getName());


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            view = itemView;

        }
    }
}
