package com.example.googlemapapidemo;

public class Route {

    private OverviewPolyline overviewPolyline;

    public Route() {
    }

    public Route(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

}
