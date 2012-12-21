package fac;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FactorialTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Factorial Program (Recursive)");
		System.out.println("################");
		while (true) {
			System.out.print("숫자 입력 : ");
			int num = sc.nextInt();
			if (num <= 0) {
				System.out.println("1 이상의 숫자를 입력하세요");
			}
			else {
				try {
					System.out.println("factorial(" + num + ") 함수 호출 : "
							+ factorial(num));
				} catch (InputMismatchException ime) {
					System.out.println(ime.getMessage());
				}
			}
		}
	}

	public static int factorial(int n) {
		int result = 0;
		if (n <= 1)
			return 1;
		else {
			result = n * factorial(n - 1);
			return result;
		}
	}
}
