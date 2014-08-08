package com.example.ottowagner.rxedward;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ottowagner on 8/8/14.
 */
public class LazyMansMovingAverage implements Observable.Operator<Double, Double> {
  int n;

  public LazyMansMovingAverage(int n) {
    // Such that n is our frame size
    this.n = n;
  }

  public LazyMansMovingAverage() {
    this.n = 10;
  }

  @Override
  public Subscriber<? super Double> call(final Subscriber<? super Double> subscriber) {
    return new Subscriber<Double>() {
      int size = 0;
      double average = 0;

      @Override
      public void onCompleted() {

      }
      @Override
      public void onError(Throwable e) {

      }
      @Override
      public void onNext(Double aDouble) {
        double data = aDouble.doubleValue();
        aDouble = null;
        if (size < n) {
          average += data / n;
          size++;
        } else {
          average = average - average / n + data / n;
          subscriber.onNext(new Double(average));
        }
      }
    };
  }
}
