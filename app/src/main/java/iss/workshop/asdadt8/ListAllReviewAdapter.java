package iss.workshop.asdadt8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAllReviewAdapter extends RecyclerView.Adapter<ListAllReviewAdapter.ViewHolder> {

    private List<CompaniesReviewDTO> localDataSet;
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        private final RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            this.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        public TextView getTextView() {
            return textView;
        }
        public RatingBar getRatingBar() {return ratingBar;}

        @Override
        public void onClick(View v) {
           // clickListener.onClick(v, getBindingAdapterPosition());
        }
    }


    public ListAllReviewAdapter(List<CompaniesReviewDTO> dataSet) {
        localDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet.get(position).getCompanyName());
        viewHolder.getRatingBar().setRating((float) localDataSet.get(position).getRatings());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
