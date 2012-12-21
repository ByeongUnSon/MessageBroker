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
		System.out.println("Stack�� ũ�⸦ ���� �������ּ���.");
		System.out.print("ũ�� �Է� : ");
		try {
			size = sc.nextInt();
		} catch (InputMismatchException ime) {
			throw new InputException("���ڸ� �Է��ϼ���!!", ime);
		}
		s = new StackEx(size);
		System.out.println("������ ������ ũ�� : " + s.getSize());
		
		while (ok1) {
			ok2 = true;
			System.out.println("1~3 ��ȣ�� �������ּ���(1 : Push , 2 : Pop , 3 : Exit)");
			System.out.println("1. Push");
			System.out.println("2. Pop");
			System.out.println("3. Exit");
			System.out.print("��ȣ ���� : ");
			while (ok2) {
				try {
					select = sc.nextInt();
				} catch (InputMismatchException ime) {
					throw new InputException("���ڸ� �Է��ϼ���!!", ime);
				}
				switch (select) {
				case 1:
					System.out.print("���ڿ� �Է� : ");
					String str = sc.next();
					System.out.println("*******	 Push ����	 *******");
					s.push(str);
					System.out.println("Push�� ���ڿ� : " + str);
					s.printStack();
					ok2 = false;
					break;
				case 2:
					System.out.println("*******	 Pop ���� 	*******");
					String nowStr = s.pop();					
					System.out.println("Pop�� ���ڿ� : "+ nowStr);
					ok2 = false;
					s.printStack();
					break;
				case 3:
					System.out.println("�׽�Ʈ ���α׷��� ����Ǿ����ϴ�!!");
					System.exit(0);
					break;
				}
			}
		}
	}
}
