package spellkaze.countryapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataHolder dataHolder = DataHolder.getInstance();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartFragment();

        WorldConfiguration();
        CountryConfiguration();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Continentes");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void WorldConfiguration()
    {
        ArrayList<Continent> continents = new ArrayList<>();
        AssetManager assets = getAssets();
        try {
            String[] list = assets.list("App");
            for(String file: list) {
                if (file.equals("images") ||
                        file.equals("sounds") ||
                        file.equals("webkit") ||
                        file.equals("databases")) {
                    //hacer nada
                } else {
                    continents.add(new Continent(file));
                }
            }
            dataHolder.setContinents(continents);

            for(Continent continentesNombre : continents)
            {
                Log.v("name", continentesNombre.getContinent());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void CountryConfiguration()
    {
        ArrayList<Country> countryList = new ArrayList<>();
        AssetManager assets = getAssets();
        try
        {
            for(Continent directory : dataHolder.getContinents())
            {
            String[] list = assets.list("App/" + directory.getContinent());
            for(String filename : list)
                {
                    InputStream inputStream = getAssets().open("App/" + directory.getContinent() + "/" + filename);
                    Drawable drawable = Drawable.createFromStream(inputStream, null);
                    String name = filename.substring(filename.indexOf('-')+1, filename.indexOf(".")).replace('_',' ');

                    countryList.add(new Country(drawable, name, directory.getContinent()));

                    Log.v(directory.getContinent(), directory + ": "+ name);
                }
            }
            dataHolder.setCountryList(countryList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ChangeToContinentLayout()
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new ContinentListFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        toolbar.setTitle("Continents");
    }


    public void ChangeToCountryLayout()
    {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new CountryListFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        toolbar.setTitle(dataHolder.getSelectedContinent());

    }

    public void ChangeToWebLayout()
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new WebFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        String countryName = dataHolder.getSelectedCountry();
        countryName = countryName.replace('_', ' ');
        toolbar.setTitle(countryName);

    }

    public void StartFragment()
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragment = new ContinentListFragment();
        fragmentTransaction.add(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void changeToolbarTitle(String newTitle)
    {
        toolbar.setTitle(newTitle);
    }

    @Override
    public void onBackPressed()
    {
        int count = getFragmentManager().getBackStackEntryCount();

        Log.v("Fragment", " " + count);
        if (count == 2) {
            //super.onBackPressed();
            getFragmentManager().popBackStack();
            changeToolbarTitle(dataHolder.getSelectedContinent());
        }else if(count == 1)
        {
            super.onBackPressed();
            getFragmentManager().popBackStack();
            changeToolbarTitle("Continentes");
        }
        else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        WorldConfiguration();
        CountryConfiguration();
        //here you can handle orientation change
    }
}
