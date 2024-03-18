package org.example.app.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.app.entity.Post;
import org.example.app.model.PostModel;
import org.example.app.utils.AppStarter;
import org.example.app.utils.Constants;
import org.example.app.view.PostByIdView;

import java.util.Optional;

public class PostByIdController {

    PostModel model;
    PostByIdView view;

    public PostByIdController(PostModel model, PostByIdView view) {
        this.model = model;
        this.view = view;
    }

    public void getPostById() {

        try {
            view.getOutput(readPostById(Integer.parseInt(view.getData())));
        } catch (NumberFormatException e) {
            view.getOutput(Constants.NO_DATA_MSG);
        }

        AppStarter.startApp();
    }

    private String readPostById(int id) {
        Optional<Post> optional = model.fetchUserById(id);

        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {
            Gson gson = new Gson();
            Post post = gson.fromJson(String.valueOf(optional.get()),
                    new TypeToken<Post>() {
                    }.getType());
            return "id " + post.getId() + ",\ntitle: " + post.getTitle() +
                    ",\nbody: " + post.getBody();
        }
    }
}
