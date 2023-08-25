package logica.DataStructure.Arbol;


import logica.DataStructure.Pila.Pila;

import java.util.Stack;

public class ExpressionTree {
    private TreeNode root;

    public ExpressionTree() {
        root = null;
    }

    public void buildTree(String postfixExpression) {
        String[] tokens = postfixExpression.split("\\s+");
        Stack<TreeNode> nodeStack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                TreeNode operatorNode = new TreeNode(token);
                operatorNode.setRight(nodeStack.pop());
                operatorNode.setLeft(nodeStack.pop());
                nodeStack.push(operatorNode);
            } else {
                nodeStack.push(new TreeNode(token));
            }
        }

        if (!nodeStack.isEmpty()) {
            root = nodeStack.pop();
        }
    }

    private boolean isOperator(String token) {
        switch (token) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                return true;
            default:
                return false;
        }
    }

    public double evaluate() {
        return evaluateExpression(root);
    }

    private double evaluateExpression(TreeNode node) {
        if (node == null) {
            return 0.0;
        }

        if (isOperator(node.getValue())) {
            double leftValue = evaluateExpression(node.getLeft());
            double rightValue = evaluateExpression(node.getRight());
            return performOperation(node.getValue(), leftValue, rightValue);
        } else {
            return Double.parseDouble(node.getValue());
        }
    }

    private double performOperation(String operator, double leftOperand, double rightOperand) {
        switch (operator) {
            case "+":
                return leftOperand + rightOperand;
            case "-":
                return leftOperand - rightOperand;
            case "*":
                return leftOperand * rightOperand;
            case "/":
                return rightOperand == 0.0 ? Double.POSITIVE_INFINITY : leftOperand / rightOperand;
            case "^":
                return Math.pow(leftOperand, rightOperand);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static int getPrecedence(char operator) {
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
}