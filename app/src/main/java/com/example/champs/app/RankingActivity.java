package com.example.champs.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class RankingActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public static final String MATCHES_URL = "http:/10.0.2.2:8080/mobile/searchForTeams";
    TextView textView;


    private static final String TAG_TEAM = "teams";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";

    JSONArray teams = null;
    List<TeamModel> teamModelList = new ArrayList<TeamModel>();

    List<TeamModel> teamList;
    ListView listTeams;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        listTeams = (ListView) findViewById(R.id.lvTeams);
        //ImageView btnNextScreen = (ImageView) findViewById(R.id.matches);
        //textView = (TextView) findViewById(R.id.textViewTESTING);
        //textView = (TextView) findViewById(R.id.textView);

        // call AsynTask to perform network operation on separate thread

        ImageView image =  (ImageView) findViewById(R.id.imageView2);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent nextScreen = new Intent(getApplicationContext(), MatchesActivity.class);
                    startActivity(nextScreen);
            }
        });


        new getMatches().execute();
    }


    private class getMatches extends AsyncTask<String, String, List<TeamModel>> {
        
        @Override
        protected List<TeamModel> doInBackground(String... args) {
            // Making a request to url and getting response

            HttpURLConnection urlConnection = null;
            HttpURLConnection urlConnectionStand = null;
            BufferedReader result = null;
            BufferedReader standings = null;

            try {
                //URL url = new URL("http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt");
                //Genymotion 10.0.3.2
                URL url = new URL("http://10.0.3.2:8080/mobile/searchForTeams.json");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();

                result = new BufferedReader(new InputStreamReader(in));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = result.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJSON = buffer.toString();
                JSONArray array = new JSONArray(finalJSON);

                for (int i = 0; i < array.length(); i++){
                    JSONObject jsonObj  = array.getJSONObject(i);
                    TeamModel team = new TeamModel();
                    team.setId(jsonObj.getString("id"));
                    team.setName(jsonObj.getString("name"));
                    teamModelList.add(team);
                }


            }catch( MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();

            }

            try {
                URL urlStandings = new URL("http://10.0.2.2:8080/mobile/getStandings.json");
                urlConnectionStand = (HttpURLConnection) urlStandings.openConnection();
                urlConnection.connect();
                InputStream in = urlConnectionStand.getInputStream();

                standings = new BufferedReader(new InputStreamReader(in));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = standings.readLine()) != null) {
                    buffer.append(line);
                }

                String standingsJSON = buffer.toString();
                JSONArray arrayStandings = new JSONArray(standingsJSON);

                for (int i = 0; i < arrayStandings.length(); i++){
                    JSONObject jsonObj  = arrayStandings.getJSONObject(i);
                    for (TeamModel team : teamModelList){
                        if (team.getName().equals(jsonObj.getString("standTeamName"))){
                            team.setStandOverallW(jsonObj.getString("standOverallW"));
                            team.setStandTeamName(jsonObj.getString("standTeamName"));
                            team.setStandPosition(jsonObj.getString("standPosition"));
                            team.setStandOverallGp(jsonObj.getString("standOverallGp"));
                            team.setStandPoints(jsonObj.getString("standPoints"));
                            //team.setStandOverallW(jsonObj.getString("standOverallW"));
                            team.setStandOverallL(jsonObj.getString("standOverallL"));
                            team.setStandOverallD(jsonObj.getString("standOverallD"));
                        }
                    }
                    //TO DO szukanei po nazwach + uzepleninie info + sortowanie na koniec
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                urlConnectionStand.disconnect();
            }

            Comparator comp = new Comparator();
            Collections.sort(teamModelList, comp);
            return teamModelList;
        }

        @Override
        protected void onPostExecute(List<TeamModel> teamModelList) {
            super.onPostExecute(teamModelList);
            TeamAdapter adapter = new TeamAdapter(getApplicationContext(), R.layout.teamrow, teamModelList);
            listTeams.setAdapter(adapter);
        }
    }

    public class TeamAdapter extends ArrayAdapter{

        public List<TeamModel> teamModelList;
        private int resource;
        private LayoutInflater inflater;

        public TeamAdapter(Context context, int resource, List<TeamModel> objects) {
            super(context, resource, objects);
            teamModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            ImageView lvTeamIcon;
            TextView teamName;
            TextView tvMatches;
            TextView tvPoints;
            TextView tvWins;
            TextView tvLoses;
            TextView tvDraws;

            TextView tvMatchesN;
            TextView tvPointsN;
            TextView tvWinsN;
            TextView tvLosesN;
            TextView tvDrawsN;

            lvTeamIcon = (ImageView) convertView.findViewById(R.id.ivTeam);
            teamName = (TextView) convertView.findViewById(R.id.textTeamName);
           /* tvMatches = (TextView) convertView.findViewById(R.id.textMatches);
            tvPoints = (TextView) convertView.findViewById(R.id.textPoints);
            tvWins = (TextView) convertView.findViewById(R.id.textWins);
            tvLoses = (TextView) convertView.findViewById(R.id.textLoses);
            tvDraws = (TextView) convertView.findViewById(R.id.textDraws);*/


            tvMatchesN = (TextView) convertView.findViewById(R.id.textMatchesN);
            tvPointsN = (TextView) convertView.findViewById(R.id.textPointsN);
            tvWinsN = (TextView) convertView.findViewById(R.id.textWinsN);
            tvLosesN =(TextView) convertView.findViewById(R.id.textLosesN);
            tvDrawsN =(TextView) convertView.findViewById(R.id.textDrawsN);


            teamName.setText(teamModelList.get(position).getName());
            tvMatchesN.setText(teamModelList.get(position).getStandOverallGp());
            tvPointsN.setText(teamModelList.get(position).getStandPoints());
            tvWinsN.setText(teamModelList.get(position).getStandOverallW());
            tvLosesN.setText(teamModelList.get(position).getStandOverallL());
            tvDrawsN.setText(teamModelList.get(position).getStandOverallD());


            return convertView;
        }
    }

}

