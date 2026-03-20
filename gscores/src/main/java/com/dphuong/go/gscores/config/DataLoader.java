package com.dphuong.go.gscores.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    private static final int BATCH_SIZE = 10000;
    private static final String INSERT_SQL = """
            INSERT INTO exam_result (sbd, math, literature, foreign_language, physics, chemistry, biology, history, geography, civic_education, foreign_language_code)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    @Value("${data.file-path}")
    private String DATA_PATH;

    @Override
    public void run(String... args) throws Exception {

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM exam_result", Integer.class);
        if (count != null && count > 0) return;
        log.info("Loading data from {}", DATA_PATH);
        InputStream inputStream = getInputStream(DATA_PATH);

        int totalInserted = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean firstLine = true;
            List<Object[]> batch = new ArrayList<>(BATCH_SIZE);

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] arr = line.split(",", -1);
                batch.add(new Object[]{
                        arr[0],
                        parseBigDecimal(arr[1]),
                        parseBigDecimal(arr[2]),
                        parseBigDecimal(arr[3]),
                        parseBigDecimal(arr[4]),
                        parseBigDecimal(arr[5]),
                        parseBigDecimal(arr[6]),
                        parseBigDecimal(arr[7]),
                        parseBigDecimal(arr[8]),
                        parseBigDecimal(arr[9]),
                        arr.length > 10 ? arr[10] : null
                });

                if (batch.size() == BATCH_SIZE) {
                    jdbcTemplate.batchUpdate(INSERT_SQL, batch);
                    totalInserted += batch.size();
                    log.info("Inserted {} records...", totalInserted);
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                jdbcTemplate.batchUpdate(INSERT_SQL, batch);
                totalInserted += batch.size();
            }
        }

        log.info("Loaded {} exam results successfully", totalInserted);
    }


    private InputStream getInputStream(String path) throws IOException {

        if (path.startsWith("classpath:")) {
            String realPath = path.replace("classpath:", "");

            // đảm bảo load từ root classpath
            if (!realPath.startsWith("/")) {
                realPath = "/" + realPath;
            }

            InputStream is = getClass().getResourceAsStream(realPath);

            if (is == null) {
                throw new FileNotFoundException("File not found in classpath: " + realPath);
            }

            return is;
        }

        return new FileInputStream(path);
    }

    private BigDecimal parseBigDecimal(String s) {
        if (s == null || s.isBlank()) return null;
        return new BigDecimal(s.trim());
    }
}
