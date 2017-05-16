package demo.aj;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HelloLambdas {

	public static void main(String[] args) {
		//Thread new thread -> Replace Anonymous class with lambda
		// No input parameters lambda needs an empty () to signify lack of input parameters
		Thread thread = new Thread(() -> System.out.println("Inside run"));
		thread.start();
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		//Double and sum all elements using doubler method
		//Single parameter lambda doesn't need ( ) around input parameters
		list.stream()
			.map(e -> doubler(e, () -> e * 2))
			.collect(Collectors.toList());
		
		//Adder takes two ints and returns an int, show lambda method
		//We can have code blocks as the body of lambdas
		//Two parameter lambdas need ( ) around input parameters
		int sum = adder(4,  10, (a, b) -> {
			if(a < 5) return 0;
			return a + b;
					
		});
		System.out.println(sum);
		
		//Replace predicate with EvenChecker
		//EvenChecker doesn't implement Predicate but we can still use isEven because the method signatures are same
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
	
	@FunctionalInterface
	interface Doubler {
		int doubleMe();
	}
	
	@FunctionalInterface
	interface Adder {
		int add(int a, int b);
	}
	
	static class EvenChecker {
		public boolean isEven(int a) {
			return a % 2 == 0;
		}
	}

}
