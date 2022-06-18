package com.shakuur.fakeposts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tvBody ;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBody = findViewById(R.id.text_view_result);
        progressBar = findViewById(R.id.progressbar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

     //   fetchComments();
        fetchPosts();
    }

    private void fetchComments() {
          progressBar.setVisibility(View.VISIBLE);
        Call<List<Comments>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if(!response.isSuccessful()){
                    tvBody.setText("code +" + response.code());
                    return;
                }

                List<Comments> commentList = response.body();
                progressBar.setVisibility(View.GONE);
                for (Comments comments : commentList){
                   String content = "";
                    content += "ID: " + " " +  comments.getId() + "\n";
                    content += "Post ID: " + " " +comments.getPostId() + "\n";
                    content += "Name " + " " + comments.getName() + "\n";
                    content += "Email: " + " " +comments.getEmail() + "\n";
                    content += "Text: " + " " + comments.getText() + "\n\n";

                    tvBody.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

            }
        });
    }


    private void fetchPosts(){
        progressBar.setVisibility(View.VISIBLE);

       Call<List<Posts>> call = jsonPlaceHolderApi.gePosts(2);

       call.enqueue(new Callback<List<Posts>>() {
           @Override
           public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
               if(!response.isSuccessful()){
                   tvBody.setText("code +" + response.code());
                   return;
               }

               List<Posts> postsList = response.body();
               progressBar.setVisibility(View.GONE);
               for(Posts posts : postsList){
                   String content = "";
                   content += "userId " +  posts.getUserId() + "\n";
                   content += "id " + posts.getId() + "\n";
                   content += "title " +posts.getTitle() + "\n";
                   content += "body " + posts.getBody() ;
                   tvBody.append(content);

               }

           }

           @Override
           public void onFailure(Call<List<Posts>> call, Throwable t) {

           }
       });
    }
}