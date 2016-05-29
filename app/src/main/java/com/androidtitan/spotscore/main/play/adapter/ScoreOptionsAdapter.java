package com.androidtitan.spotscore.main.play.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidtitan.spotscore.R;
import com.androidtitan.spotscore.main.play.PlayMvp;
import com.androidtitan.spotscore.main.play.ui.VenueListFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by amohnacs on 5/28/16.
 */
public class ScoreOptionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private Context mContext;
    private PlayMvp.Presenter mPlayPresenter;

    private ArrayList<String> mOptionsList = new ArrayList<>();
    private ArrayList<String> mOptionsDescList = new ArrayList<>();

    public ScoreOptionsAdapter(Context context, PlayMvp.Presenter playPresenter) {
        mContext = context;
        mPlayPresenter = playPresenter;

        mOptionsList.add("Save"); //floppydisc
        mOptionsList.add("Loot!"); //chest
        mOptionsList.add("Leaderboard"); //leaderboard
        mOptionsList.add("Nearby"); //geofence

        mOptionsDescList.add("Save this score for later and pull a quick one on everyone else.  Remember, you only get 3 saves.");
        mOptionsDescList.add("You\'ve been competing and you\'ve been winning.  Take a look at all of your sweet ass loot!");
        mOptionsDescList.add("Find out who\'s the top top and start devising plans to take them out.");
        mOptionsDescList.add("So, you\'re in a cool place.  Find out why and maybe grab a bite to eat.");
    }

    public static class OptionsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.relative)
        RelativeLayout mRelativeLayout;
        @Bind(R.id.scoreOptionImageView)
        ImageView mOptionImage;
        @Bind(R.id.scoreOptionsTextView)
        TextView mOptionsText;
        @Bind(R.id.scoreOptionsDescriptionTextView)
        TextView mOptionsDescription;

        public OptionsViewHolder(View itemView) {
            super(itemView);
            try {
                ButterKnife.bind(this, itemView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v1 = inflater.inflate(R.layout.card_score_options, parent, false);
        RecyclerView.ViewHolder viewHolder = new OptionsViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        initViewHolder((OptionsViewHolder) holder, position);

    }

    private void initViewHolder(OptionsViewHolder holder, int position) {

        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {

                    case 0:
                        //
                        break;

                    case 1:
                        //
                        break;

                    case 2:
                        //
                        break;

                    case 3:
                        mPlayPresenter.showFragment(new VenueListFragment(), null);
                        break;
                }
            }
        });

        holder.mOptionsText.setText(mOptionsList.get(position));
        holder.mOptionsDescription.setText(mOptionsDescList.get(position));

        switch (position) {

            case 0:
                holder.mOptionImage.setImageResource(R.drawable.ic_floppy);
                break;

            case 1:
                holder.mOptionImage.setImageResource(R.drawable.ic_treasure);

                break;

            case 2:
                holder.mOptionImage.setImageResource(R.drawable.ic_leader_trophy);

                break;

            case 3:
                holder.mOptionImage.setImageResource(R.drawable.ic_options_location);

                break;
        }
    }

    @Override
    public int getItemCount() {
        return mOptionsList.size();
    }
}
