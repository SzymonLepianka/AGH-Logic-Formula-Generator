import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // Loading of workflow patterns from batch files
        String patternRulesFolFile = "./pattern_rules/pattern_rules_FOL.json";
        String patternRulesLtlFile = "./pattern_rules/pattern_rules_LTL.json";
        List<WorkflowPattern> folWorkflowPatterns = WorkflowPattern.loadWorkflowPatterns(patternRulesFolFile);
        List<WorkflowPattern> ltlWorkflowPatterns = WorkflowPattern.loadWorkflowPatterns(patternRulesLtlFile);

        // Algorithm 1 - Labelling pattern expressions
        String exampleExpression1 = "Seq(a, Seq(Concur(b,c,d), ConcurRe(e,f,g)))";
        String exampleExpression2 = "Seq(a100, Seq(Concur(b101, c102, d103), ConcurRe(e104, f105, g106)))";
        String labelledPatternExpression1 = LabellingPatternExpressions.labelExpressions(exampleExpression1);
        String labelledPatternExpression2 = LabellingPatternExpressions.labelExpressions(exampleExpression2);
        System.out.println(labelledPatternExpression1);
        System.out.println(labelledPatternExpression2);
    }
}