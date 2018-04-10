package spellkaze.countryapp;

import java.util.ArrayList;

/**
 * Created by Spellkaze on 08/04/2018.
 */

public class DataHolder {

    private static DataHolder instance;
    //private ArrayList<String> continents;
    private ArrayList<Continent> continents;

    private ArrayList<Country> countryList;
    private ArrayList<Country> selectedCountryList;
    private String selectedContinent;
    private String selectedCountry;

    private DataHolder()
    {
        //Bloquear instancias
    }

    public static DataHolder getInstance()
    {
        if(instance == null)
        {
            instance = new DataHolder();
        }
        return instance;
    }

    public void setContinents (ArrayList<Continent> continents)
    {
        this.continents = continents;
    }

    public ArrayList<Continent> getContinents()
    {
        return continents;
    }

    public void setCountryList(ArrayList<Country> countryList)
    {
        this.countryList = countryList;
    }

    public ArrayList<Country> getCountryList()
    {
        return countryList;
    }


    public void setSelectedCountryList(ArrayList<Country> selectedCountryList)
    {
        this.selectedCountryList = selectedCountryList;
    }

    public ArrayList<Country> getSelectedCountryList()
    {
        return selectedCountryList;
    }

    public void setSelectedCountry(String selectedCountry)
    {
        this.selectedCountry = selectedCountry;
    }

    public String getSelectedCountry()
    {
        return selectedCountry;
    }

    public void setSelectedContinent(String selectedContinent)
    {
        this.selectedContinent = selectedContinent;
    }

    public String getSelectedContinent()
    {
        return selectedContinent;
    }

    public void changeIsActive(int position, boolean change){
            continents.get(position).setIsActive(change);

    }

    public ArrayList<Continent> getActiveContinents()
    {
        ArrayList<Continent> activeContinents = new ArrayList<>();

        for(Continent continent : continents)
        {
            if(continent.getIsActive())
            {
                activeContinents.add(continent);
            }
        }
        return activeContinents;
    }

}
