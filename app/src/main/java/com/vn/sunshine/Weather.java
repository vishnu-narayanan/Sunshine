package com.vn.sunshine;

/**
 * Created by vn on 28/9/15.
 */
public class Weather {
    //private variables
    int _id;
    Double _lat;
    Double _long;
    String _weather;

    // Empty constructor
    public Weather(){

    }

    public Weather(int _id, Double _lat, Double _long, String _weather) {
        this._id = _id;
        this._lat = _lat;
        this._long = _long;
        this._weather = _weather;
    }

    public Weather(Double _lat, Double _long, String _weather) {
        this._lat = _lat;
        this._long = _long;
        this._weather = _weather;
    }

    //getters and setters

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Double get_lat() {
        return this._lat;
    }

    public void set_lat(Double _lat) {
        this._lat = _lat;
    }

    public Double get_long() {
        return this._long;
    }

    public void set_long(Double _long) {
        this._long = _long;
    }

    public String get_weather() {
        return this._weather;
    }

    public void set_weather(String _weather) {
        this._weather = _weather;
    }
}
