import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // Loading of Pattern property set from batch files
        String patternRulesLtlFile = "./pattern_rules/pattern_rules_LTL.json"; // Linear Temporal Logic
        List<WorkflowPatternTemplate> ltlPatternPropertySet =
                WorkflowPatternTemplate.loadPatternPropertySet(patternRulesLtlFile);

        // Algorithm 1 - Labelling pattern expressions
        String exampleExpression1 = "Seq(a, Seq(Concur(b,c,d), ConcurRe(e,f,g)))";
        String exampleExpression2 = "Seq(a100, Seq(Concur(b101, c102, d103), ConcurRe(e104, f105, g106)))";
        String labelledPatternExpression1 = LabellingPatternExpressions.labelExpressions(exampleExpression1);
        String labelledPatternExpression2 = LabellingPatternExpressions.labelExpressions(exampleExpression2);
        System.out.println(labelledPatternExpression1);
        System.out.println(labelledPatternExpression2);

        // Algorithm 2 - Calculating consolidated expression
        String ini = CalculatingConsolidatedExpression.generateConsolidatedExpression(
                labelledPatternExpression1.replace(" ", ""), "ini", ltlPatternPropertySet);
        System.out.println("ini: " + ini);
        String fin = CalculatingConsolidatedExpression.generateConsolidatedExpression(
                labelledPatternExpression1.replace(" ", ""), "fin", ltlPatternPropertySet);
        System.out.println("fin: " + fin);

        // Algorithm 3 - Generating logical specifications
        String s = GeneratingLogicalSpecifications.generateLogicalSpecifications(exampleExpression1.replace(" ", ""),
                ltlPatternPropertySet);
        System.out.println(s);
    }
}