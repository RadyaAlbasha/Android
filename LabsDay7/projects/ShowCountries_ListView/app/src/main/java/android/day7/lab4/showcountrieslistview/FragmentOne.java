package android.day7.lab4.showcountrieslistview;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.FragmentManager;
import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    private List<Country> myCountryList;
    private MyAdapter adapter;
    private ListView listCountries;
    JsonConniction jsonConniction;
    DownloadFlagsHelper downloadFlagsHelper;
    String urlFlag;
    static int index;
    Handler handler;
    Handler handlerFlag;
    Country myCountry;
    Communicator comm;
    int orientation;

    public FragmentOne() {
        // Required empty public constructor
        myCountryList = new ArrayList<Country>();
        index = 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_one, container, false);

        if(savedInstanceState != null)
        {
            myCountry = savedInstanceState.getParcelable("mycount");
        }
        listCountries = (ListView) view.findViewById(R.id.listViewCountries);
        jsonConniction=new JsonConniction();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //super.handleMessage(msg);
                myCountryList = jsonConniction.getCountriesList();
                for(int i= 0 ; i< myCountryList.size() ;i++)
                {
                    index =i;
                    urlFlag=myCountryList.get(i).getFlag();
                    downloadFlagsHelper= new DownloadFlagsHelper(urlFlag,myCountryList.get(i),i);
                    //Log.i("in handler ",String.valueOf(i));
                    //Country c = downloadFlagsHelper.getCountry();
                   // myCountryList.get(index).setImgFlag(c.getImgFlag());
                    handlerFlag = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            //super.handleMessage(msg);
                            //Log.i("in handler after start ",String.valueOf(myCountryList.get(index).getImgFlag()==null));
                            if(index == myCountryList.size()-1)
                            {
                                //Toast.makeText(getActivity().getApplicationContext(), "Download  Sucessfuly flags", Toast.LENGTH_SHORT).show();
                                adapter = new MyAdapter(view.getContext(),myCountryList);
                                Log.i("in handler ",String.valueOf(myCountryList.get(index).getImgFlag()==null));
                                listCountries.setAdapter(adapter);
                                urlFlag=myCountryList.get(index).getFlag();
                                Log.i("urlFlag",urlFlag);
                            }
                        }
                    };
                    //Log.i("in handler ",String.valueOf(myCountryList.get(i).getImgFlag()==null));
                    Thread updater2 = new Thread(downloadFlagsHelper);
                    updater2.start();


                }

            }
        };
        Thread updater = new Thread(jsonConniction);
        updater.start();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();
        orientation=comm.getOrientation();
        listCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent outIntent;
                //convert imageview to bitmap
              /*  myCountry = (Country) parent.getItemAtPosition(position);
                ImageView iv = (ImageView)view.findViewById(R.id.imageViewIcon);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) iv.getDrawable();
                myCountry.setImgFlag(bitmapDrawable.getBitmap());*/
                //myCountryList.get(position).setImgFlag(bitmapDrawable.getBitmap());
                /////////////////////////////////////////
                comm.getCurrentCountry((Country)parent.getItemAtPosition(position));
            }
        });

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("mycountry",myCountry);
    }

    class JsonConniction implements Runnable{

        private String url="https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
        private URL urlObj=null;
        //list of countries;
        private List<Country> countriesList = new ArrayList<Country>();
        private Country country;
        private HttpsURLConnection httpConn = null;
        private InputStream inputStream = null;

        public List<Country> getCountriesList() {
            return countriesList;
        }

        @Override
        public void run() {
            try {
                urlObj = new URL(url);
                httpConn = (HttpsURLConnection) urlObj.openConnection();
                httpConn.connect();
                inputStream = httpConn.getInputStream();
                String resultJson = convertStreamToString(inputStream);
                JSONObject jsonObg = new JSONObject(resultJson);
                JSONArray jArray = jsonObg.getJSONArray("worldpopulation");
                String countryFlag;
                for(int i=0 ; i < jArray.length() ; i++)
                {
                    country = new Country();
                    country.setRank(jArray.getJSONObject(i).getString("rank"));
                    country.setCountry(jArray.getJSONObject(i).getString("country"));
                    country.setPopulation(jArray.getJSONObject(i).getString("population"));
                    countryFlag=jArray.getJSONObject(i).getString("flag");
                    countryFlag=countryFlag.replaceFirst("http","https");
                    Log.i("replace_Country_Flag",countryFlag);
                    country.setFlag(countryFlag);
                    countriesList.add(country);
                }
                handler.sendEmptyMessage(0);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    ////////////////////////////////////////////////////////
    public class DownloadFlagsHelper implements Runnable{

        private String url;
        private URL urlObj=null;
        //list of countries;
        private Country country;
        private HttpsURLConnection httpConn = null;
        private InputStream inputStream = null;
        private int currentIndex;
        public DownloadFlagsHelper(String _url , Country _country ,int _index)
        {
            url = _url;
            country = _country;
            currentIndex = _index;
        }

        public Country getCountry() {
            return country;
        }

        public Bitmap download(String _url)throws IOException{
            Bitmap result = null;
            URL urlObj=null;
            HttpsURLConnection httpConn=null;
            InputStream inputStream=null;
            try {
                urlObj = new URL(_url);
                httpConn = (HttpsURLConnection) urlObj.openConnection();
                httpConn.connect();
                inputStream=httpConn.getInputStream();
                result = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            finally {
                if(inputStream != null)
                {
                    inputStream.close();
                }
            }

            return result;
        }
        @Override
        public void run() {

            Bitmap result = null;
            try {
                result = download(this.url);
               // country.setImgFlag(result);
                myCountryList.get(currentIndex).setImgFlag(result);
                //Log.i(" myCountryList",String.valueOf(currentIndex));
               // Log.i("in run ",String.valueOf(myCountryList.get(currentIndex).getImgFlag()==null));
               // Log.i("in run 0",String.valueOf(myCountryList.get(0).getImgFlag()==null));
                handlerFlag.sendEmptyMessage(0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
