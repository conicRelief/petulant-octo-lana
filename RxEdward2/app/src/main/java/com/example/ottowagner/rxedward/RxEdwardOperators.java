package com.example.ottowagner.rxedward;

import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by ottowagner on 8/1/14.
 */
public class RxEdwardOperators extends Observable<Double>
{
  /**
   * Creates an Observable with a Function to execute when it is subscribed to.
   * <p/>
   * <em>Note:</em> Use {@link #create(rx.Observable.OnSubscribe)} to create an Observable, instead of this constructor,
   * unless you specifically have a need for inheritance.
   *
   * @param f {@link rx.Observable.OnSubscribe} to be executed when {@link #subscribe(rx.Subscriber)} is called
   */
  protected RxEdwardOperators(OnSubscribe<Double> f) {
    super(f);
  }

  public Observable<Double> sma (int frame)
  {
    return lift(new SimpleMovingAverage(frame));
  }
}









//
//public class PlacesResponseToPlaceOperator implements Observable.Operator<double> {
//  @Override
//  public Subscriber<? super PlacesResonse> call(final Subscriber<? super Place> subscriber) {
//    Log.i("PlacesResponseToPlaceOperator", "SUBSCRIBED");
//
//    return new Subscriber<PlacesResonse>(subscriber) {
//      @Override
//      public void onCompleted() {
//        subscriber.onCompleted();
//      }
//
//      @Override
//      public void onError(Throwable e) {
//        subscriber.onError(e);
//      }
//
//      @Override
//      public void onNext(PlacesResonse placesResonse) {
//
//        final List<Place> places = placesResonse.getPlaces();
//        for (int i = 0, il = Math.min(places.size(), MAX_PLACES); i < il; i++) {
//          subscriber.onNext(places.get(i));
//        }
//      }
//    };
//  }
//}
/*
* private static final class PlacesResponseToPlaceOperator implements Observable.Operator<Place, PlacesResonse> {
    @Override
    public Subscriber<? super PlacesResonse> call(final Subscriber<? super Place> subscriber) {
      Log.i("PlacesResponseToPlaceOperator", "SUBSCRIBED");

      return new Subscriber<PlacesResonse>(subscriber) {
        @Override
        public void onCompleted() {
          subscriber.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
          subscriber.onError(e);
        }

        @Override
        public void onNext(PlacesResonse placesResonse) {

          final List<Place> places = placesResonse.getPlaces();
          for (int i = 0, il = Math.min(places.size(), MAX_PLACES); i < il; i++) {
            subscriber.onNext(places.get(i));
          }
        }
      };
    }
  }
*
* */