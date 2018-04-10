package spellkaze.countryapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {

    WebView webView;
    DataHolder dataHolder = DataHolder.getInstance();

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_web, container, false);



        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {

        super.onActivityCreated(state);
        webView = getView().findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        String URL = "https://en.wikipedia.org/wiki/" + dataHolder.getSelectedCountry();

        webView.loadUrl(URL);

    }
}
