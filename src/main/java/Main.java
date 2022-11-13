import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String patternRulesFolFile = "./pattern_rules/pattern_rules_FOL.json";
        String patternRulesLtlFile = "./pattern_rules/pattern_rules_LTL.json";
        List<WorkflowPattern> folWorkflowPatterns = WorkflowPattern.loadWorkflowPatterns(patternRulesFolFile);
        List<WorkflowPattern> ltlWorkflowPatterns = WorkflowPattern.loadWorkflowPatterns(patternRulesLtlFile);
    }
}