package demo.aj;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Analytics {
	
	static class User {
		String name;
		boolean active;
		
		public User(String name, boolean active) {
			this.name = name;
			this.active = active;
		}
	}

	public static void main(String[] args) {
		List<User> users = Arrays.asList(new User("A", true), new User("B", false), new User("C", true), new User("D", false), new User("E", true), new User("F", false), new User("G", true), new User("H", false));
		
		for(User user : users) {
			doSomethingWithUser(user);
		}
		
		//Let's do the same with streams
		users.stream()
			.filter(user -> user.active)
			.forEach(user -> System.out.println("User is active"));
		
		users.stream()
		.filter(user -> !user.active)
		.forEach(Analytics::displayInactive);
		
		//Let's get rid of code duplication
		//The different parts are the filter parameter (a Predicate) and the forEach parameter (a Consumer)
		processUsers(users, user -> user.active, user -> System.out.println("User is active"));
		processUsers(users, user -> !user.active, Analytics::displayInactive);
	}
	
	/**
	 * This is a higer order function. It takes two methods as parameters.
	 * @param users List of users to be processed
	 * @param predicate A conditional to filter users. Takes a user as input and returns a boolean.
	 * @param consumer Consumes a user object. Takes a user as input and returns nothing (void).
	 */
	private static void processUsers(List<User> users, Predicate<User> predicate, Consumer<User> consumer) {
		users.stream()
		.filter(predicate)
		.forEach(consumer);
	}

	private static void doSomethingWithUser(User user) {
		if(user.active) {
			System.out.println("User is active");
		} else {
			displayInactive(user);
		}
	}

	private static void displayInactive(User user) {
		System.out.println("User is inactive");
	}

}
