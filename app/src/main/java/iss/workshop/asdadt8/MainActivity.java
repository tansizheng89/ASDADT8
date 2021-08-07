package iss.workshop.asdadt8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnFetch;
    ReviewApi apiInterface;
    private TextView responseTV;
    private EditText reviewStar, reviewCompany, reviewUser, reviewDescription, reviewJobTitle, reviewDate;
    private EditText reviewDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reviewStar = findViewById(R.id.ReviewStars);
        reviewDescription = findViewById(R.id.ReviewDescription);
        reviewCompany = findViewById(R.id.ReviewCompany);
        reviewJobTitle = findViewById(R.id.ReviewJobTitle);
        reviewUser = findViewById(R.id.ReviewUser);
        reviewDate = findViewById(R.id.ReviewDate);

        reviewDelete = findViewById(R.id.DeleteReview);

        Button gotolist = findViewById(R.id.gotolist);
        if(gotolist != null){
            gotolist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this,ReviewListFetchActivity.class);
                            startActivity(intent);
                        }
                    }).start();
                }
            });
        }

        Button delete = findViewById(R.id.delete);
        if(delete != null){
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            deleteReview(reviewDelete.getText().toString());
                        }
                    }).start();
                }
            });
        }
        Button ok = findViewById(R.id.ok);
        if(ok != null){
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String reviewstar =  reviewStar.getText().toString();
                    String reviewcompany =  reviewCompany.getText().toString();
                    String reviewuser = reviewUser.getText().toString();
                    String reviewdescription = reviewDescription.getText().toString();
                    String reviewjobname = reviewJobTitle.getText().toString();
                    String reviewdate = reviewDate.getText().toString();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            createReview(reviewstar, reviewcompany,reviewuser,reviewdescription, reviewjobname,reviewdate);
                        }
                    }).start();
                }
            });
        }

        Button ratingsAllBtn = findViewById(R.id.ListAllRatings);
        if(ratingsAllBtn!=null){
            ratingsAllBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,CompaniesReviews.class);
                    startActivity(intent);
                }
            });
        }
    }
    private void deleteReview(String id){
        long processid = Long.parseLong(id);
        Long pass = new Long(processid);
        Call<Void> call1 =  RetrofitClient.getInstance().getMyApi().deleteReview(processid);
        call1.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call1, Response<Void> response) {
                if(response.isSuccessful()){
                    //Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void createReview(String reviewStar, String reviewCompany, String reviewUser, String reviewDescription, String reviewJobTitle, String reviewDate) {

        float rreviewStar = Float.parseFloat(reviewStar);
        long rreviewUser = Long.parseLong(reviewUser);
        final ReviewDTO review = new ReviewDTO(rreviewStar, reviewCompany, rreviewUser, reviewDescription, reviewJobTitle,reviewDate);
        Call<ReviewDTO> call1 =  RetrofitClient.getInstance().getMyApi().createReview(review);
        call1.enqueue(new Callback<ReviewDTO>() {
            @Override
            public void onResponse(Call<ReviewDTO> call1, Response<ReviewDTO> response) {
                if(response.isSuccessful()){
                    //Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<ReviewDTO> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}

