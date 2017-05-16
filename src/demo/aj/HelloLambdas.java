package demo.aj;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HelloLambdas {

	public static void main(String[] args) {
		//Thread new thread -> Replace Anonymous class
//		Thread thread = new Thread(() -> System.out.println("Inside run"));
//		thread.start();
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		//Double and sum all elements using doubler method
		list.stream()
			.map(e -> doubler(e, () -> e * 2))
			.collect(Collectors.toList());
		
		//Adder to sum 2 ints, show lambda expression and method
		int sum = adder(4,  10, (a, b) -> {
			if(a < 5) return 0;
			return a + b;
					
		});
		System.out.println(sum);
		
		//Replace predicate with EvenChecker
		list.stream()
			.filter(e -> new EvenChecker().isEven(e))
			.forEach(System.out::println);

	}
	
	private static int adder(int a, int b, Adder adder) {
		return adder.add(a, b);
	}
	
	private static int doubler(int e, Doubler doubler) {
		return doubler.doubleMe();
	}
	
	interface Doubler {
		int doubleMe();
	}
	
	interface Adder {
		int add(int a, int b);
	}
	
	static class EvenChecker {
		public boolean isEven(int a) {
			return a % 2 == 0;
		}
	}

}
