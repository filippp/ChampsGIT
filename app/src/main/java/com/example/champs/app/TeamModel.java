package com.example.champs.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TeamModel {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        private String standPosition;
        private String standOverallGp;
        private String standPoints;
        private String standOverallW;
        private String standOverallL;
        private String standOverallD;
        private String standTeamName;

    public String getStandPosition() {
        return standPosition;
    }

    public void setStandPosition(String standPosition) {
        this.standPosition = standPosition;
    }

    public String getStandOverallGp() {
        return standOverallGp;
    }

    public void setStandOverallGp(String standOverallGp) {
        this.standOverallGp = standOverallGp;
    }

    public String getStandPoints() {
        return standPoints;
    }

    public void setStandPoints(String standPoints) {
        this.standPoints = standPoints;
    }

    public String getStandOverallW() {
        return standOverallW;
    }

    public void setStandOverallW(String standOverallW) {
        this.standOverallW = standOverallW;
    }

    public String getStandTeamName() {
        return standTeamName;
    }

    public void setStandTeamName(String standTeamName) {
        this.standTeamName = standTeamName;
    }

    public String getStandOverallD() {
        return standOverallD;
    }

    public void setStandOverallD(String standOverallD) {
        this.standOverallD = standOverallD;
    }

    public String getStandOverallL() {
        return standOverallL;
    }

    public void setStandOverallL(String standOverallL) {
        this.standOverallL = standOverallL;
    }

    /**
         *
         * @return
         * The id
         */
        public String getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }



}