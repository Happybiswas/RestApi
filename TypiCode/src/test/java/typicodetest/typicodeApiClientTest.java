package typicodetest;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import typicode.PostAPIClient;
import typicode.TypiCodeAPIClient;

public class typicodeApiClientTest {

    private TypiCodeAPIClient typiCodeAPIClient;
    private PostAPIClient postAPIClient;
    @BeforeClass
    public void setUpTypiCodeAPI() {
        this.postAPIClient = new PostAPIClient();
    }

    @Test
    public void testgetStatusOfTwitteID(){
        ValidatableResponse response= this.postAPIClient.createPost("this is my first post");
        // response.statusCode(200);
//        String actualTweet= deleteResponse.extract().body().path("text");
//        Assert.assertEquals(tweet,actualTweet);
        System.out.println(response.extract().body().asPrettyString());
    }


}
