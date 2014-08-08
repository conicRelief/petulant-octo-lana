package com.example.ottowagner.rxedward;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ottowagner on 8/1/14.
 */
public class SimpleMovingStandardDeviation implements Observable.Operator<Double,Double>{
  private int frame;

  public SimpleMovingStandardDeviation(int frame)
  {this.frame = frame;}
  public SimpleMovingStandardDeviation()
  {this.frame = 10;}

  @Override
  public Subscriber<? super Double> call(Subscriber<? super Double> subscriber) {
    return null;
  }

}
