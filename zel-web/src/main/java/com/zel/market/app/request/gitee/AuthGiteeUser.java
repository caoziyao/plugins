package com.zel.market.app.request.gitee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
@NoArgsConstructor
@Data
public class AuthGiteeUser {

    /**
     * id : 8877565
     * login : snacsy
     * name : snacsy
     * avatar_url : https://gitee.com/assets/no_portrait.png
     * url : https://gitee.com/api/v5/users/snacsy
     * html_url : https://gitee.com/snacsy
     * followers_url : https://gitee.com/api/v5/users/snacsy/followers
     * following_url : https://gitee.com/api/v5/users/snacsy/following_url{/other_user}
     * gists_url : https://gitee.com/api/v5/users/snacsy/gists{/gist_id}
     * starred_url : https://gitee.com/api/v5/users/snacsy/starred{/owner}{/repo}
     * subscriptions_url : https://gitee.com/api/v5/users/snacsy/subscriptions
     * organizations_url : https://gitee.com/api/v5/users/snacsy/orgs
     * repos_url : https://gitee.com/api/v5/users/snacsy/repos
     * events_url : https://gitee.com/api/v5/users/snacsy/events{/privacy}
     * received_events_url : https://gitee.com/api/v5/users/snacsy/received_events
     * type : User
     * blog : null
     * weibo : null
     * bio :
     * public_repos : 0
     * public_gists : 0
     * followers : 0
     * following : 0
     * stared : 0
     * watched : 0
     * created_at : 2021-03-27T00:44:42+08:00
     * updated_at : 2021-03-27T05:04:35+08:00
     * email : null
     */

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("name")
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("url")
    private String url;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("followers_url")
    private String followersUrl;
    @JsonProperty("following_url")
    private String followingUrl;
    @JsonProperty("gists_url")
    private String gistsUrl;
    @JsonProperty("starred_url")
    private String starredUrl;
    @JsonProperty("subscriptions_url")
    private String subscriptionsUrl;
    @JsonProperty("organizations_url")
    private String organizationsUrl;
    @JsonProperty("repos_url")
    private String reposUrl;
    @JsonProperty("events_url")
    private String eventsUrl;
    @JsonProperty("received_events_url")
    private String receivedEventsUrl;
    @JsonProperty("type")
    private String type;
    @JsonProperty("blog")
    private String blog;
    @JsonProperty("weibo")
    private Object weibo;
    @JsonProperty("bio")
    private String bio;
    @JsonProperty("public_repos")
    private Integer publicRepos;
    @JsonProperty("public_gists")
    private Integer publicGists;
    @JsonProperty("followers")
    private Integer followers;
    @JsonProperty("following")
    private Integer following;
    @JsonProperty("stared")
    private Integer stared;
    @JsonProperty("watched")
    private Integer watched;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("email")
    private String email;
}
