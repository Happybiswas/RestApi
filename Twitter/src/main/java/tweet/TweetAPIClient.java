package tweet;

import base.RestAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT="/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT="/statuses/home_timeline.json";// GET all Tweet Information
    private final String GET_USER_QUERY_ENDPOINT="/2/tweets/search/recent";
    private final String GET_TWEETER_MUSI_ENDPOINT="/collections/list.json";
    private final String GET_COLLECTION_SHOW_ENDPOINT="/collections/show.json";
    private final String POST_COLLECTION_CREATE ="/collections/create.json";
    private final String POST_COLLECTION_DESTROY ="/collections/destroy.json";
    private final String POST_COLLECTIONS_ENTRIES_ADD="/collections/entries/add.json";
    private final String POST_COLLECTIONS_UPDATE="/collections/update.json";
    private final String GET_STATUSES_SHOWID="/statuses/show.json";
    private final String GET_FAVORITE_LIST="/favorites/list.json";
    private final String POST_FAVORITE_CREATE="/favorites/create.json";
    private final String GET_STATUS_LOOKUP="statuses/lookup.json";
    private final String GET_LIST_LISTS="statuses/lookup.json";
    private final String GET_LIST_SHOW= "/lists/show.json";
    private final String GET_LIST_SUBSCRIBERS= "/lists/subscribers.json";
    private final String POST_LIST_CREATE= "lists/create.json";
    private final String GET_FOLLOWERS_IDS= "followers/ids.json";
    private final String GET_USER_SEARCH= "users/search.json";
    private final String GET_USER_SHOW= "/users/show.json";
    private final String GET_FOLLOWER_LIST= "/followers/list.json";

    private final String GET_FRIEND_LIST= "/friends/list.json";

    private final String Post_Statuds_Update = "/statuses/update.json";
    private final String Delete_post_Status = "/statuses/destroy.json";


    public ValidatableResponse PostStatus1(String tweet) {
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUrl+this.Post_Statuds_Update)
                .then().statusCode(200);

    }

    public ValidatableResponse deleteTweet1(String tweet){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .param("id",tweet)
                .when().delete(this.baseUrl+this.Delete_post_Status)
                .then().statusCode(200);
    }




    public ValidatableResponse createTweet(String tweet){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }
    public ValidatableResponse getQueryResults(String query){

        return given().auth().oauth2(this.bearerToken)
                .param("status",query)
                .when().get(this.baseUrl + this.GET_USER_QUERY_ENDPOINT)
                .then();
    }
    public ValidatableResponse deleteTweet(Long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().delete(this.baseUrl+this.DELETE_TWEET_ENDPOINT).then().statusCode(200);
    }

    public ValidatableResponse responseTime(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .timeIn(TimeUnit.MILLISECONDS));
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT)
                .then();
    }

    public void headerValue(){
        System.out.println(given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT).then().extract().headers());

    }
    public  void checkProperty(){
        Response response= given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT);
        JsonPath pathEvaluator= response.jsonPath();
        String createdAt=pathEvaluator.get("id");
        System.out.println(createdAt);
    }
    public ValidatableResponse getUserTimeTweet(){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUrl+this.GET_USER_TWEET_ENDPOINT).then().statusCode(200);
    }

    public ValidatableResponse getMusicEndpoint(String query){

        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",query)
                .when().post(this.baseUrl+this.GET_TWEETER_MUSI_ENDPOINT)
                .then();
    }
    public ValidatableResponse collectionShow(String customID){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",customID)
                .when().get(this.baseUrl+this.GET_COLLECTION_SHOW_ENDPOINT).then().statusCode(200);
    }

    public ValidatableResponse POSTCollectionsCreate(String name) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("name",name)
                .when().post(this.baseUrl + this.POST_COLLECTION_CREATE).then().statusCode(200);
    }

    public ValidatableResponse POSTCollectionsDestroy(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                // .queryParam("Name", name)
                .queryParam("id",id)
                .when().post(this.baseUrl + this.POST_COLLECTION_DESTROY).then().statusCode(200);
    }

    public ValidatableResponse POSTCollectionsEntriesAdd(String tweetid, String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)

                .queryParam("tweet_id",tweetid)
                .queryParam("id",id)
                .when().post(this.baseUrl + this.POST_COLLECTIONS_ENTRIES_ADD).then().statusCode(200);
    }
    public ValidatableResponse POSTCollectionsUpdate( String name,String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("name",name)
                .queryParam("id",id)
                .when().post(this.baseUrl + this.POST_COLLECTIONS_UPDATE).then().statusCode(200);
    }
    //tweet send
    public ValidatableResponse tweetSendInMytweetAccount(String tweet){
        return given().auth().oauth(this.apiKey,this.apiSecretKey, this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }
    //delete tweet
    public ValidatableResponse deleteTweet1(long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().delete(this.baseUrl+this.DELETE_TWEET_ENDPOINT).then().statusCode(200);
    }
    public ValidatableResponse getStatusOfTwitteID(long id){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",id)
                .when().get(this.baseUrl+this.GET_STATUSES_SHOWID).then().statusCode(200);
    }
    public ValidatableResponse getFavoriteList(String name){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("screen_name",name)
                .when().get(this.baseUrl+this.GET_FAVORITE_LIST).then().statusCode(200);
    }
    public ValidatableResponse POSTFavoriteCreate(String id){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",id)
                .when().post(this.baseUrl+this.POST_FAVORITE_CREATE).then().statusCode(200);
    }
    public ValidatableResponse GetStatusLookup(long id){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",id)
                .when().post(this.baseUrl+this.GET_STATUS_LOOKUP).then().statusCode(200);
    }
    public ValidatableResponse GetListLists(String id){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("user_id",id)
                .when().get(this.baseUrl+this.GET_LIST_LISTS).then().statusCode(200);
    }

    public ValidatableResponse GetListShow(String id, String slug){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("list_id",id)
                .queryParam("slug", slug)
                .when().get(this.baseUrl+this.GET_LIST_SHOW).then().statusCode(200);
    }

    public ValidatableResponse GetListSubscribers(String id, String slug){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("list_id",id)
                .queryParam("slug", slug)
                .when().get(this.baseUrl+this.GET_LIST_SUBSCRIBERS).then().statusCode(200);
    }
    public ValidatableResponse PostListCreate( String listName ){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("name",listName)
                .when().post(this.baseUrl+this.POST_LIST_CREATE).then().statusCode(200);
    }
    public ValidatableResponse GetF0llowerIds( String name ){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("screen_name",name)
                .when().get(this.baseUrl+this.GET_FOLLOWERS_IDS).then().statusCode(200);
    }
    public ValidatableResponse GetUserSearch( String q ){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("q",q)
                .when().get(this.baseUrl+this.GET_USER_SEARCH).then().statusCode(200);
    }
    public ValidatableResponse getUserShow( String id ,String name ){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("user_id",id)
                .queryParam("screen_name",name)
                .when().get(this.baseUrl+this.GET_USER_SHOW).then().statusCode(200);
    }

    public ValidatableResponse getFollowerList( String id ,String name ){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("user_id",id)
                .queryParam("screen_name",name)
                .when().get(this.baseUrl+this.GET_FOLLOWER_LIST).then().statusCode(200);
    }

    public ValidatableResponse getFriendList( String id ,String name ){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("user_id",id)
                .queryParam("screen_name",name)
                .when().get(this.baseUrl+this.GET_FOLLOWER_LIST).then().statusCode(200);
    }


}
