package hw4.properties;

public class Filter {

    private String priority;
    private String severity;
    private String status;
    private String startYear;
    private String startMonth;
    private String startDay;
    private String endYear;
    private String endMonth;
    private String endDay;

    public Filter(String priority, String severity, String status, String startYear,String startMonth,String startDay,String endYear, String endMonth,String endDay)
    {
        this.priority = priority;
        this.severity = severity;
        this.status = status;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endYear = endYear;
        this.startMonth = endMonth;
        this.endDay = endDay;
    }

    public String getPriority(){return priority;}
    public String getSeverity(){return severity;}
    public String getStatus(){return status;}
    public String getStartYear(){return startYear;}
    public String getStartMonth(){return startMonth;}
    public String getStartDay(){return startDay;}
    public String getEndYear(){return endYear;}
    public String getEndMonth(){return endMonth;}
    public String getEndDay(){return endDay;}
}
