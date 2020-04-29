package ru.spring.marker.rest;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import ru.spring.marker.rest.model.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@SpringBootTest
class ApplicationTests {



	private String url = "http://localhost:8080/message/";

	private String bodyXML = "<User>\n" +
			"\t<name>Maxim</name>\n" +
			"\t<surname>Evlentev</surname>\n" +
			"\t<birthday>01.06.1999</birthday>\n" +
			"</User>\n";

	private String bodyJSON = "{\n" +
			"\t\"name\":\"Maxim\",\n" +
			"\t\"surname\": \"Evlentev\",\n" +
			"\t\"birthday\": \"01.06.1999\"\n" +
			"}";




	private void getResponse(HttpURLConnection con) throws Exception {
		int responseCode = con.getResponseCode();

		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);



		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(response.toString());
	}

	@Test
	public void sendGet() throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//header
		con.setRequestMethod("GET");
		con.setDoOutput(true);

		getResponse(con);
		con.disconnect();

	}



	@Test
	public void sendPost() throws Exception {


		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);

		//header
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");



		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(bodyJSON);
		wr.flush();
		wr.close();

		getResponse(con);

	}

	@Test
	public void sendPut() throws Exception {


		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);

		//header
		con.setRequestMethod("PUT");

		con.setRequestProperty("Content-Type", "application/xml");



		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(bodyXML);
		wr.flush();
		wr.close();

		getResponse(con);

	}

	@Test
	public void sendDelete() throws Exception {


		URL obj = new URL(url + "/34" );
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);

		//header
		con.setRequestMethod("DELETE");

		getResponse(con);

	}







}
