package iss.workshop.asdadt8;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends
        RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    Context context;
    List<ReviewDTO> reviewListResponseData;

    public ReviewAdapter(Context context, List<ReviewDTO> reviewListResponseData) {
        this.reviewListResponseData = reviewListResponseData;
        this.context = context;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.review_layout, null);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);
        return reviewViewHolder;
    }
    @Override
    public void onBindViewHolder(ReviewViewHolder holder, final int position) {
        // set the data
        holder.reviewDescription.setText("Description: " + reviewListResponseData.get(position).getReviewDescription());
        holder.reviewCompany.setText("Company: " + reviewListResponseData.get(position).getCompanyName());
        holder.reviewJob.setText("Job: " + reviewListResponseData.get(position).getJobTitle());
        holder.reviewStar.setText("Stars: " + String.valueOf(reviewListResponseData.get(position).getReviewstars()));
        holder.reviewUser.setText("User: " + String.valueOf(reviewListResponseData.get(position).getUserId()));
        holder.reviewDate.setText("Date: " + String.valueOf(reviewListResponseData.get(position).getReviewDate()));
        // implement setONCLickListtener on itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with user name
                Toast.makeText(context, reviewListResponseData.get(position).getReviewDescription(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return reviewListResponseData.size(); // size of the list items
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView reviewDescription, reviewStar,reviewJob, reviewCompany,reviewUser, reviewDate;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            reviewDate = (TextView) itemView.findViewById(R.id.ReviewDate);
            reviewUser = (TextView) itemView.findViewById(R.id.ReviewUser);
            reviewCompany = (TextView) itemView.findViewById(R.id.ReviewCompany);
            reviewJob = (TextView) itemView.findViewById(R.id.ReviewJob);
            reviewDescription = (TextView) itemView.findViewById(R.id.ReviewDescription);
            reviewStar = (TextView) itemView.findViewById(R.id.ReviewStar);
        }}
}
