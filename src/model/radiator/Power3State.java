package model.radiator;

public class Power3State implements RadiatorState
{
  private static final int POWER = 3;
  private Thread thread;

  public Power3State(Radiator radiator)
  {
    thread = new Thread(()->{
      try
      {
        Thread.sleep(10000);
        turnDown(radiator);
      }
      catch (InterruptedException e)
      {
      }
    });
    thread.setDaemon(true);
    thread.start();
  }

  @Override public void turnUp(Radiator radiator)
  {
    if (thread.isAlive())
      thread.interrupt();
    radiator.setPowerState(new Power3State(radiator));
  }

  @Override public void turnDown(Radiator radiator)
  {
    if (thread.isAlive())
      thread.interrupt();
    radiator.setPowerState(new Power2State());
  }

  @Override public int getPower()
  {
    return POWER;
  }
}
