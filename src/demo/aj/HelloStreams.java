package demo.aj;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HelloStreams {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// Use map to double each element and create a new list
		List<Integer> doubleList = list.stream()
										.map(t -> t * 2)
										.collect(Collectors.toList());
		
		System.out.println(doubleList);
		
		//Demonstrate chaining of operations
		// Filter out even numbers and sum them
		System.out.println("Sum of even numbers " + 
							list.stream()
								.filter(e -> e % 2 == 0)
								.mapToInt(Integer::valueOf) //Sum operation is only available on int streams, so we have to map our Integer to int
								.sum()); 
		
		IntStream intStream = IntStream.range(1,  11);
		// Sum the elements of the stream
		System.out.println("Sum all elements of the stream" + intStream.sum());
		
		Stream.of(1, 2, 3, 4, 5, 6);
		// Filter even numbers
		Stream.of(1, 2, 3, 4, 5, 6)
				.filter( e -> e % 2 == 0)
				.forEach(e -> System.out.println(e)); //Can be replaced by a method reference System.out::println
	}

}
