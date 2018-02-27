package demo.soap.sample.endpoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.soap.sample.domain.GetAllGreetedUsersRequest;
import demo.soap.sample.domain.GetAllGreetedUsersResponse;
import demo.soap.sample.domain.GetGreetingRequest;
import demo.soap.sample.domain.GetGreetingResponse;
import demo.soap.sample.domain.PostGreetingRequest;
import demo.soap.sample.domain.PostGreetingResponse;

@Endpoint
public class GreetingEndpoint {
	private static final String NAMESPACE_URI = "http://demo/soap/sample/domain";

	private List<String> USERS = new ArrayList<>();

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGreetingRequest")
	@ResponsePayload
	public GetGreetingResponse getGreeting(@RequestPayload GetGreetingRequest request) {
		GetGreetingResponse response = new GetGreetingResponse();
		response.setMessage("Greetings from SOAP World!");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "postGreetingRequest")
	@ResponsePayload
	public PostGreetingResponse postGreeting(@RequestPayload PostGreetingRequest request) {
		PostGreetingResponse response = new PostGreetingResponse();
		if (request.getUser() != null && !request.getUser().isEmpty()) {
			if (!USERS.contains(request.getUser().toUpperCase()))
				USERS.add(request.getUser().toUpperCase());
			response.setMessage("Greetings " + request.getUser());
		} else {
			response.setMessage("Mandatory User Field Required");
		}
		response.setTime(Calendar.getInstance());
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllGreetedUsersRequest")
	@ResponsePayload
	public GetAllGreetedUsersResponse getAllGreetedUsers(@RequestPayload GetAllGreetedUsersRequest request) {
		GetAllGreetedUsersResponse response = new GetAllGreetedUsersResponse();
		response.setUsers(USERS.toString());
		return response;
	}
}
