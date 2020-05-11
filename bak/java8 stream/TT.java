package cn.infisa.inquiry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TT {

	public static void main(String[] args) throws FileNotFoundException {
		Student stu1 = new Student("张三", 20, "20209000");
		Student stu2 = new Student("李四", 19, "20209001");
		Student stu3 = new Student("王五", 21, "20209002");
		Student stu4 = new Student("赵六", 20, "20209001");

		// ---------创建流----------
		List<Student> list = new ArrayList<>();
		Stream<Student> s1 = list.stream();
		Stream<Student> ps1 = list.parallelStream();

		Student[] stuArr = new Student[] { stu1, stu2, stu3, stu4 };
		Stream<Student> s2 = Arrays.stream(stuArr);

		Stream<Student> s3 = Stream.of(stuArr);
		Stream<Student> s4 = Stream.iterate(stu1, s -> {
			s.setAge(s.getAge() + 1);
			return s;
		}).limit(3);
		Stream<Integer> s5 = Stream.generate(() -> (new Random()).nextInt(100)).limit(3);

		IntStream ints1 = IntStream.range(1, 9);
		IntStream ints2 = IntStream.of(1, 2, 3);
		IntStream ints3 = IntStream.iterate(2, i -> i++);

//		Stream<String> s6 = new BufferedReader(new FileReader("filePath")).lines();

		Stream<String> s7 = Pattern.compile(",").splitAsStream("a,b,c,d");

		// ---------使用流------------
		list.addAll(Arrays.asList(stuArr));

		Stream<Student> s8 = list.stream();
		Stream<Student> s9 = s8.distinct();
		System.out.println(s9.count());

		System.out.println("----source----");
		list.stream().forEach(System.out::println);
		// ---------中间操作-----------
		System.out.println("----filter----");
		list.stream().filter(t -> t.getAge() == 20).forEach(System.out::println);
		System.out.println("----sorted----");
		list.stream().sorted((t1, t2) -> t1.getAge() - t2.getAge()).forEach(System.out::println);
		System.out.println("----mapToInt----");
		list.stream().mapToInt(t -> t.getAge()).forEach(System.out::println);
		System.out.println("----map----");
		list.stream().map(t -> t.getName()).forEach(System.out::println);
		System.out.println("----flatMap----");
		list.stream().flatMap(t -> Arrays.stream(t.getName().split(""))).forEach(System.out::println);
		System.out.println("----peek----");
		list.stream().sorted((t1, t2) -> t1.getAge() - t2.getAge()).peek(System.out::println).count();
//		list.stream().peek(t -> t.setAge(99)).forEach(System.out::println);
		System.out.println("----distinct----");
		list.stream().distinct().forEach(System.out::println);
		System.out.println("----limit skip----");
		list.stream().skip(1).limit(2).forEach(System.out::println);
		// -----------结束操作----------
		// -----------短路操作----------
		System.out.println("----anyMatch allMatch noneMatch----");
		System.out.println(list.stream().anyMatch(t -> t.getAge() == 88));
		System.out.println(list.stream().allMatch(t -> t.getAge() == 20));
		System.out.println(list.stream().noneMatch(t -> t.getAge() == 88));
		System.out.println("----findFirst findAny----");
		System.out.println(list.stream().findFirst().get());
		System.out.println(list.stream().findAny().get());
		// -----------非短路操作----------
		System.out.println("----max min count----");
		System.out.println(list.stream().max((t1, t2) -> t1.getAge() - t2.getAge()).get());
		System.out.println(list.stream().min((t1, t2) -> t1.getAge() - t2.getAge()).get());
		System.out.println(list.stream().count());
		System.out.println("----collect----");
		List<Integer> intList = list.stream().map(t -> t.getAge()).collect(Collectors.toList());
		intList.stream().forEach(System.out::println);

		System.out.println("----reduce----");
		Integer int1 = intList.stream().reduce((t1, t2) -> t1 + t2).orElse(0);
		System.out.println(int1);
		System.out.println(list.stream().reduce(new Student("", 100, ""), (t1, t2) -> {
			t1.setAge(t1.getAge() + t2.getAge());
			return t1;
		}));

		// -----------并行流----------
		System.out.println("----并行流----");
		list.parallelStream().forEach(System.out::println);
		System.out.println("----forEachOrdered----");
		list.parallelStream().forEachOrdered(System.out::println);
		System.out.println("----findAny----");
		System.out.println(list.stream().unordered().findAny().get());
		System.out.println(list.parallelStream().findAny().get());
		System.out.println("----unordered----");
		list.parallelStream().unordered().peek(System.out::println).forEach(System.out::println);

	}
}
