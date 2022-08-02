package models.requestmodels.posts;

import lombok.Data;

@Data
public class CreatePost {

    private String userId;
    private String id;
    private String title;
    private String body;

}
