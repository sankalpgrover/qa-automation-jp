package utils;

import lombok.Data;
import lombok.Getter;

/**
 * The type End points.
 */

@Data
@Getter
public class EndPoints {

    private String usersEndpoint = "/users";
    private String usersEndpointWithPathParam = "/users/{userId}";
    private String commentsEndpoint = "/comments";
    private String postsEndpoint = "/posts";
    private String postsEndpointWithPathParam = "/posts/{postId}";

}
