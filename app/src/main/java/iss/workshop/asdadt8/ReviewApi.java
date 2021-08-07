package iss.workshop.asdadt8;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Body;

import java.time.LocalDate;
import java.util.List;

public interface ReviewApi {

    String BASE_URL = "http://10.0.2.2:8080/api/review/";
    @GET("list")
    Call<List<ReviewDTO>> getAllReviews();

    @GET("company/review/{companyname}")
    Call<List<ReviewDTO>> getReviewsByCompanyName(@Path("companyname") String companyname);

    @POST("newreview")
    Call<ReviewDTO> createReview(@Body ReviewDTO rdto);

    @DELETE("deletereview/{reviewid}")
    Call<Void> deleteReview(@Path("reviewid") Long reviewid);

    @GET("user/review/{userid}")
    Call<List<ReviewDTO>> getReviewsByUser(@Path("userid") Long userid);

    @GET("job/review/{jobtitle}")
    Call<List<ReviewDTO>> getReviewsByJob(@Path("jobtitle") String jobTitle);

    @GET("job/company/{jobtitle}/{companyname}")
    Call<List<ReviewDTO>> getReviewsByJobandCompany(@Path("jobtitle") String jobTitle,@Path("companyname") String companyname);

    @GET("review/list")
    Call<List<CompaniesReviewDTO>> getAll();

}
