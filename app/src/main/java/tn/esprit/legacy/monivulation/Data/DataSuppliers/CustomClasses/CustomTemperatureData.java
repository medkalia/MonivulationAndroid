package tn.esprit.legacy.monivulation.Data.DataSuppliers.CustomClasses;

import java.util.Date;


public class CustomTemperatureData {



    private int id;

    private Date entryDate;

    private float value;

    private float periodLength;

    public CustomTemperatureData(int id, Date entryDate, float value, float periodLength) {
        this.id = id;
        this.entryDate = entryDate;
        this.value = value;
        this.periodLength = periodLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(float periodLength) {
        this.periodLength = periodLength;
    }

    @Override
    public String toString() {
        return "CustomTemperatureData{" +
                "id=" + id +
                ", entryDate=" + entryDate +
                ", value=" + value +
                ", periodLength=" + periodLength +
                '}';
    }
}
