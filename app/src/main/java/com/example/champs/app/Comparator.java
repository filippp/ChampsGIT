package com.example.champs.app;

/**
 * Created by Filip on 2015-12-06.
 */
public class Comparator implements java.util.Comparator<TeamModel> {
    @Override
    public int compare(TeamModel obj, TeamModel obj2) {
        int o1 = Integer.parseInt(obj.getStandPoints());
        int o2 = Integer.parseInt(obj2.getStandPoints());

        if (o1 > o2)
            return o1;
        else
            return o2;
    }
}
