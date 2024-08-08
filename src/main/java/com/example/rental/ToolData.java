package com.example.rental;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Implements lookup of Tool instances by tool code. Tool and rate info is loaded from CSV file
 * resources.
 */
public class ToolData {
    private static String RATES_FILENAME = "/rates.csv";
    private static String TOOLS_FILENAME = "/tools.csv";
    private static String TOOL_CODE = "Tool Code";
    private static String TOOL_TYPE = "Tool Type";
    private static String BRAND = "Brand";
    private static String DAILY = "Daily";
    private static String WEEKDAY = "Weekday";
    private static String WEEKEND = "Weekend";
    private static String HOLIDAY = "Holiday";

    private final Map<String, Tool> tools = new ConcurrentHashMap<>();

    public ToolData() throws IOException {
        this(
                ToolData.class.getResourceAsStream(TOOLS_FILENAME),
                ToolData.class.getResourceAsStream(RATES_FILENAME));
    }

    public ToolData(InputStream toolInputStream, InputStream rateInputStream) throws IOException {
        Map<String, CSVRecord> rates = new HashMap<>();
        parseCSV(rateInputStream)
                .forEach(
                        record -> {
                            rates.put(record.get(TOOL_TYPE), record);
                        });
        rateInputStream.close();

        parseCSV(toolInputStream)
                .forEach(
                        record -> {
                            CSVRecord rate = rates.get(record.get(TOOL_TYPE));
                            Tool tool =
                                    new Tool()
                                            .setDailyRate(
                                                    new BigDecimal(rate.get(DAILY)).setScale(2))
                                            .setIsChargeHoliday(parseYesNo(rate.get(HOLIDAY)))
                                            .setIsChargeWeekday(parseYesNo(rate.get(WEEKDAY)))
                                            .setIsChargeWeekend(parseYesNo(rate.get(WEEKEND)))
                                            .setToolBrand(record.get(BRAND))
                                            .setToolCode(record.get(TOOL_CODE))
                                            .setToolType(record.get(TOOL_TYPE));
                            tools.put(record.get(TOOL_CODE), tool);
                        });
        toolInputStream.close();
    }

    public Map<String, Tool> getMap() {
        return Collections.unmodifiableMap(tools);
    }

    public Tool getTool(String toolCode) {
        Tool found = tools.get(toolCode);

        // return a copy of the tool to the caller because it's mutable
        return found == null ? null : new Tool(found);
    }

    private Iterable<CSVRecord> parseCSV(InputStream in) throws IOException {
        Reader reader = new InputStreamReader(in, "UTF-8");
        return CSVFormat.RFC4180
                .builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build()
                .parse(reader);
    }

    private boolean parseYesNo(String input) {
        switch (input) {
            case "Yes":
                return true;

            case "No":
                return false;

            default:
                throw new IllegalArgumentException(input);
        }
    }
}
