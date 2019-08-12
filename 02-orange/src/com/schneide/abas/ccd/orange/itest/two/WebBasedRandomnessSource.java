package com.schneide.abas.ccd.orange.itest.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.schneide.abas.ccd.orange.itest.two.WebBasedClickMeGame.RandomnessSource;

public class WebBasedRandomnessSource implements RandomnessSource {

	private final String url;

	public WebBasedRandomnessSource(String url) {
		super();
		this.url = url;
	}

	@Override
	public int randomInteger(int threshold) {
		try {
			return queryMicroservice(threshold);
		} catch (IOException e) {
			return 0;
		}
	}

	private int queryMicroservice(int threshold) throws IOException {
		URL url = new URL(this.url + "/" + threshold);
		System.out.println("----> REQUEST: " + url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		int status = connection.getResponseCode();
		System.out.println("<---- RESPONSE: " + status);
		if (200 != status) {
			throw new IOException("Request failed");
		}
		StringBuffer content = new StringBuffer();
		try (
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader in = new BufferedReader(reader);
		) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
		}
		connection.disconnect();
		return Integer.parseInt(content.toString());
	}
}
