package model.radiator;

public class OffState implements RadiatorState
{
  private static final int POWER = 0;

  @Override public void turnUp(Radiator radiator)
  {
    radiator.setPowerState(new Power1State());
  }

  @Override public void turnDown(Radiator radiator)
  {
  }

  @Override public int getPower()
  {
    return POWER;
  }
}
