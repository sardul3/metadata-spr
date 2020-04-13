package com.sagar.metadatagooglesheet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stationName;
    private String latitude;
    private String longitude;
    private String elevation;
    private String ipAddress;
    private String phone;
    private String SHEF;
    private String wdl_id;
    private String county;
    private String city;
    private String tower;
    private String anchorType;
    private String towerInstalled;
    private String datalogger;
    private String os;
    private String dataloggerSN;
    private String modem;
    private String modemSN;
    private String modemInstall;
    private String anteena;
    private String antennaSN;
    private String antennaInstall;
    private String tairRh2m;
    private String tairRh2mSN;
    private String tairRh2mInstall;
    private String tairRh10m;
    private String tairRh10mSN;
    private String tairRh10mInstall;
    private String solarSensor;
    private String solarSensorSN;
    private String solarSensorInstall;
    private String precipitation;
    private String precipitationSN;
    private String precipitationInstall;
    private String wspdDir2m;
    private String wspdDir2mSN;
    private String wspdDir2mInstall;
    private String wspdDir10m;
    private String wspdDir10mSN;
    private String wspdDir10mInstall;
    private String soilProbe2;
    private String soilProbe2SN;
    private String soilProbe2Install;
    private String soilProbe4;
    private String soilProbe4SN;
    private String soilProbe4Install;
    private String soilMoisture;
    private String soilMoistureSN;
    private String soilMoistureInstall;
    private String barometer;
    private String barometerSN;
    private String barometerInstall;
    private String solarPanel;
    private String solarPanelSN;
    private String solarPanelInstall;
    private String chargeController;
    private String chargeControllerInstall;
    private String dataloggerBattery;
    private String dataloggerBatteryInstall;
    private String heaterBattery;
    private String heaterBatteryInstall;
}
