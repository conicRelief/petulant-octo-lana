package com.example.ottowagner.rxedward;


import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/*
*This is a simple Moving Average as defined by wikipedia:
*
*                 http://en.wikipedia.org/wiki/Moving_average#Simple_moving_average
*
*
* TO DO: REPLACE NON PRIMITIVE DOUBLES WITH PRIMITIVE VARIABLES!!!! GARBAGE COLLECTION MAY DESTROY SMA OTHERWISE!!!
*
* */

public class SimpleMovingAverage implements Observable.Operator<Double, Double> {
  private int frame;

  public SimpleMovingAverage(int frame) {
    this.frame = frame;
  }

  public SimpleMovingAverage() {
    this.frame = 10;
  }

  @Override
  public Subscriber<? super Double> call(final Subscriber<? super Double> subscriber) {
    return new Subscriber<Double>() {
      double[] dubList = new double[frame];
      int counter = 0;
      int replaceCounter = 0;
      double sum = 0;
      double average = 0;
      double passedDub = 0;

      @Override
      public void onCompleted() {
        subscriber.onCompleted();
      }

      @Override
      public void onError(Throwable e) {
        subscriber.onError(e);
      }

      @Override
      public void onNext(Double aDouble) {

        passedDub = aDouble.doubleValue();  //Non Primitive Overhead
        aDouble = null;

        if (counter < frame) {
          dubList[counter] = passedDub;
          counter++;
        } else {
          if (replaceCounter < frame - 1) {
            dubList[replaceCounter] = passedDub;
            replaceCounter++;
          } else {
            dubList[replaceCounter] = passedDub;
            replaceCounter = 0;
          }
          sum = sum(dubList);
          Log.d("SMA MOVING SUM", String.valueOf(sum));
          average = sum / (frame);
          Log.d("SMA MOVING AVERAGE", String.valueOf(average));
          subscriber.onNext(new Double(average));        //Non Primitive Overhead
        }
      }
    };
  }

  private double sum(double[] a) {
    double sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
    }
    return sum;
  }
}
