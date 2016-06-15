package sau.mertcelen.webinarandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static int _eventID = -1;
    public static final String _url = "http://webinar.sakarya.edu.tr/api/WApi/List";
    private ArrayList<Webinar> list;
    private ArrayList<String> deneme;
    boolean flag= true;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new JSONParse().execute();
        ListView currentList = (ListView)findViewById(R.id.currentList);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                deneme );

        currentList.setAdapter(arrayAdapter);

        currentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _eventID = list.get(position).getId();
                Intent intent = new Intent(MainActivity.this, LiveStream.class);
                startActivity(intent);
            }
        });


    }


    private class JSONParse extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            list = new ArrayList<>();
            deneme = new ArrayList<>();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            String json = jParser.getJSONFromUrl(_url,false);
            return json;
        }
        @Override
        protected void onPostExecute(String json) {

            try {
                // Getting JSON Array
                JSONArray array  = new JSONArray(json);
                for(int i=0;i<array.length();i++){
                    JSONObject current = array.getJSONObject(i);
                    int id = Integer.valueOf(current.getString("WebinarId"));
                    String title = current.getString("WebinarTitle");
                    String description = current.getString("Description");
                    String presenterName = current.getString("PresenterName");
                    String guestName = current.getString("GuestName");
                    String guestMail = current.getString("GuestMail");
                    Boolean online = Boolean.valueOf(current.getString("Online"));
                    Boolean completed = Boolean.valueOf(current.getString("Completed"));
                    Boolean started = Boolean.valueOf(current.getString("Started"));
                    String startDate = current.getString("StartDate");
                    String startMessage = current.getString("StartMessage");
                    Boolean isPublic = Boolean.valueOf(current.getString("IsPublic"));
                    int duration = Integer.valueOf(current.getString("Duration"));
                    Boolean enApplause = Boolean.valueOf(current.getString("EnApplause"));
                    Boolean enMessage = Boolean.valueOf(current.getString("EnMessage"));
                    Boolean enLobby= Boolean.valueOf(current.getString("EnLobby"));
                    int maxUser = Integer.valueOf(current.getString("MaxUser"));

                    list.add(new Webinar(title,description,presenterName,guestName,guestMail,
                            startDate,startMessage,id,duration,maxUser,online,completed,started,isPublic,
                            enApplause,enMessage,enLobby));

                    deneme.add(title);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("mert","Found " + deneme.size() + " events going on.");
            Log.i("mert",deneme.toString());
            arrayAdapter.notifyDataSetChanged();
        }
    }

}



