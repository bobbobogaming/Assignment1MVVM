package external;

import mediator.temperatoremediator.TemperatureModel;
import model.temperature.Temperature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Thermometer implements Runnable, PropertyChangeListener
{
  private String id;
  private double t;
  private double t0;
  private int d;
  int p;
  private TemperatureModel model;

  public Thermometer(TemperatureModel model,String id, double t, int d)
  {
    this.model = model;
    this.id = id;
    this.t = t;
    this.d = d;
    p = 0;
    t0 = 0;
    model.addPropertyChangeListener(this);
  }

  public Thermometer(TemperatureModel model, double t0){
    this.model = model;
    id = "t0";
    this.t0 = t0;
  }

  private double temperature(double t, int p, int d, double t0, int s){
    double tMax = Math.min(11 * p + 10, 11 * p + 10 + t0);
    tMax = Math.max(Math.max(t, tMax), t0);
    double heaterTerm = 0;

    if (p > 0){
      double den = Math.max((tMax * (20 - 5 * p) * (d + 5)), 0.1);
      heaterTerm = 30 * s * Math.abs(tMax - t) / den;
    }

    double outdoorTerm = (t - t0) * s / 250.0;
    t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);
    return t;
  }

  public double externalTemperature(double t0, double min, double max)
  {
    double left = t0 - min;
    double right = max - t0;
    int sign = Math.random() * (left + right) > left ? 1 : -1;
    t0 += sign * Math.random();
    return t0;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("State chance"))
      p = (int) evt.getNewValue();
    else if (evt.getPropertyName().equals("New temp"))
      if (((Temperature)evt.getNewValue()).getId().equals("t0"))
        t0 = ((Temperature) evt.getNewValue()).getTemp();
  }

  @Override public void run()
  {
    while (true){
      if (!id.equals("t0")){
        t=temperature(t,p,d,t0,6);
        model.addTemperature(id,t);
        System.out.println(id + ": " + t + " deg, at power: " + p + " and t0 at: " + t0);
        try
        {
          Thread.sleep(6000);
        }
        catch (InterruptedException e)
        {
          break;
        }
      } else {
        t0=externalTemperature(t0,-5,20);
        model.addTemperature(id,t0);
        System.out.println(id + ": " + t0);
        try
        {
          Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
          break;
        }
      }
    }
  }
}
