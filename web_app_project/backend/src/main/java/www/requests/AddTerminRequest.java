package www.requests;

import java.util.Arrays;


import javax.validation.constraints.NotBlank;

public class AddTerminRequest {
    public String year;
    public String month;
    public String day;
    public String startHour;
    public String startMinute;
    public String endHour;
    public String endMinute;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(String startMinute) {
        this.startMinute = startMinute;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(String endMinute) {
        this.endMinute = endMinute;
    }

    @Override
    public String toString() {
        return "AddTerminRequest{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", startHour='" + startHour + '\'' +
                ", startMinute='" + startMinute + '\'' +
                ", endHour='" + endHour + '\'' +
                ", endMinute='" + endMinute + '\'' +
                '}';
    }
}
