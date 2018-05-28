package tn.esprit.legacy.monivulation.Models;

import java.util.Date;


public class Cycle {



    private int id;

    private Date startDate;

    private float length;

    private float periodLength;

    private Date ovulationDate;

    private String fertilityStartDate;

    private String fertilityEndDate;

    private float follicularLength;

    private float lutealLength;

    private String currentStatus;

    private int currentDayOfCycle;

    private boolean considerForCalculation = true;

    public Cycle() {
    }

    public Cycle(int id, Date startDate, float length, float periodLength, Date ovulationDate, String fertilityStartDate, String fertilityEndDate, float follicularLength, float lutealLength, String currentStatus, int currentDayOfCycle, boolean considerForCalculation) {
        this.id = id;
        this.startDate = startDate;
        this.length = length;
        this.periodLength = periodLength;
        this.ovulationDate = ovulationDate;
        this.fertilityStartDate = fertilityStartDate;
        this.fertilityEndDate = fertilityEndDate;
        this.follicularLength = follicularLength;
        this.lutealLength = lutealLength;
        this.currentStatus = currentStatus;
        this.currentDayOfCycle = currentDayOfCycle;
        this.considerForCalculation = considerForCalculation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(float periodLength) {
        this.periodLength = periodLength;
    }

    public Date getOvulationDate() {
        return ovulationDate;
    }

    public void setOvulationDate(Date ovulationDate) {
        this.ovulationDate = ovulationDate;
    }

    public String getFertilityStartDate() {
        return fertilityStartDate;
    }

    public void setFertilityStartDate(String fertilityStartDate) {
        this.fertilityStartDate = fertilityStartDate;
    }

    public String getFertilityEndDate() {
        return fertilityEndDate;
    }

    public void setFertilityEndDate(String fertilityEndDate) {
        this.fertilityEndDate = fertilityEndDate;
    }

    public float getFollicularLength() {
        return follicularLength;
    }

    public void setFollicularLength(float follicularLength) {
        this.follicularLength = follicularLength;
    }

    public float getLutealLength() {
        return lutealLength;
    }

    public void setLutealLength(float lutealLength) {
        this.lutealLength = lutealLength;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public boolean isConsiderForCalculation() {
        return considerForCalculation;
    }

    public void setConsiderForCalculation(boolean considerForCalculation) {
        this.considerForCalculation = considerForCalculation;
    }

    public int getCurrentDayOfCycle() {
        return currentDayOfCycle;
    }

    public void setCurrentDayOfCycle(int currentDayOfCycle) {
        this.currentDayOfCycle = currentDayOfCycle;
    }

    @Override
    public String toString() {
        return "Cycle{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", length=" + length +
                ", periodLength=" + periodLength +
                ", ovulationDate=" + ovulationDate +
                ", fertilityStartDate=" + fertilityStartDate +
                ", fertilityEndDate=" + fertilityEndDate +
                ", follicularLength=" + follicularLength +
                ", lutealLength=" + lutealLength +
                ", currentStatus='" + currentStatus + '\'' +
                ", considerForCalculation=" + considerForCalculation +
                '}';
    }
}
