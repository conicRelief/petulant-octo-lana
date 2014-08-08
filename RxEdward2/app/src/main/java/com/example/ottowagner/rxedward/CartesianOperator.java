package com.example.ottowagner.rxedward;

import android.util.Pair;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ottowagner on 8/6/14.
 */
public class CartesianOperator implements Observable.Operator<Pair<String,String>,Pair<String,String>> {


  @Override
  public Subscriber<? super Pair<String, String>> call(final Subscriber<? super Pair<String, String>> subscriber) {
    return new Subscriber<Pair<String, String>>() {
      List<String> numbers;
      List<String> characters;
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onNext(Pair<String, String> stringStringPair) {

        if(stringStringPair.second == "number")
        {
          if(numbers.size() == 0)
          {
            numbers.add(stringStringPair.first);
          }
          else
          {
            if(numbers.contains(stringStringPair.first))
            {}
            else
            {
              for(String temp: characters)
              {
                //Return Every permutation of existing characters with new number
                subscriber.onNext( new Pair<String,String>(temp,stringStringPair.first));
              }
            }

          }
        }
        else // Assume vallue is a character
        {
          if(characters.size() == 0)
          {
            characters.add(stringStringPair.first);
          }
          else
          {
            if(characters.contains(stringStringPair.first))
            {}
            else
            {
              for(String temp: numbers)
              {
                //Return Every permutation of existing numbers with new character
                subscriber.onNext( new Pair<String,String>(stringStringPair.first,temp));
              }
            }

          }


        }





      }
    };
  }
}

