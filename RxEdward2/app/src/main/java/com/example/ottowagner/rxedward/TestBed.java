package com.example.ottowagner.rxedward;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

import rx.Observable;
import rx.Observer;


public class TestBed extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_bed);

    Double[] a = new Double[10000];


    for (int i = 0; i < a.length; i++) {
      a[i] = new Random().nextDouble();
    }


    Observable movingAverage = Observable.from(a).lift(new LazyMansMovingAverage(1000));


    movingAverage.subscribe(new Observer<Double>() {

      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onNext(Double aDouble) {
        Log.d("Observed Average", String.valueOf(aDouble));
      }
    });
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.test_bed, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
