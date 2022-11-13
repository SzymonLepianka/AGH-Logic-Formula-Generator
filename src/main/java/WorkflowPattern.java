import org.json.*;

import java.io.*;
import java.util.*;

public class WorkflowPattern {
    private String name;
    private int numberOfArguments;
    private List<String> rules;

    public WorkflowPattern(String name, int numberOfArguments, List<String> rules) {
        this.name = name;
        this.numberOfArguments = numberOfArguments;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfArguments() {
        return numberOfArguments;
    }

    public void setNumberOfArguments(int numberOfArguments) {
        this.numberOfArguments = numberOfArguments;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public static List<WorkflowPattern> loadWorkflowPatterns(String pathToPatternRulesFile) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(pathToPatternRulesFile);
        JSONTokener jsonTokener = new JSONTokener(fileInputStream);
        JSONObject jsonObject = new JSONObject(jsonTokener);
        List<WorkflowPattern> workflowPatternList = new ArrayList<>();
        for (String workflowPatternName: jsonObject.keySet()) {
            JSONObject patternDescrJSONObject = (JSONObject)jsonObject.get(workflowPatternName);
            int numberOfArguments = (int) patternDescrJSONObject.get("number of args");
            JSONArray rules = (JSONArray) patternDescrJSONObject.get("rules");
            List<String> rulesList = new ArrayList<>();
            for (var rule: rules.toList()) {
                rulesList.add((String) rule);
            }
            WorkflowPattern workflowPattern = new WorkflowPattern(workflowPatternName, numberOfArguments, rulesList);
            workflowPatternList.add(workflowPattern);
        }
        return workflowPatternList;
    }}
