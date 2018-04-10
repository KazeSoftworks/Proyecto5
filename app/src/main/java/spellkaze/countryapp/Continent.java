package spellkaze.countryapp;

/**
 * Created by Spellkaze on 09/04/2018.
 */

public class Continent {
    private String continent;
    private boolean isActive;

    public Continent(String continent)
    {
        this.continent = continent;
        isActive = true;
    }

    public String getContinent()
    {
        return continent;
    }

    public boolean getIsActive()
    {
        return isActive;
    }
}
