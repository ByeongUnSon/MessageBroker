package stack;

public class StackEx {

	private Object[] stack;
	private int stackSize;
	private int top;

	/*
	 * STACK Size SET
	 */	
	public StackEx(int stackSize) {
		top = -1;
		this.stackSize = stackSize;
		stack = new Object[this.stackSize]; 	// 스택의 저장공간 할당
	}

	public int getSize() {
		return stackSize;
	}
	
	/*
	 *  PUSH OverFlowException
	 */
	public void push(String str) throws Exception {
		try {
			stack[++top] = str;
			System.out.println("Pushed String : " + str);
		} catch (ArrayIndexOutOfBoundsException abe) {
			throw new OverFlowException("OverFlow발생!!", abe);
		}		
	}
	
	/*
	 *  POP UnderFlowException
	 */	
	public String pop() throws Exception {		
		try {
			return (String) stack[top--];
		} catch (ArrayIndexOutOfBoundsException abe) {
			throw new UnderFlowException("UnderFlow발생!!", abe);
		} 
	}
		
	public boolean isEmpty() {
		return top==-1;
	}
	
	public boolean isFull() {
		return top==this.stackSize - 1;
	}
		
	public void printStack() {
		if (isEmpty()) {
			System.out.println("Stack is Empty !!");
		} else if (isFull()) { 
			System.out.println("Stack is Full !!");
		} else {
			System.out.print("Inserted String : [ ");
			for (int i=0; i<=top; i++) {
				System.out.print(stack[i] + "   ");
			}
			System.out.print(" ]");			
			System.out.println();
		}
	}

}
