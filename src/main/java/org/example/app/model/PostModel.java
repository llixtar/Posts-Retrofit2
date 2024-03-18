package org.example.app.model;

import org.example.app.entity.Post;
import org.example.app.network.ApiClient;
import org.example.app.network.ApiService;
import retrofit2.Call;

import java.util.List;
import java.util.Optional;

public class PostModel {

    // REST /posts
    public Optional<List<Post>> fetchUsers() {
        ApiClient client = new ApiClient();
        ApiService service = client.getApiService();
        Call<List<Post>> call = service.getPosts();

        Optional<List<Post>> optional;

        try {
            optional = Optional.ofNullable(call.execute().body());
        } catch (Exception ex) {

            optional = Optional.empty();
        }

        return optional;
    }

    // REST /posts/{id}
    public Optional<Post> fetchUserById(int id) {
        ApiClient client = new ApiClient();
        ApiService service = client.getApiService();
        Call<Post> call = service.getPostById(id);
        Optional<Post> optional;

        try {
            optional = Optional.ofNullable(call.execute().body());
        } catch (Exception ex) {
            optional = Optional.empty();
        }

        return optional;
    }
}
