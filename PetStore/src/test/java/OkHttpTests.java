import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttpTests {

    Service service = new Service();


//    @Test
//    public void testGetRequest() throws IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://petstore.swagger.io/v2/pet/1234")
//                .get()
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            int statusCode = response.code();
//
//            // Check the status code
//            if (statusCode == 200) {
//                String responseBody = response.body().string();
//                JSONObject jsonObject = new JSONObject(responseBody);
//                String formattedResponse = jsonObject.toString(2);
//                System.out.println(formattedResponse);
//            } else {
//                System.out.println("Request failed with status code: " + statusCode);
//            }
//        }
//    }


//    @Test
//    public void testGetRequest2() throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        MediaType mediaType = MediaType.parse("application/json");
//
//        // Create the request body
//        JsonNode requestBodies = service.loadRequestBodiesFromFile();
//
//        String requestKey = "addPetSingleTags"; // Key of the desired request body from requests.json file
//        JsonNode requestBody = requestBodies.get(requestKey);
//
//        //Setting randomized values for request
//        int petId = service.randomNumberGenerator();
//        String petType = service.getRandomPetType();
//        String petName = service.getRandomPetName();
//        String petStatus = service.getRandomPeStatus();
//        String photoUrl = service.photoUrl(petType, petId, petName);
//
//        //Assigned values to key, which represents fields in request
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("petId", petId);
//        variables.put("petType", petType);
//        variables.put("petName", petName);
//        variables.put("petStatus", petStatus);
//        variables.put("photoUrl", photoUrl);
//
//        //Method that inject variables from HashMap to request
//        String requestBodyWithValues = service.injectVariablesIntoRequestBody(requestBody.toString(), variables);
//
//        // Print the formatted request body
//        System.out.println("Request Body:\n" + service.formatJson(requestBodyWithValues));
//
//        Request request = new Request.Builder()
//                .url(service.baseUri() + "pet")
//                .post(RequestBody.create(mediaType, requestBodyWithValues))
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            int statusCode = response.code();
//            String responseBody = response.body().string();
//
//            // Print the formatted request body
//            System.out.println("Request Body:\n" + service.formatJson(requestBodyWithValues));
//
//            // Check the response content type
//            String contentType = response.header("Content-Type");
//            if (contentType != null && contentType.startsWith("application/json")) {
//                // Print the formatted response body
//                System.out.println("Response Body:\n" + service.formatJson(responseBody));
//
//                // Check the status code
//                assertEquals(200, statusCode);
//            } else {
//                // Handle non-JSON response
//                System.out.println("Non-JSON Response:\n" + responseBody);
//                //              fail("Received a non-JSON response. Check the response content type or handle the error.");
//            }
//        }
//    }

    @Test
    void plainOkHttp3() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(service.baseUri() + "/pet/1234")
                .header("Content-Type", "application/json")
                .build();
        Response response = null;
        String responseInString = null;
        try {
            response = client.newCall(request).execute();
            responseInString = response.body().string();
            Assert.assertEquals(response.code(), 200);
            System.out.println(service.formatJson(responseInString));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

}
