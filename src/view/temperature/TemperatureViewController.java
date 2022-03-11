package view.temperature;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TemperatureViewController
{


  @FXML
  Label t1Label;
  @FXML
  Label t2Label;

  @FXML Label t0Label;

  @FXML Label t1Warning;
  @FXML Label t2Warning;

  @FXML TextField maxField;
  @FXML TextField minField;

  @FXML
  LineChart t1Chart;
  @FXML
  LineChart t2Chart;

  private TemperatureViewModel viewModel;

  private XYChart.Data t1Data[] = new XYChart.Data[5];
  private XYChart.Data t2Data[] = new XYChart.Data[5];


  public void init(TemperatureViewModel temperatureViewModel){
    viewModel = temperatureViewModel;

    for (int i = 0; i < t1Data.length;i++){
      t1Data[i] = new XYChart.Data(i+"",0);
      t1Data[i].YValueProperty().bind(viewModel.t1TemperatureProperties()[i]);
    }

    for (int i = 0; i < t2Data.length;i++){
      t2Data[i] = new XYChart.Data(i+"",0);
      t2Data[i].YValueProperty().bind(viewModel.t2TemperatureProperties()[i]);
    }

    t0Label.textProperty().bind(viewModel.t0Property());

    XYChart.Series series= new XYChart.Series();
    series.getData().addAll(t1Data);
    series.setName("t1");
    t1Chart.getData().add(series);

    XYChart.Series series2 = new XYChart.Series();
    series2.getData().addAll(t2Data);
    series2.setName("t2");
    t2Chart.getData().add(series2);

    t1Warning.setStyle("-fx-text-fill: Red");
    t2Warning.setStyle("-fx-text-fill: Red");
    t1Warning.textProperty().bind(viewModel.t1WarningProperty());
    t2Warning.textProperty().bind(viewModel.t2WarningProperty());
  }

  public void onUpdateButton(ActionEvent actionEvent)
  {
    viewModel.defineMinMax(Integer.parseInt(minField.getText()),Integer.parseInt(maxField.getText()));
  }
}
