package core;

import mediator.radiatormediator.RadiatorModel;
import mediator.temperatoremediator.TemperatureModel;
import mediator.temperatoremediator.TemperatureModelManager;
import model.radiator.Radiator;

public class ModelFactory
{
  private TemperatureModel temperatureModel;
  private RadiatorModel radiatorModel;

  public TemperatureModel getTemperatureModel()
  {
    if (temperatureModel == null) temperatureModel = new TemperatureModelManager();
    return temperatureModel;
  }

  public RadiatorModel getRadiatorModel()
  {
    if (radiatorModel == null) radiatorModel = new Radiator();

    return radiatorModel;
  }


}
