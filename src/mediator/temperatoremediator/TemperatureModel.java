package mediator.temperatoremediator;

import model.temperature.Temperature;
import util.PropertyChangeSubject;

public interface TemperatureModel extends PropertyChangeSubject
{
  void defineMinMax(int min, int max);
  void addTemperature(String id, double temperature);
  Temperature getLastTemperature();
  Temperature getLastTemperature(String id);
}
