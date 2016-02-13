package intelement.com.intelementdemo;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Senario2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private static String TAG = Senario2Activity.class.getName();
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senario2);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner_locations);
        Button navigateButton = (Button) findViewById(R.id.btnNavigate);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Button click listener
        navigateButton.setOnClickListener(this);

        // Spinner Drop down elements
        List<LocationInfo> locations = new ArrayList<LocationInfo>();

        JSONArray jsonArray = loadJson();
        if(jsonArray != null){
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    LocationInfo locationInfo = new LocationInfo();
                    JSONObject obj = jsonArray.getJSONObject(i);
                    locationInfo.setId(obj.getInt("id"));
                    locationInfo.setName(obj.getString("name"));
                    locationInfo.setFromCentral(obj.getJSONObject("fromcentral").toString());
                    JSONObject locationObj = obj.getJSONObject("location");
                    locationInfo.setLatitude(locationObj.getDouble("latitude"));
                    locationInfo.setLongitude(locationObj.getDouble("longitude"));
                    locations.add(locationInfo);
                }
            }catch (JSONException e){
                Log.e(TAG, e.getMessage(), e);
            }

            // Creating adapter for spinner
            ArrayAdapter<LocationInfo> dataAdapter = new LocationAdapter(this, android.R.layout.simple_spinner_item, locations);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

        }
    }

    private JSONArray loadJson(){
        JSONArray jsonArray = null;
        InputStream is = null;
        try {

            //is = getAssets().open("sample.json");
            is = getResources().openRawResource(R.raw.sample);
            InputStreamReader reader = new InputStreamReader(is);
            JsonReader jsonReader = new JsonReader(reader);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            String jsonString = new String(buffer, "UTF-8");

            jsonArray = new JSONArray(jsonString);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return jsonArray;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView txtTransportMode = (TextView)findViewById(R.id.txt_transport_mode);
        txtTransportMode.setText(((LocationInfo)parent.getSelectedItem()).getFromCentral());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnNavigate){
            LocationInfo locationInfo = (LocationInfo)spinner.getSelectedItem();
            String uri = String.format(Locale.ENGLISH, "geo:%f,%f", locationInfo.getLatitude(), locationInfo.getLongitude());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            this.startActivity(intent);
        }
    }

    private static class LocationAdapter extends ArrayAdapter <LocationInfo>{

        public LocationAdapter(Context context, int resource, List<LocationInfo> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = new TextView(getContext());
            }
            ((TextView)convertView).setText(getItem(position).getName());
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = View.inflate(getContext(), R.layout.spiner_item, null);
            }
            ((TextView)convertView.findViewById(R.id.txtLocationName)).setText(getItem(position).getName());
            return convertView;
        }
    }
}
