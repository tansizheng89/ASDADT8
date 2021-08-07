package iss.workshop.asdadt8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewListFetchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ReviewDTO> reviewListResponseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list_fetch);
        recyclerView = (RecyclerView) findViewById(R.id.rvReviews);
        getReviewListData(); // call a method in which we have implement our GET type web API
    }

    private void getReviewListData() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(ReviewListFetchActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog


        (RetrofitClient.getInstance().getMyApi().getReviewsByJobandCompany("analyst","ABC Company")).enqueue(new Callback<List<ReviewDTO>>() {
            @Override
            public void onResponse(Call<List<ReviewDTO>> call, Response<List<ReviewDTO>> response) {
                //Log.d("responseGET", response.body().get(0).getName());
                progressDialog.dismiss(); //dismiss progress dialog
                reviewListResponseData = response.body();
                setDataInRecyclerView();
            }

            @Override
            public void onFailure(Call<List<ReviewDTO>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(ReviewListFetchActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss(); //dismiss progress dialog
            }
        });
    }

    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReviewListFetchActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        ReviewAdapter reviewAdapter = new ReviewAdapter(ReviewListFetchActivity.this, reviewListResponseData);
        recyclerView.setAdapter(reviewAdapter); // set the Adapter to RecyclerView
    }
}