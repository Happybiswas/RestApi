package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;
    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

@Test
     public void testPostStatus1(){
        String tweet= "This is my very beginning tweet  "+UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
         System.out.println(response.extract().body().asPrettyString());
         response.statusCode(200);
         String actualTweet = response.extract().body().path("text");
         Assert.assertEquals(actualTweet,tweet,"Tweet does not match");

     }
@Test
     public  void testdeleteTweet1(){
        String tweet = "This is my very beginning tweet  75190270-5cbd-4208-996c-623a87f8c3b5";
         ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1406789611087708161l);
         System.out.println(deleteResponse.extract().body().asPrettyString());
         deleteResponse.statusCode(200);
         String actualTweet= deleteResponse.extract().body().path("text");
         Assert.assertEquals(deleteResponse,actualTweet);
         //String actualTweet= deleteResponse.extract().body().path("text");
         //Assert.assertEquals(tweet,actualTweet);

     }

    @Test
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet"+ UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet=response.extract().body().path("text");
        // Long id= response.extract().body().path("id");
        //System.out.println(id);
        Assert.assertEquals(actualTweet,tweet,"Tweet is not match");
    }

    @Test
    public void testqueryResults(){
        String Query ="query=cat%20has%3Amedia%20-grumpy&tweet.fields=created_at&max_results=100 -H";
        ValidatableResponse response = this.tweetAPIClient.getQueryResults(Query);
        response.statusCode(200);
        String actualQueryResult = response.extract().body().path("text");
        Assert.assertEquals(actualQueryResult,Query,"Query does not match");
    }
    @Test
    public void testgetMusicCollection(){
        String Query ="screen_name=twittermusic&count=1";
        ValidatableResponse response = this.tweetAPIClient.getQueryResults(Query);
        response.statusCode(200);
        String actualQueryResult = response.extract().body().path("text");
        Assert.assertEquals(actualQueryResult,Query,"Query does not match");
    }
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "We are learning Rest API using Rest Assured and our First Tweet Same Tweet1";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage="Status is a duplicate.";
        String actualTweet=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet,expectedMessage,"Tweet is match");
        Assert.assertNotEquals("403",200);
    }
    @Test
    public void testDeleteTweet(){
        String tweet="We are learning Rest API using Rest Assured and our First Tweet11fed9e3-8adb-4cb6-8364-322029bd4621";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet(1406778063074037760L);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = false)
    public void testResponseTime() {
        ValidatableResponse response = this.tweetAPIClient.responseTime();
    }
    @Test(enabled = false)
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }

    @Test
    public void testPropertyFromResponse() {
        //1. User send a tweet
        String tweet = "We are learning Rest API Automation with WeekdaysEvening_Selenium_NY_Summer2020" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString().contains("id"));
        //String actualTweet = response.extract().body().path("text");
        //Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }
    @Test
    public void testcollectionShow(){
      //  String Text="Tweets from the music Superstars trending now";
        ValidatableResponse response = this.tweetAPIClient.collectionShow("custom-393773266801659904");
       // response.statusCode(200);
      //  String actualTweet= response.extract().body().asString();
     // Assert.assertEquals(actualTweet,Text);
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testPOSTCollectionsCreate(){
        //  String Text="Tweets from the music Superstars trending now";
        ValidatableResponse response = this.tweetAPIClient.POSTCollectionsCreate("Sweet%20Tweets");
        // response.statusCode(200);
        //  String actualTweet= response.extract().body().asString();
        // Assert.assertEquals(actualTweet,Text);
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testPOSTCollectionsDestroy(){
        //  String Text="true";
        ValidatableResponse response = this.tweetAPIClient.POSTCollectionsDestroy("custom-393773266801659904");
         //response.statusCode(200);
         // String actualTweet= response.extract().body().asPrettyString();
        // Assert.assertEquals(actualTweet,Text);
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testPOSTCollectionsEntriesAdd(){
        //  String Text="true";
        ValidatableResponse response = this.tweetAPIClient.POSTCollectionsEntriesAdd("390890231215292416 &","custom-388061495298244609");
        //response.statusCode(200);
        // String actualTweet= response.extract().body().asPrettyString();
        // Assert.assertEquals(actualTweet,Text);
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testPOSTCollectionsUpdate(){
        //  String Text="true";
        ValidatableResponse response = this.tweetAPIClient.POSTCollectionsUpdate("Subtweets","custom-1379914544831680516");
        response.statusCode(200);
        // String actualTweet= response.extract().body().asPrettyString();
        // Assert.assertEquals(actualTweet,Text);
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testtweetSendInMytweetAccount() {
        //  sent a tweet in my tweet Account
        String tweet = "I got my COVID-19 vaccine "+ UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.tweetSendInMytweetAccount(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(actualTweet,tweet,"Tweet is not match");
    }
    @Test
    public void testDeleteTweet1(){
        String tweet="I got my COVID-19 vaccine 7c26eb95-1b89-4c34-9f9a-1ea2150ced4e";
        ValidatableResponse deleteResponse= this.tweetAPIClient.deleteTweet1(1401340560644919301l);
        deleteResponse.statusCode(200);
        String actualTweet= deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test
    public void testgetStatusOfTwitteID(){
        ValidatableResponse response= this.tweetAPIClient.getStatusOfTwitteID(210462857140252672l);
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testgetFavoriteList(){
        //String tweet = "We're so happy for our Official Partner @Brandwatch and their big news";
        ValidatableResponse response= this.tweetAPIClient.getFavoriteList("twitterdev");
        response.statusCode(200);
       // String actualTweet= response.extract().body().path("text");
       // Assert.assertEquals(tweet,actualTweet);
        System.out.println(response.extract().body().asPrettyString());
    }
    // post favorite from my tweet account id
    @Test
    public void testPOSTfavoriteCreate(){
        ValidatableResponse response= this.tweetAPIClient.POSTFavoriteCreate("1379588274046504962");
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testGetStatusLookup(){
        ValidatableResponse response= this.tweetAPIClient.GetStatusLookup(1379923925421940749l);
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testGetListLists(){
        ValidatableResponse response= this.tweetAPIClient.GetListLists("HappyBiswas19");
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testGetListShow(){
        ValidatableResponse response= this.tweetAPIClient.GetListShow("1380678075982168066","team");
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testGetListSubscirbers(){
        ValidatableResponse response= this.tweetAPIClient.GetListSubscribers("1380678075982168066","team");
        System.out.println(response.extract().body().asPrettyString());
    }
    //
    @Test
    public void testPostListCreate(){
        ValidatableResponse response= this.tweetAPIClient.PostListCreate("goonies");
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testGetFlowerIds(){
        ValidatableResponse response= this.tweetAPIClient.GetF0llowerIds("HappyBiswas19");
        System.out.println(response.extract().body().asPrettyString());
    }
    //
    @Test
    public void testGetUserSearch(){
        ValidatableResponse response= this.tweetAPIClient.GetUserSearch("Twitter%20API");
        System.out.println(response.extract().body().asPrettyString());
    }
    //
    @Test
    public void testgetUserShow(){
        ValidatableResponse response= this.tweetAPIClient.getUserShow("HappyBiswas19","twitterdev");
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testgetFolloerList(){
        ValidatableResponse response= this.tweetAPIClient.getFollowerList("1376002632444211200","twitterdev");
        System.out.println(response.extract().body().asPrettyString());
    }
    @Test
    public void testgetFriendList(){
        ValidatableResponse response= this.tweetAPIClient.getFriendList("1376002632444211200","twitterapi");
        System.out.println(response.extract().body().asPrettyString());
    }



}
