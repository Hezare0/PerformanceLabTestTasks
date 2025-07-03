package org.testTask;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Task3 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("В качестве аргументов укажите путь к tests.json, values.json, report.json");
            return;
        }

        String testsFilePath = args[0];
        String valuesFilePath = args[1];
        String reportFilePath = args[2];

        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode testsRoot = mapper.readTree(new File(testsFilePath));
            JsonNode valuesRoot = mapper.readTree(new File(valuesFilePath));

            Map<Integer, String> valueMap = new HashMap<>();
            for (JsonNode valueNode : valuesRoot.get("values")) {
                int id = valueNode.get("id").asInt();
                String value = valueNode.get("value").asText();
                valueMap.put(id, value);
            }

            updateTestValues(testsRoot, valueMap);

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), testsRoot);
        } catch (IOException e) {
            System.out.println("Ошибка в обработке файла " +e.getMessage());
        }
    }

    private static void updateTestValues(JsonNode node, Map<Integer, String> valueMap) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;

            if (objectNode.has("id") && objectNode.has("value")) {
                int id = objectNode.get("id").asInt();
                if (valueMap.containsKey(id)) {
                    objectNode.put("value", valueMap.get(id));
                }
            }

            Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                if (field.getValue().isArray() || field.getValue().isObject()) {
                    updateTestValues(field.getValue(), valueMap);
                }
            }
        } else if (node.isArray()) {
            for (JsonNode arrayElement : node) {
                updateTestValues(arrayElement, valueMap);
            }
        }
    }
}