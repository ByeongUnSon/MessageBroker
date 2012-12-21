package fac;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FactorialTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Factorial Program (Recursive)");
		System.out.println("################");
		while (true) {
			System.out.print("���� �Է� : ");
			int num = sc.nextInt();
			if (num <= 0) {
				System.out.println("1 �̻��� ���ڸ� �Է��ϼ���");
			}
			else {
				try {
					System.out.println("factorial(" + num + ") �Լ� ȣ�� : "
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
