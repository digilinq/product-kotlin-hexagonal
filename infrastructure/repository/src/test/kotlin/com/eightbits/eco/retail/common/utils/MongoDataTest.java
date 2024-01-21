package com.eightbits.eco.retail.common.utils;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MongoDataTest {
    public static final String REGEX_DATE_BLOCK = "(\\{\\s*\"\\$date\":\\s*\\{\\s*\"\\$numberLong\"\\s*:\\s*\")(-?\\d+)(\"\\s*}\\s*})";
    public static final String REGEX_NUMBER_LONG = "(\\{\\s*\"\\$numberLong\"\\s*:\\s*\")(-?\\d+)(\"\\s*})";
    public static final String REGEX_NUMBER_INT = "(\\{\\s*\"\\$numberInt\"\\s*:\\s*\")(-?\\d+)(\"\\s*})";
    public static final String REGEX_NUMBER_DOUBLE = "(\\{\\s*\"\\$numberDouble\"\\s*:\\s*\")(-?\\d+.\\d+)(\"\\s*})";

    private String readAsString(String path, Charset charset) throws IOException {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (in == null) throw new IllegalArgumentException(path + " not found");
            return IOUtils.toString(in, charset);
        }
    }

    private String readAsString(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(path))))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private String writeDatesAsTimestamps(String jsonString) {
        StringBuilder builder = new StringBuilder();
        int lastMatchIndex = 0;

        Pattern pattern = Pattern.compile(REGEX_DATE_BLOCK);
        Matcher matcher = pattern.matcher(jsonString);

        while (matcher.find()) {
            LocalDateTime date = convertToISODate(matcher.group(2));
            builder
                    .append(jsonString, lastMatchIndex, matcher.start())
                    .append('"')
                    .append(date.format(DateTimeFormatter.ISO_DATE))
                    .append('"');
            lastMatchIndex = matcher.end();
        }

        builder.append(jsonString, lastMatchIndex, jsonString.length());
        return builder.toString();
    }

    private String writeIdAsLongNumber(String jsonString) {
        return jsonString.replaceAll(REGEX_NUMBER_LONG, "$2");
    }

    private String writeIdAsIntNumber(String jsonString) {
        return jsonString.replaceAll(REGEX_NUMBER_INT, "$2");
    }

    private String writeIdAsDoubleNumber(String jsonString) {
        return jsonString.replaceAll(REGEX_NUMBER_DOUBLE, "$2");
    }

    private LocalDateTime convertToISODate(String numberLong) {
        Date date = new Date(Long.parseLong(numberLong));
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Test
    void translate_mongo_document_to_order_unit_entity() throws Exception {
        Optional.of(readAsString("mongodb/documents/taxonomy-993.json",
                        StandardCharsets.UTF_8))
                .map(this::writeDatesAsTimestamps)
                .map(this::writeIdAsLongNumber)
                .map(this::writeIdAsIntNumber)
                .map(this::writeIdAsDoubleNumber)
                .ifPresent(System.out::println);
    }
}
