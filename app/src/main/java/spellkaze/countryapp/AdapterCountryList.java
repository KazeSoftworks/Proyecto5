package spellkaze.countryapp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Spellkaze on 08/04/2018.
 */

public class AdapterCountryList extends BaseAdapter{

    protected Activity activity;
    protected ArrayList<Country> listCountry;

    public AdapterCountryList(Activity activity, ArrayList<Country> listCountry)
    {
        this.activity = activity;
        this.listCountry = listCountry;
    }

    @Override
    public int getCount() {
        return listCountry.size();
    }

    @Override
    public Object getItem(int position) {
        return listCountry.get(position);
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
            customView= inf.inflate(R.layout.country_list_layout, null);
        }
        Country country = listCountry.get(position);
        TextView textView = customView.findViewById(R.id.textView);
        textView.setText(country.getName());

        ImageView imageView = customView.findViewById(R.id.imageView);
        imageView.setImageDrawable(country.getImage());

        return customView;
    }
}
