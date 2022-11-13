public class LabellingPatternExpressions {

    // Input: pattern expression w, ex. Seq(a, Seq(Concur(b, c, d), Concur Re(e, f, g)))
    public static String labelExpressions(String expression) {
        StringBuilder result = new StringBuilder();
        int labelNumber = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                labelNumber++;
                result.append("(" + labelNumber + "]");
            } else if (c == ')') {
                result.append("[" + labelNumber + ")");
                labelNumber--;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

}
