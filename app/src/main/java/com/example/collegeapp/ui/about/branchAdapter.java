package com.example.collegeapp.ui.about;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.example.collegeapp.R;
import java.util.ArrayList;
public class branchAdapter extends PagerAdapter {
    ArrayList<branchModel> list;
    private Context context;
    public branchAdapter(ArrayList<branchModel> arrayList, Context context) {
        list = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=LayoutInflater.from(context).inflate(R.layout.view_pager_layout,container,false);
        ImageView departmentIcon;
        TextView departDescription,departmentTitle;
        departmentTitle=view.findViewById(R.id.departmentTitle);
        departDescription=view.findViewById(R.id.departmentDescription);
        departmentIcon=view.findViewById(R.id.departmentIcon);
        departmentIcon.setImageResource(list.get(position).getImg());
        departDescription.setText(list.get(position).getDescription());
        departmentTitle.setText(list.get(position).getTitle());
        container.addView(view,0);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      container.removeView((View)object);
    }
}
