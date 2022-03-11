package model.temperature;

public class Temperature
{
 private String id;
 private double temp;


  public Temperature(String id, double temp)
  {
    this.id = id;
    this.temp = temp;
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
