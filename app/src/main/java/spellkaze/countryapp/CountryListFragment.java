package spellkaze.countryapp;


import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends Fragment {

    DataHolder dataHolder = DataHolder.getInstance();
    ListView countryListView;
    public CountryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {

        super.onActivityCreated(state);
        countryListView = (ListView) getView().findViewById(R.id.CountryList);

        AdapterCountryList adapterCountryList = new AdapterCountryList(getActivity(), dataHolder.getSelectedCountryList());
        countryListView.setAdapter(adapterCountryList);
        countryListView.setOnItemClickListener(SelectItemOnList);
    }

    private final AdapterView.OnItemClickListener SelectItemOnList = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String countryName = dataHolder.getSelectedCountryList().get(position).getName();
            countryName = countryName.replace(' ', '_');

            Log.v("ClickEvent", countryName);
            dataHolder.setSelectedCountry(countryName);

            ((MainActivity)getActivity()).ChangeToWebLayout();

        }
    };
}
