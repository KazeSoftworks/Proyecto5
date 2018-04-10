package spellkaze.countryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by Spellkaze on 09/04/2018.
 */

public class AdapterSettingList extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Continent> listContinent;

    public AdapterSettingList(Activity activity, ArrayList<Continent> listContinent)
    {
        this.activity = activity;
        this.listContinent = listContinent;
    }

    @Override
    public int getCount() {
        return listContinent.size();
    }

    @Override
    public Object getItem(int position) {
        return listContinent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View customView = view;
        final int position = i;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView= inf.inflate(R.layout.setting_list_layout, null);
        }

        Continent continent = listContinent.get(position);

        CheckBox checkBox = customView.findViewById(R.id.checkBox);
        checkBox.setText(continent.getContinent());
        checkBox.setChecked(continent.getIsActive());


        return customView;
    }
}
