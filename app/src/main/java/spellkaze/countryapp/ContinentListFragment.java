package spellkaze.countryapp;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContinentListFragment extends Fragment {

    DataHolder dataHolder = DataHolder.getInstance();
    ListView continentListView;
    public ContinentListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_continent_list, container, false);

    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle state) {

        super.onActivityCreated(state);
        continentListView = (ListView) getView().findViewById(R.id.ContinentList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, continentTraductionString(dataHolder.getContinents()));

        continentListView.setAdapter(arrayAdapter);
        continentListView.setOnItemClickListener(SelectItemOnList);

    }

    private final AdapterView.OnItemClickListener SelectItemOnList = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String continentName = dataHolder.getContinents().get(position).getContinent();
            Log.v("ClickEvent", continentName);
            Continent continentTranslatedName = continentTraduction(dataHolder.getContinents()).get(position);

            dataHolder.setSelectedContinent(continentTranslatedName.getContinent());
            dataHolder.setSelectedCountryList(ListByContinent(dataHolder.getCountryList(), continentName));

            ((MainActivity)getActivity()).ChangeToCountryLayout();

        }
    };


    private ArrayList<Continent> continentTraduction(ArrayList<Continent> enter)
    {
        ArrayList<Continent> newList = new ArrayList<>();

        for(Continent name: enter)
        {
            if(name.getContinent().contains("_"))
            {
                newList.add(new Continent (name.getContinent().replace('_',' ')));
            }
            else
            {
                newList.add(name);
            }
        }
        return newList;
    }

    private ArrayList<String> continentTraductionString(ArrayList<Continent> enter)
    {
        ArrayList<String> newList = new ArrayList<>();

        for(Continent name: enter)
        {
            if(name.getContinent().contains("_"))
            {
                newList.add(name.getContinent().replace('_',' '));
            }
            else
            {
                newList.add(name.getContinent());
            }
        }
        return newList;
    }

    private ArrayList<Country> ListByContinent (ArrayList<Country> worldCountryList, String Continent)
    {
        ArrayList<Country> separatedCountryList = new ArrayList<>();

        for(Country country : worldCountryList)
        {
            if(country.getContinent().equals(Continent))
            {
                separatedCountryList.add(country);
            }
        }
        return separatedCountryList;
    }

    @Override
    public void onStop() {

        super.onStop();
        Log.v("Stop", "detenido");
        ((MainActivity) getActivity()).changeToolbarTitle(dataHolder.getSelectedContinent());
    }



}
