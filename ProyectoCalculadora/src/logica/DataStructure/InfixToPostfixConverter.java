package logica.DataStructure;

import logica.DataStructure.Pila.Pila;

public class InfixToPostfixConverter {

    private  int getPrecedence(char operator) {
        switch (operator) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public  String infixToPostfix(String infixExpression) {


        StringBuilder postfixExpression = new StringBuilder();
        Pila<Character> operatorStack=new Pila<>();

        for (int i = 0; i < infixExpression.length(); i++) {
            char token = infixExpression.charAt(i);

                     if (Character.isLetterOrDigit(token) || token == '.') {
                while (i < infixExpression.length() && (Character.isDigit(infixExpression.charAt(i)) || infixExpression.charAt(i) == '.')) {
                    postfixExpression.append(infixExpression.charAt(i));
                    i++;
                }
                i--;
                postfixExpression.append(" ");
            } else if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfixExpression.append(operatorStack.pop());
                }
                operatorStack.pop();
            } else {
                while (!operatorStack.isEmpty() && getPrecedence(token) <= getPrecedence(operatorStack.peek())) {
                    postfixExpression.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(token);
                postfixExpression.append(" ");
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixExpression.append(" ");
            postfixExpression.append(operatorStack.pop()).append(" ");
        }

        return postfixExpression.toString();
    }

    public  boolean isValidExpression(String expression) {
        Pila<Character> parenthesisStack = new Pila<>();


        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '(') {
                parenthesisStack.push(ch);
            } else if (ch == ')') {
                if (parenthesisStack.isEmpty()) {
                    return false;
                } else {
                    parenthesisStack.pop();
                }
            }
        }

        return parenthesisStack.isEmpty();
    }
}