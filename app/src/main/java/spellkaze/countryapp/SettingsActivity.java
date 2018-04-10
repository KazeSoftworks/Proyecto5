package spellkaze.countryapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingsActivity extends AppCompatActivity {

    DataHolder dataHolder = DataHolder.getInstance();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.8 ), (int) (height * .6));

        listView = findViewById(R.id.settingList);
    }
    @Override
    public void onStart(){
        super.onStart();
        AdapterSettingList arrayAdapter = new AdapterSettingList(this, dataHolder.getContinents());
        listView.setAdapter(arrayAdapter);
    }


}
