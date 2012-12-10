package fac;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FactorialTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("팩토리얼 계산 프로그램");
		System.out.println("################");
		System.out.print("숫자 입력 : ");
		int num = sc.nextInt();
		try {
			System.out.println("factorial("+num+") 함수 호출 결과 : " + factorial(num));
		} catch (InputMismatchException ime) {
			System.out.println(ime.getMessage());
		}
	}
	public static int factorial(int n) {
		int result = 0;
		if (n<=1)
			return 1;
		else {
			result = n * factorial(n-1);
			return result;
		}
	}
}
