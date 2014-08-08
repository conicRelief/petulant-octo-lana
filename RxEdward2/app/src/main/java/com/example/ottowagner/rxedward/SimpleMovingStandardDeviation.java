package com.example.ottowagner.rxedward;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ottowagner on 8/1/14.
 */
public class SimpleMovingStandardDeviation implements Observable.Operator<Double, Double> {
  private int frame;
  public SimpleMovingStandardDeviation(int frame)
  {this.frame = frame;}
  public SimpleMovingStandardDeviation()
  {this.frame = 10;}


  @Override
  public Subscriber<? super Double> call(final Subscriber<? super Double> subscriber) {
    return new Subscriber<Double>() {

      double[] dubList = new double[frame];
      int counter = 0;
      int replaceCounter = 0;
      double sum = 0;
      double average = 0;
      double passedDub = 0;
      double standardDeviationSum = 0;
      double variance = 0;


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
          Log.d("SMSTD MOVING SUM", String.valueOf(sum));
          average = sum / (frame);
          Log.d("SMSTD MOVING AVERAGE", String.valueOf(average));
          standardDeviationSum = square_difference_sum(dubList,average);
          Log.d("SMSTD MOVING SUM", String.valueOf(standardDeviationSum));
          variance = standardDeviationSum/frame;
          subscriber.onNext(new Double(Math.sqrt(variance)));        //Non Primitive Overhead
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

  private double square_difference_sum(double[] a, double mean)
  {
    double sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += (mean-a[i])*(mean-a[i]);
    }
    return sum;

  }
}
