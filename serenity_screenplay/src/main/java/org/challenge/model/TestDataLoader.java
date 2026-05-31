package org.challenge.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for loading test data from JSON files located in src/test/resources/testdata/.
 *
 * Usage:
 *   List<PurchaseData> data = TestDataLoader.loadPurchaseData("testdata/purchase_data.json");
 */
public class TestDataLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private TestDataLoader() {}

    /**
     * Loads a list of PurchaseData from a JSON file on the test classpath.
     *
     * @param resourcePath path relative to test resources root (e.g. "testdata/purchase_data.json")
     * @return list of PurchaseData, or empty list on failure
     */
    public static List<PurchaseData> loadPurchaseData(String resourcePath) {
        try (InputStream is = TestDataLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                System.err.println("[TestDataLoader] Resource not found: " + resourcePath);
                return Collections.emptyList();
            }
            return MAPPER.readValue(is, new TypeReference<List<PurchaseData>>() {});
        } catch (Exception e) {
            System.err.println("[TestDataLoader] Error reading " + resourcePath + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
