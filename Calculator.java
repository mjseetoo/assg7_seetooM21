package assg7_seetooM21;

import java.util.Stack;

public class Calculator {
	String exp;
	String postfix;
	
	/*
	 * @param exp
	 */
	public Calculator(String exp){
		this.exp = exp;
	}
	/*
	 * @returns this.exp
	 */
	public String toString() {
		return this.exp;
	}
	/*
	 * @returns true, false
	 */
	private boolean convertPostfix() {
		
		Stack<Character> stack = new Stack<Character>();
		String converted = "";
		
		for(int i = 0; i < this.exp.length() ; i++) {
			if(Character.isDigit(this.exp.charAt(i))) {
				String temp = "";
				int count = i;
				while(count < this.exp.length() && Character.isDigit(this.exp.charAt(count))) {
					temp += (this.exp.charAt(count));
					count++;
				}
				i = count-1;
				converted +=  temp + " ";
			}
			else if(this.exp.charAt(i) =='(') {
				stack.push(this.exp.charAt(i));
			}
			else if(this.exp.charAt(i) ==')') {
				while(!stack.isEmpty() && stack.peek() != '('){
					converted+=stack.peek();
					stack.pop();
				}
				stack.pop();
			}
			else if(this.exp.charAt(i)!= ' '){
				int precedence = determinePrecedence(this.exp.charAt(i));
				while(!stack.isEmpty() && precedence <= determinePrecedence(stack.peek())) {
					converted +=stack.peek();
					stack.pop();
					}
					stack.push(this.exp.charAt(i));
					}
		}
		while (!stack.isEmpty()) {
			if (stack.peek()=='(') {
				return false;
			}
			converted +=stack.peek();
			stack.pop();
		}
		this.postfix = converted;
		return true;
	}
	/*
	 * @param ope
	 * 
	 * @returns 1, 2, 3, 0
	 */
	public static int determinePrecedence(char ope) {
		switch(ope) {
		case '^':
			return 3;
		case '*':
			return 2;
		case '/':
			return 2;
		case '+':
			return 1;
		case '-':
			return 1;
		}
		return 0;
	}
	public String getPostfix() throws IllegalStateException {
		if(!convertPostfix()) {
			throw new IllegalStateException("Illegal State!");
		}
		else {
			return this.postfix;
		}
	}
	/*
	 * @returns stack.pop()
	 * 
	 * @throws IllegalStateException
	 */
	public int evaluate() throws IllegalStateException{
		
		try {
		String pfx = getPostfix();
		}
		catch(IllegalStateException e) {
			throw new IllegalStateException("Illegal State!");
		}
        Stack<Integer> stack=new Stack<>();
         
        for(int i=0;i<postfix.length();i++){
        	
            if(Character.isDigit(postfix.charAt(i))) {
            	String temp = "";
            	int count = i;
			
				while(count < postfix.length() && Character.isDigit(postfix.charAt(count))) {
					temp += (postfix.charAt(count));
					count++;
				}
				i = count-1;
				int pusher = Integer.parseInt(temp);
				stack.push(pusher);
            }
            else if(postfix.charAt(i) == ' ') {
            	
            }
            else{
            	
                int x = stack.pop();
                int y = stack.pop();
                 
	            if(postfix.charAt(i) == '+') {
                    stack.push(x+y);
	            }
	            else if(postfix.charAt(i) == '-') {
	            	stack.push(y-x);
	            }
	            else if(postfix.charAt(i) == '/') {
	            	 stack.push(x/y);
	            }
	            else if(postfix.charAt(i) == '*') {
	            	stack.push(x*y);
	            }
	            else if(postfix.charAt(i) == '^') {
	            	stack.push((int) Math.pow(x, y));
	            }

              }
           }
        
        return stack.pop();   
    }
}
