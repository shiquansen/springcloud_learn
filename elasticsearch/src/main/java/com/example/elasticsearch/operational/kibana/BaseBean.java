package com.example.elasticsearch.operational.kibana;
import lombok.Data;

import java.util.Date;

@Data
public class BaseBean {
    private String id;
    private String FlightNum;
    private String DestCountry;
    private String OriginWeather;
    private String OriginCityName;
    private Double AvgTicketPrice;
    private Double DistanceMiles;
    private Boolean FlightDelay;
    private String DestWeather;
    private String Dest;
    private String FlightDelayType;
    private String OriginCountry;
    private Integer dayOfWeek;
    private Double DistanceKilometers;
    private Date timestamp;
    private DestLocation DestLocation;
    private String DestAirportID;
    private String Carrier;
    private Boolean Cancelled;
    private Double FlightTimeMin;
    private String Origin;
    private OriginLocation OriginLocation;
    private String DestRegion;
    private String OriginAirportID;
    private String OriginRegion;
    private String DestCityName;
    private Double FlightTimeHour;
    private Integer FlightDelayMin;
}