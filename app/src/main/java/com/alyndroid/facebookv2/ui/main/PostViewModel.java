package com.alyndroid.facebookv2.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alyndroid.facebookv2.R;
import com.alyndroid.facebookv2.data.PostInterface;
import com.alyndroid.facebookv2.databinding.ActivityMainBinding;
import com.alyndroid.facebookv2.databinding.PostItemBinding;
import com.alyndroid.facebookv2.pojo.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostViewModel extends ViewModel {
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private PostInterface postInterface;







    public void getPosts() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);

        Call<List<PostModel>> call = postInterface.getPosts();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                postsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                posts.setValue("errr");
            }
        });







    }
}
