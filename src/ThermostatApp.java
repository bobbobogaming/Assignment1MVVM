import core.ModelFactory;
import core.ViewHandler;
import core.ViewModelFactory;
import external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;

public class ThermostatApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ModelFactory mf = new ModelFactory();

    Thermometer thermometer1 = new Thermometer(mf.getTemperatureModel(), "t1",0,1);
    Thermometer thermometer2 = new Thermometer(mf.getTemperatureModel(), "t2",0,7);
    Thermometer thermometer0 = new Thermometer(mf.getTemperatureModel(),0);

    Thread thread0 = new Thread(thermometer0);
    thread0.setDaemon(true);
    thread0.start();

    Thread thread = new Thread(thermometer1);
    thread.setDaemon(true);
    thread.start();

    Thread thread2 = new Thread(thermometer2);
    thread2.setDaemon(true);
    thread2.start();

    ViewModelFactory viewModelFactory = new ViewModelFactory(mf);
    ViewHandler viewHandler = new ViewHandler(stage,viewModelFactory);

    mf.getRadiatorModel().addPropertyChangeListener(thermometer1);
    mf.getRadiatorModel().addPropertyChangeListener(thermometer2);

    viewHandler.start();
  }
}
