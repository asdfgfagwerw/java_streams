package demo.aj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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
		
		IntStream intStream = IntStream.range(1,  11);
		// Sum the elements of the stream
		
		System.out.println(intStream.sum());
		
		Stream.of(1, 2, 3, 'a', 'b', "C");
		// Filter elements of particular type
		Stream.of(1, 2, 3, 'a', 'b', "C")
				.filter( t -> t instanceof Character)
				.forEach(t -> System.out.println(t));
	}

}
