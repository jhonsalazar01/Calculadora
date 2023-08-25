package presentacion;

import logica.DataStructure.InfixToPostfixConverter;
import presentacion.Gui.Calculator;

public class RunCalculator {
    public static void main(String[] args) {
        new Calculator().inicialize();
        //System.out.println(new InfixToPostfixConverter().infixToPostfix("-35*6"));
    }
}
