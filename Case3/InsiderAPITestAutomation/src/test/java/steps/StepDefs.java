package steps;

import helpers.HttpHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import java.io.File;


public class StepDefs {
    String basePath = "/pet";
    Response response;

    @Given("send post request for upload an image to pet")
    public void sendPostRequestForUploadAnImageToPet() {
        String petId = "1500";
        String path = basePath + "/" + petId + "/uploadImage";

        File imageData = new File("src/main/java/files/images/cat.png");

        response = HttpHelper.postRequestWithMultipart(path, imageData);
    }

    @Then("assert status code for success")
    public void assertStatusCodeForSuccess() {
        Assert.assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @And("assert response for upload an image to pet")
    public void assertResponseForUploadAnImageToPet() {
        Assert.assertTrue(response.asPrettyString().contains("File uploaded to ./cat.png"));
    }

    @Given("send post request for upload an image to pet without image")
    public void sendPostRequestForUploadAnImageToPetWithoutImage() {
        String petId = "1500";
        String path = basePath + "/" + petId + "/uploadImage";

        response = HttpHelper.postRequestWithoutBody(path);
    }

    @Then("assert status code for unsupported media type")
    public void assertStatusCodeForUnsupportedMediaType() {
        Assert.assertEquals(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, response.statusCode());
    }

    @Given("send post request for upload an image to not created id")
    public void sendPostRequestForUploadAnImageToNotCreatedId() {
        String petId = "4011000";
        String path = basePath + "/" + petId + "/uploadImage";

        File imageData = new File("src/main/java/files/images/cat.png");

        response = HttpHelper.postRequestWithMultipart(path, imageData);
    }

    @Given("send post request for adding new pet")
    public void sendPostRequestForAddingNewPet() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPet.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @Then("assert status code for created")
    public void assertStatusCodeForCreated() {
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
    }

    @And("assert response for adding new pet")
    public void assertResponseForAddingNewPet() {
        String id = response.path("id").toString();
        String name = response.path("name").toString();
        String status = response.path("status").toString();

        Assert.assertEquals(id, "999125");
        Assert.assertEquals(name, "spaniel");
        Assert.assertEquals(status, "available");
    }

    @Given("send post request for adding new pet with wrong id value")
    public void sendPostRequestForAddingNewPetWithWrongIdValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPetWithWrongIdValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @Then("assert status code for server error")
    public void assertStatusCodeForServerError() {
        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.statusCode());
    }

    @Given("send post request for adding new pet with wrong name value")
    public void sendPostRequestForAddingNewPetWithWrongNameValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPetWithWrongNameValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @Given("send post request for adding new pet with wrong status value")
    public void sendPostRequestForAddingNewPetWithWrongStatusValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPetWithWrongNameValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @Given("send post request for adding new pet with non-alphanumeric chars")
    public void sendPostRequestForAddingNewPetWithNonAlphanumericChars() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPetWithNonAlphaNumericChars.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @Then("assert status code for bad request")
    public void assertStatusCodeForBadRequest() {
        Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }

    @Given("send put request to existing pet")
    public void sendPutRequestToExistingPet() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPet.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @And("assert response for update an existing pet")
    public void assertResponseForUpdateAnExistingPet() {
        String id = response.path("id").toString();
        String name = response.path("name").toString();
        String status = response.path("status").toString();

        Assert.assertEquals(id, "5");
        Assert.assertEquals(name, "ronnie");
        Assert.assertEquals(status, "pending");
    }

    @Given("send put request to existing pet with wrong id value")
    public void sendPutRequestToExistingPetWithWrongIdValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPetWithWrongIdValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @Given("send put request to existing pet with wrong name value")
    public void sendPutRequestToExistingPetWithWrongNameValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPetWithWrongNameValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @Given("send put request to existing pet with wrong status value")
    public void sendPutRequestToExistingPetWithWrongStatusValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPetWithWrongStatusValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @Given("send put request to existing pet with non-alphanumeric chars")
    public void sendPutRequestToExistingPetWithNonAlphanumericChars() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPetWithWrongNameValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
    }

    @Given("send get request with status available")
    public void sendGetRequestWithStatusAvailable() {
        String path = basePath + "/findByStatus";

        response = HttpHelper.getRequestWithQueryParam(path, "status", "available");
    }

    @And("assert response for find pet by status available")
    public void assertResponseForFindPetByStatusAvailable() {
        Assert.assertFalse(response.asPrettyString().contains("pending") || response.asPrettyString().contains("sold"));
        Assert.assertTrue(response.asPrettyString().contains("available"));
    }

    @Given("send get request with status pending")
    public void sendGetRequestWithStatusPending() {
        String path = basePath + "/findByStatus";

        response = HttpHelper.getRequestWithQueryParam(path, "status", "pending");
    }

    @And("assert response for find pet by status pending")
    public void assertResponseForFindPetByStatusPending() {
        Assert.assertFalse(response.asPrettyString().contains("available") || response.asPrettyString().contains("sold"));
        Assert.assertTrue(response.asPrettyString().contains("pending"));
    }

    @Given("send get request with status sold")
    public void sendGetRequestWithStatusSold() {
        String path = basePath + "/findByStatus";

        response = HttpHelper.getRequestWithQueryParam(path, "status", "sold");
    }

    @And("assert response for find pet by status sold")
    public void assertResponseForFindPetByStatusSold() {
        Assert.assertFalse(response.asPrettyString().contains("available") || response.asPrettyString().contains("pending"));
        Assert.assertTrue(response.asPrettyString().contains("sold"));
    }

    @Given("send get request with wrong status")
    public void sendGetRequestWithWrongStatus() {
        String path = basePath + "/findByStatus";

        response = HttpHelper.getRequestWithQueryParam(path, "status", "test100!");
    }

    @Given("send get request with petId")
    public void sendGetRequestWithPetId() {
        String petId = "1231223432131";
        String path = basePath + "/" + petId;

        response = HttpHelper.getRequest(path);
    }

    @And("assert response for find pet by Id")
    public void assertResponseForFindPetById() {
        String id = response.path("id").toString();

        Assert.assertEquals(id, "1231223432131");
    }

    @Given("send post request with form data")
    public void sendPostRequestWithFormData() {
        String petId = "5";
        String path = basePath + "/" + petId;

        response = HttpHelper.postRequestWithFormParams(path, "name", "jonie", "status", "sold");
    }

    @Given("send post request with form data wrong name & status")
    public void sendPostRequestWithFormDataWrongNameStatus() {
        String petId = "5";
        String path = basePath + "/" + petId;

        response = HttpHelper.postRequestWithFormParams(path, "name", "1000", "status", "1000");
    }

    @Given("send delete request with petId")
    public void sendDeleteRequestWithPetId() {
        String petId = "999125";
        String path = basePath + "/" + petId;

        response = HttpHelper.deleteRequest(path);
    }

    @And("assert response for delete a pet")
    public void assertResponseForDeleteAPet() {
        String message = response.path("message").toString();

        Assert.assertEquals(message, "999125");
    }

}
