package mediator.temperatoremediator;

import model.temperature.Temperature;
import model.temperature.TemperatureList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureModelManager implements TemperatureModel
{
  private TemperatureList temperatureList;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private int min;
  private int max;

  public TemperatureModelManager()
  {
    temperatureList = new TemperatureList();
  }

  @Override public void defineMinMax(int min, int max)
  {
    this.min = min;
    this.max = max;
  }

  @Override public void addTemperature(String id, double temperature)
  {
    Temperature temp = new Temperature(id, temperature);
    support.firePropertyChange("New temp",temperatureList.isEmpty()? null:getLastTemperature(),temp);
    if (temperature>max || temperature<min)
      support.firePropertyChange(temperature>max?"Max":"Min",temperatureList.isEmpty()? null:getLastTemperature(),temp);
    else
      support.firePropertyChange("Normalize",temperatureList.isEmpty()? null:getLastTemperature(),temp);
    temperatureList.add(temp);
  }

  @Override public Temperature getLastTemperature()
  {
    return temperatureList.get(temperatureList.size()-1);
  }

  @Override public Temperature getLastTemperature(String id)
  {
    return temperatureList.getById(id);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
