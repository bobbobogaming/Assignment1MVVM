package model.temperature;

import java.util.ArrayList;

public class TemperatureList extends ArrayList<Temperature>
{
  public Temperature getById(String id){
    for (int i = this.size()-1; i >= 0;i--)
    {
        if (id.equals(this.get(i).getId())) return this.get(i);
    }
    throw new IllegalArgumentException("No Temperature with id: " + id);
  }
}
