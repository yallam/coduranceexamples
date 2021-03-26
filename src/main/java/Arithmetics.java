public class Arithmetics {

  public boolean parenthesesCheck(String operation) {
    boolean transactionCheck = false;
    long countOfStartingParentheses = operation.chars().filter(ch -> ch == '(').count();
    long countOfEndingParentheses = operation.chars().filter(ch -> ch == ')').count();
    if ((operation.startsWith("(") && operation.endsWith(")"))
        && (countOfEndingParentheses == countOfStartingParentheses)
        && !(operation.matches(".*[a-zA-Z]+.*"))) {

      transactionCheck = true;
    }
    return transactionCheck;
  }
  }

