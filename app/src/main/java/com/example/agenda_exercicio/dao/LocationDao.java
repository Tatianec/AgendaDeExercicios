package com.example.agenda_exercicio.dao;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class LocationDao implements LocationListener {
    private double totalDistance;
    private long startTime;
    private Location startPoint;

    public LocationDao() {
        reset();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (startPoint == null) {
            startPoint = location;
            startTime = System.currentTimeMillis();
        } else {
            double distance = startPoint.distanceTo(location);
            totalDistance += distance;
            startPoint = location;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Método chamado quando o provedor de localização é ativado
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Método chamado quando o provedor de localização é desativado
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Método chamado quando o status do provedor de localização é alterado
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public long getStartTime() {
        return startTime;
    }

    public void reset() {
        totalDistance = 0;
        startTime = 0;
        startPoint = null;
    }
}
