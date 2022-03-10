package core;

import view.radiator.RadiatorViewModel;
import view.temperature.TemperatureViewModel;

public class ViewModelFactory
{
  private TemperatureViewModel temperatureViewModel;
  private RadiatorViewModel radiatorViewModel;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    temperatureViewModel = new TemperatureViewModel(modelFactory.getTemperatureModel());
    radiatorViewModel = new RadiatorViewModel(modelFactory.getRadiatorModel());
  }

  public TemperatureViewModel getTemperatureViewModel()
  {
    return temperatureViewModel;
  }

  public RadiatorViewModel getRadiatorViewModel()
  {
    return radiatorViewModel;
  }
}
