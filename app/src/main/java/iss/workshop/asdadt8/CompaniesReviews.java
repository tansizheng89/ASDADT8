package iss.workshop.asdadt8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompaniesReviews extends AppCompatActivity implements ListAllReviewAdapter.ItemClickListener {

    protected RecyclerView mRecyclerView;
    protected ListAllReviewAdapter mAdapter;
    TextView textView;
    List<CompaniesReviewDTO> mDataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies_reviews);
        textView = findViewById(R.id.textView);

        Toast.makeText(this, "starting", Toast.LENGTH_SHORT).show();

        fetchData();

    }

    private void fetchData() {

        Call<List<CompaniesReviewDTO>> call = RetrofitClient.getInstance().getMyApi().getAll();
        call.enqueue(new Callback<List<CompaniesReviewDTO>>() {


            @Override
            public void onResponse(Call<List<CompaniesReviewDTO>> call, Response<List<CompaniesReviewDTO>> response) {
                List<CompaniesReviewDTO> eList = response.body();


                for (int i = 0; i < eList.size(); i++) {
                    //Log.e("name", eList.get(i).getCompanyName());
                    mDataset.add(eList.get(i));
                }
                Toast.makeText(CompaniesReviews.this, String.valueOf(mDataset.size()), Toast.LENGTH_SHORT).show();

                // textView.setText(mDataset.get(0));
                mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(CompaniesReviews.this));
                mAdapter = new ListAllReviewAdapter(mDataset,CompaniesReviews.this);
                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<List<CompaniesReviewDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this,ReviewCompanyListFetchActivity.class);
        intent.putExtra("CompanyName",mDataset.get(position).getCompanyName());
        startActivity(intent);
        //this will get the position of the clicked item from the adaptor
    }
}