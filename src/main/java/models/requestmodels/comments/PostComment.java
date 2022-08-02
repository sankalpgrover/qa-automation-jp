package models.requestmodels.comments;

import lombok.Data;

@Data
public class PostComment {

    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;

    @Override
    public String toString() {
        return "{" +
                "postId='" + postId + "\'" +
                ", id='" + id + "\'" +
                ", name='" + name + "\'" +
                ", email='" + email + "\'" +
                ", body='" + body + "\'" +
                '}';
    }
}
