package stack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StackTest {

	public static void main(String[] args) throws Exception {
		
		StackEx s = null;
		Scanner sc = null;
		boolean ok1 = true;
		boolean ok2 = true;
		int size = 0;
		int select = 0;
		sc = new Scanner(System.in);

		System.out.println("------------ Push/Pop & OverFlow/UnderFlow Test Program ------------");
		System.out.println("Stack의 크기를 먼저 지정해주세요.");
		System.out.print("크기 입력 : ");
		try {
			size = sc.nextInt();
		} catch (InputMismatchException ime) {
			throw new InputException("숫자를 입력하세요!!", ime);
		}
		s = new StackEx(size);
		System.out.println("설정된 스택의 크기 : " + s.getSize());
		
		while (ok1) {
			ok2 = true;
			System.out.println("1~3 번호중 선택해주세요(1 : Push , 2 : Pop , 3 : Exit)");
			System.out.println("1. Push");
			System.out.println("2. Pop");
			System.out.println("3. Exit");
			System.out.print("번호 선택 : ");
			while (ok2) {
				try {
					select = sc.nextInt();
				} catch (InputMismatchException ime) {
					throw new InputException("숫자를 입력하세요!!", ime);
				}
				switch (select) {
				case 1:
					System.out.print("문자열 입력 : ");
					String str = sc.next();
					System.out.println("*******	 Push 실행	 *******");
					s.push(str);
					System.out.println("Push된 문자열 : " + str);
					s.printStack();
					ok2 = false;
					break;
				case 2:
					System.out.println("*******	 Pop 실행 	*******");
					String nowStr = s.pop();					
					System.out.println("Pop된 문자열 : "+ nowStr);
					ok2 = false;
					s.printStack();
					break;
				case 3:
					System.out.println("테스트 프로그램이 종료되었습니다!!");
					System.exit(0);
					break;
				}
			}
		}
	}
}
