package stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefination m = new StepDefination();
        //if(StepDefination.strplace_id==null)

        m.addPlacePayloadWith("namo home", "English", "mukai nagar");
        m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
        m.verifyPlace_IdCreatedMapsToUsing("namo home", "getPlaceAPI");



    }

}
