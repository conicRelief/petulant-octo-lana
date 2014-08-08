package com.example.ottowagner.rxedward;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ottowagner on 8/8/14.
 */

//EMA Works by applying more weight to recent recorded values.

public class ExponentialMovingAverage implements Observable.Operator<Double,Double> {
  @Override
  public Subscriber<? super Double> call(Subscriber<? super Double> subscriber) {

    Subscriber a = new Subscriber() {
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onNext(Object o) {

      }
    };

    return a;
  }
}
