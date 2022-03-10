package model.temperature;

import java.util.Date;

public class Temperature
{
 private Date now;
 private String id;
 private double temp;


  public Temperature(String id, double temp)
  {
    this.id = id;
    this.temp = temp;
    now = new Date();
  }

  public void update(int temp){
    this.temp = temp;
  }

  public Date getNow()
  {
    return now;
  }

  public String getId()
  {
    return id;
  }

  public double getTemp()
  {
    return temp;
  }
}
