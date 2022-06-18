package com.shakuur.fakeposts;

import org.w3c.dom.Comment;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
//    @GET("posts/2/comments")
//    Call<List<Comments>> getComments();

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);

        @GET("posts")
    Call<List<Posts>> gePosts(@Query("userId") int userId);
}
