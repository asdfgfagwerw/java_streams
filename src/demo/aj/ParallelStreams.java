package demo.aj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.*;

public class ParallelStreams {
	
	public static void main(String[] args) {
		List<User> users = getUsers();
		long startTime = System.nanoTime();
		for(User user : users) {
			String repos = apiRequest(user.getReposUrl());
			System.out.println(getFirstRepoName(repos));
		}
		long endTime = System.nanoTime();
		System.out.println("Total time imperative : " + (endTime - startTime) / 1e9);
		
		startTime = System.nanoTime();
		
		users.stream()
			.parallel()
			.map(user -> apiRequest(user.getReposUrl()))
			.forEach(repoName -> System.out.println(getFirstRepoName(repoName)));
		
		endTime = System.nanoTime();
		System.out.println("Total time functional : " + (endTime - startTime) / 1e9);
	}
	
	private static String getFirstRepoName(String reposJson) {
		List<Map<String, String>> reposMap = new Gson().fromJson(reposJson, List.class);
		return reposMap.get(0).get("name");
		
	}

	private static List<User> getUsers() {
		String inputLine = apiRequest("https://api.github.com/users");

		List<Map<String, String>> usersMap = new Gson().fromJson(inputLine, List.class);
		List<User> usersList = usersMap.stream()
				.map(user -> new User(user.get("repos_url")))
				.collect(Collectors.toList());
		return usersList.subList(0, 10);

	}

	private static String apiRequest(String requestApi) {
		URL url;
		try {
			url = new URL(requestApi);
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine = in.readLine();
			in.close();
			return inputLine;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

class User {
	
	private String reposUrl;
	
	public User(String reposUrl) {
		this.reposUrl = reposUrl;
	}
	
	public String getReposUrl() {
		return reposUrl;
	}
}
