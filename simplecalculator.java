import java.util.*;

public class SimpleExpressionCalculator {

    private static final Map<Character, Integer> PRECEDENCE = Map.of(
        '+', 1, '-', 1,
        '*', 2, '/', 2, '%', 2,
        '^', 3
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an Mathematical expression: (allowed + - * / % ^ and parentheses)");
        String input = sc.nextLine();

        try {
            double result = evaluateExpression(input);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Math error: " + e.getMessage());
        } catch(IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        } catch(Exception e) {
            System.out.println("An error has occured: " + e.getMessage());
        }
    }

    public static double evaluateExpression(String expression) {
        List<String> tokens = tokenise(expression.replaceAll("\\s+", ""));
        List<String> postfix = toPostfix(tokens);
        return evaluatePostfix(postfix);
    }

    private static List<String> tokenise(String expression) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while(i < expression.length()) {
            char c = expression.charAt(i);

            if(Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while(i < expression.length() && (Character.isDigit(c) || c == '.')) {
                    sb.append(expression.charAt(i++));
                }
                tokens.add(sb.toString());
            } else if(PRECEDENCE.containsKey(c) || c == '(' || c ==')') {
                tokens.add(Character.toString(c));
                i++;
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }
        return tokens;
    }

    private static List<String> toPostfix(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> ops = new Stack<>();

        for(int i=0; i< tokens.size(); i++) {
            String token = tokens.get(i);

            if(isNumber(token)) {
                output.add(token);
            } 

            else if(token.equals("(")) {
                ops.push(token);
            } else if(token.equals(")")) {
                while(!ops.isEmpty() && !ops.peek().equals("(")) {
                    output.add(ops.pop());
                }
                if(ops.isEmpty || !ops.pop().equals("(")) {
                    throw new IllegalArgumentException("Mismatched parantheses");
                }
            }

            else if(PRECEDENCE.containsKey(token.charAt(0))) {
                while(!ops.isEmpty() && isHigherPrecedence(ops.peek(), token)) {
                    output.add(ops.pop());
                }
                ops.push(token);
            } else {
                throw new IllegalArgumentException("Inavlid operator: " + token);
            }
        }

        while(!ops.isEmpty()) {
            String op =ops.pop();
            if(op.equals("(") || op.equals(")")) {
                throw new IllegalArgumentException("Mismatched parantheses");
            }
            output.add(op);
        }
        return output;
    }

    private static boolean isNumber(String token) {
        try {Double.parseDouble(token); return true; }
        catch (Exception e) {return false; }
    }

    private static boolean isHigherPrecedence(String opOnStack, String op) {
        if (!PRECEDENCE.containsKey(opOnStack.charAt(0))) {return false; }
        if(op.equals("^")) {return false; }
        return PRECEDENCE.get(opOnStack.charAt(0)) >= PRECEDENCE.get(op.charAt(0));
    }

    private static double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();
        for(String token : postfix) {
            if(isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if(PRECEDENCE.containsKey(token.charAt(0))) {
                if(stack.size() < 2) {
                    throw new IllegalArgumentException("Malformed Expression")
                }
                Double b = stack.pop(), a = stack.pop();
                switch (token.charAt(0)) {
                    case '+' : stack.push(a + b); break;
                    case '-' : stack.push(a - b); break;
                    case '*' : stack.push(a * b); break;
                    case '/' : 
                                if(b == 0) {throw new ArithmeticException("Division by zero"); }
                                stack.push(a / b); break;
                    case '%' :
                                if(b == 0) {throw new ArithmeticException("Modulus by zero"); }
                                stack.push(a % b); break;
                    case '^' : stack.push(Math.pow(a, b)); break;
                    default : throw new IllegalArgumentException("Invalid operator: " + token);
                }

            } else {
                throw new IllegalArgumentException("Unexpected token: " + token);
            }
        }
        if(stack.size() != 1) {throw new IllegalArgumentException("Malformed Expression");}
        return stack.pop();
    }

}