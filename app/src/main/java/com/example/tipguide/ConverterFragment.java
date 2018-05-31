package com.example.tipguide;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;


public class ConverterFragment extends Fragment {
        public ConverterFragment() {
        }
        TextView t;
        public int to;
        public int from;
        public String[] val;
        public String s;
        String exResult;
        public Handler handler;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.item_converter, container, false);
            t = rootView.findViewById(R.id.textView4);
            Spinner s1 = rootView.findViewById(R.id.spinner1);
            Spinner s2 = rootView.findViewById(R.id.spinner2);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    Objects.requireNonNull(this.getActivity()), R.array.name, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
            val = getResources().getStringArray(R.array.value);
            s1.setAdapter(adapter);
            s2.setAdapter(adapter);
            s1.setOnItemSelectedListener(new spinOne(1));
            s2.setOnItemSelectedListener(new spinOne(2));
            Button b = rootView.findViewById(R.id.button1);
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View View) {
                    if (from == to) {
                        Toast.makeText(getActivity().getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
                    } else {
                        new calculate().execute();
                    }
                }
            });
            return rootView;
        }

        public class calculate extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... args) {
                try {
                    s = getJson("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22"+val[from]+val[to]+"%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
                    JSONObject jObj;
                    jObj = new JSONObject(s);
                    exResult = jObj.getJSONObject("query").getJSONObject("results").getJSONObject("rate").getString("Rate");
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return exResult;
            }
            @Override
            protected void onPostExecute(String exResult) {
                t.setText(exResult);
            }
        }

        public String getJson(String url)throws  IOException {
            StringBuilder build = new StringBuilder();
            HttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String con;
            while ((con = reader.readLine()) != null) {
                build.append(con);
            }
            return build.toString();
        }

        public class spinOne implements AdapterView.OnItemSelectedListener {
            int ide;
            spinOne(int i)
            {
                ide =i;
            }
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int index, long id) {
                if(ide == 1)
                    from = index;
                else if(ide == 2)
                    to = index;

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        }
    }