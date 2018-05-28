package tn.esprit.legacy.monivulation.Data.DataSuppliers.CustomClasses;

import java.util.Date;


public class CustomCycle {



    private int id;

    private Date startDate;

    private float length;

    private float periodLength;



    public CustomCycle(int id, Date startDate, float length, float periodLength) {
        this.id = id;
        this.startDate = startDate;
        this.length = length;
        this.periodLength = periodLength;

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



    @Override
    public String toString() {
        return "CustomCycle{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", length=" + length +
                ", periodLength=" + periodLength +
                '}';
    }
}
