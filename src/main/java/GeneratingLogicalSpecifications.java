import org.apache.commons.lang3.*;

import java.util.*;

public class GeneratingLogicalSpecifications {

    // Algorithm 3 - Generating logical specifications
    //
    // Input:   - pattern expression w, ex. Seq(a, Seq(Concur(b, c, d), Concur Re(e, f, g)))
    //          - predefined pattern property set Σ (non-empty)
    // Output: logical specification L
    //                          ex.
    //
    public static String generateLogicalSpecifications(String patternExpression, List<WorkflowPatternTemplate> patternPropertySet) throws Exception {
        List<String> logicalSpecification = new ArrayList<>();
        String labelledExpression = LabellingPatternExpressions.labelExpressions(patternExpression);
        int highestLabelNumber = getHighestLabel(labelledExpression);
        for (int l = highestLabelNumber; l > 0; l--) {
            int c = 1;
            WorkflowPattern pat = getPat(labelledExpression, l, c, patternPropertySet);
//            System.out.println(pat.getWorkflowPatternTemplate().getName());
            do {
                List<String> L2 = pat.getPatternArguments();
                L2.remove(0);
                L2.remove(0);
                for (String arg : pat.getPatternArguments2()) {
                    if (!WorkflowPattern.isAtomic(arg)) {
                        String cons = CalculatingConsolidatedExpression.generateConsolidatedExpression(arg, "ini", patternPropertySet) + " | " + CalculatingConsolidatedExpression.generateConsolidatedExpression(arg, "fin", patternPropertySet);

                        List<String> L2_cons = new LinkedList<String>();
                        for (String outcome : L2) {
                            L2_cons.add(outcome.replace(arg, cons));
                        }
                        L2 = L2_cons;
                    }
                }
                c++;
                logicalSpecification.addAll(L2);
                pat = getPat(labelledExpression, l, c, patternPropertySet);
            } while (pat != null);

        }

        Set<String> set = new HashSet<>(logicalSpecification);
        logicalSpecification.clear();
        logicalSpecification.addAll(set);
        String connectedString = "";
        System.out.println("\nWynik: ");
        for (String lValue : logicalSpecification) {
            connectedString = connectedString + lValue + ", ";
            System.out.println(lValue);
        }
        return connectedString;

    }

    private static int getHighestLabel(String labelledExpression) {
        // TODO improve
        labelledExpression = labelledExpression.replaceAll("[^0-9]+", " ");
        List<String> labels = Arrays.asList(labelledExpression.trim().split(" "));
        int maxLabel = -1;
        for (String label : labels) {
            int castedLabel = Integer.valueOf(label);
            maxLabel = Math.max(castedLabel, maxLabel);
        }
        return maxLabel;
    }

    private static WorkflowPattern getPat(String labelledExpression, int l, int c, List<WorkflowPatternTemplate> patternPropertySet) throws Exception {
        System.out.println("l: "+l);
        System.out.println("c: "+c);
        int entryOccurrences = StringUtils.countMatches(labelledExpression, "(" + l + "]");
        int endOccurrences = StringUtils.countMatches(labelledExpression, "[" + l + ")");
        if (entryOccurrences != endOccurrences) throw new Exception("(" + l + "] nie równa się [" + l + ")");
//        if (entryOccurrences < c) throw new Exception("Wystąpień ("+entryOccurrences+") jest mniej niż ("+c+")");
        if (entryOccurrences < c) return null;
        String[] split = labelledExpression.split("\\(" + l + "]");
//        System.out.println("split:");
//        System.out.println(Arrays.stream(split).toList());
        String[] split1 = split[c].split("\\[" + l + "\\)");
//        System.out.println("split1:");
//        System.out.println(Arrays.stream(split1).toList());
        String[] split2 = split[c - 1].split("]");
//        System.out.println("split2:");
//        System.out.println(Arrays.stream(split2).toList());
        String[] split3 = split2[split2.length - 1].split(",");
//        System.out.println("split3:");
//        System.out.println(Arrays.stream(split3).toList());
        String workflowName = split3[split3.length - 1];

        String workflowExp = workflowName + "("+ l + "]" +  split1[0] +"[" +l+")";
        System.out.println(workflowExp);

        return WorkflowPattern.getWorkflowPatternFromExpression(workflowExp, patternPropertySet);


//        int occurenceIndex = 0;
//        do {
//            if (occurenceIndex == 0)
//                occurenceIndex = labeledExpression.indexOf(l + "]", occurenceIndex);
//            else {
//                int oldOccurenceIndex = occurenceIndex;
//                String subst = labeledExpression.substring(oldOccurenceIndex + 1);
//                occurenceIndex = subst.indexOf(l + "]") + oldOccurenceIndex + 1;
//                if (oldOccurenceIndex == occurenceIndex) return null;
//            }
//            c -= 1;
//        } while (c > 0);
//        StringBuilder pattern = new StringBuilder();
//        String args = labeledExpression.substring(occurenceIndex + (l + "]").length(), labeledExpression.indexOf("[" + l, occurenceIndex));
//        char[] chars = labeledExpression.toCharArray();
//        int beginExpressionIndex = 0;
//        for (int i = occurenceIndex; i >= 0; i--) {
//            if (chars[i] == ']' || chars[i] == ',') {
//                beginExpressionIndex = i;
//                break;
//            }
//        }
//        String expressionName = "";
//        if (beginExpressionIndex != 0)
//            expressionName = labeledExpression.substring(beginExpressionIndex + 1, occurenceIndex - 1);
//        else
//            expressionName = labeledExpression.substring(0, occurenceIndex - 1);
//
//        System.out.println("Xdddd");

        // tworzy nowy obiekt WorkflowPattern (na podstawie WorkflowPatternTemplate i argumentów w patternExpression)
//        String finalExpressionName = expressionName;
//        WorkflowPatternTemplate workflowPatternTemplate = patternPropertySet.stream().filter(x -> x.getName().equals(finalExpressionName.trim())).findFirst().orElseThrow();
//        List<String> patternArguments = WorkflowPattern.extractArgumentsFromLabelledExpression(labeledExpression, patternPropertySet);
//        return new WorkflowPattern(workflowPatternTemplate, patternArguments);


//        PredefinedSetEntry result = patternPropertySet.findByIdentifier(expressionName.trim());
//        if (result != null) {
//            List<String> arguments = extractArgumentsFromFunction(args);
//            result.PassArguments(arguments);
//        }
//        return result;
//        return null;
    }


}
