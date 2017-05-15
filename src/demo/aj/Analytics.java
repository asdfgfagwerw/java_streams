package demo.aj;

import java.util.Arrays;
import java.util.List;

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
			if(user.active) {
				//doSomething with active users
			} else {
				//doSomething with inactive users
			}
		}

	}

}
