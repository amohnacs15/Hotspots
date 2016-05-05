package com.androidtitan.spotscore.main.play.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidtitan.spotscore.R;
import com.androidtitan.spotscore.main.App;
import com.androidtitan.spotscore.main.play.presenter.ScorePresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ScoreActivity extends AppCompatActivity implements ScoreView {
    private final String TAG = getClass().getSimpleName();

    @Inject
    ScorePresenter mScorePresenter;

    @Bind(R.id.locationImageView) ImageView mLocationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        App.getAppComponent().inject(this);
        ButterKnife.bind(this);

        mScorePresenter.attachView(this);
        mScorePresenter.takeActivity(ScoreActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mLocationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, String.valueOf(mScorePresenter.getLastKnownLocation()));
            }
        });
    }

    @Override
    protected void onStart() {
        mScorePresenter.connectGoogleApiClient();
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mScorePresenter.googleApiIsConnected()) {
            mScorePresenter.disconnectGoogleApiClient();
        }
    }

    @Override
    protected void onStop() {
        mScorePresenter.disconnectGoogleApiClient();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScorePresenter.detachView();
    }

}
