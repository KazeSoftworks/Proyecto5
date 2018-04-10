package spellkaze.countryapp;

import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * Created by Spellkaze on 08/04/2018.
 */

public class Country {

    Drawable image;
    String name;
    String continent;

    public Country(Drawable image, String name, String continent)
    {
        this.image = image;
        this.name = name;
        this.continent = continent;
    }

    public Drawable getImage()
    {
        return image;
    }

    public String getName()
    {
        return name;
    }

    public String getContinent()
    {
        return continent;
    }
}
