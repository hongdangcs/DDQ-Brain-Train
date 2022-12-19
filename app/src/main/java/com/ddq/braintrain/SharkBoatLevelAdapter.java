package com.ddq.braintrain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ddq.braintrain.models.SharkBoatModel;

import java.util.ArrayList;

public class SharkBoatLevelAdapter{ /*extends RecyclerView.Adapter<SharkBoatLevelAdapter.ViewHolder>{

    private ArrayList<SharkBoatModel> sharkBoatModels;
    private Context mContext;

    SharkBoatLevelAdapter(Context context, ArrayList<SharkBoatModel> sportsData) {
        this.sharkBoatModels = sportsData;
        this.mContext = context;
    }


    @Override
    public SharkBoatLevelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SharkBoatLevelAdapter.ViewHolder holder, int position) {
        SharkBoatModel currentSport = sharkBoatModels.get(position);
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return sharkBoatModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mSportsImage = itemView.findViewById(R.id.sportsImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(SharkBoatModel currentSport) {
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());
            mSportsImage.setImageResource(currentSport.getImageResource());
        }

        @Override
        public void onClick(View view) {
            SharkBoatModel currentSport = mSportsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource", currentSport.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }*/
}
