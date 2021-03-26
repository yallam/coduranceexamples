import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ArithmeticsTest {
    Arithmetics arithmetics = new Arithmetics();
    @ParameterizedTest

    @CsvSource({
            "should cover in paranthasis, (1+2),true",
            "something1, 1+2),false",
            "something1, ((2*3)/(3*1)),true",
            "something1, ((3^2)*(3*2)),true",
            "something1, (8(a*1)),false"})
    public void operationsShouldBeWrappedInParentheses(String senario, String expression,boolean expected){

        assertEquals(expected,arithmetics.parenthesesCheck(expression));
//        @ValueSource(strings = {"(1+2)","((2*3)/(3*1))","((3^2)*(3*2))","(8(a*1))"});
//        assertTrue(arithmetics.parenthesesCheck(expression));
    }
//    @Test
//    public void operationsWhenNotWrappedInParenthesesShouldReturnFalse(){
//
//        assertEquals(false,arithmetics.parenthesesCheck("1+2)"));
//    }
//    @Test
//    public void transactionShouldBeWrappedInEvenNumberOfParentheses(){
//        assertEquals(true,arithmetics.parenthesesCheck("(8(4*1))"));
//    }
//    @Test
//    public void transactionWhenWrappedInOddNumberOfParenthesesShouldReturnFalse(){
//        assertEquals(false,arithmetics.parenthesesCheck("((8(4*1))"));
//    }
//    @Test
//    public void transactionShouldNotHaveAtleastOneAlphabet(){
//        assertEquals(false,arithmetics.parenthesesCheck("(8(a*1))"));
//    }

}
