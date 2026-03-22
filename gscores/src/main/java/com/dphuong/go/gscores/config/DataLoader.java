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
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    private static final int BATCH_SIZE = 10000;

    @Value("${data.file-path}")
    private String DATA_PATH;

    @Override
    public void run(String... args) throws Exception {
        // 1. Kiểm tra xem đã có sinh viên chưa để tránh nạp đè
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM students", Integer.class);
        if (count != null && count > 0) return;

        log.info("Loading data...");

        // 2. Lấy ID môn học từ DB để làm Map tra cứu (Cache)
        Map<String, Long> subjectMap = jdbcTemplate.query(
                "SELECT id, name FROM subjects",
                (rs, rowNum) -> Map.entry(rs.getString("name"), rs.getLong("id"))
        ).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Thứ tự môn học tương ứng với các cột trong file CSV của bạn (index 1 -> 9)
        String[] csvColumns = {
                "Toán", "Ngữ văn", "Ngoại ngữ", "Vật lý",
                "Hoá học", "Sinh học", "Lịch sử", "Địa lý", "GDCD"
        };

        log.info("Starting batch import from CSV...");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getInputStream(DATA_PATH)))) {
            String line;
            boolean firstLine = true;

            List<Object[]> studentBatch = new ArrayList<>(BATCH_SIZE);
            List<Object[]> scoreBatch = new ArrayList<>(BATCH_SIZE);

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] arr = line.split(",", -1);
                String sbd = arr[0];
                String langCode = arr.length > 10 ? arr[10] : null;

                // Nạp vào batch sinh viên
                studentBatch.add(new Object[]{sbd, langCode});

                // Duyệt qua các cột điểm
                for (int i = 0; i < csvColumns.length; i++) {
                    BigDecimal scoreVal = parseBigDecimal(arr[i + 1]);
                    if (scoreVal != null) {
                        Long subId = subjectMap.get(csvColumns[i]);
                        scoreBatch.add(new Object[]{sbd, subId, scoreVal});
                    }
                }

                if (studentBatch.size() >= BATCH_SIZE) {
                    saveData(studentBatch, scoreBatch);
                }
            }
            saveData(studentBatch, scoreBatch); // Nạp nốt phần cuối
            log.info("Done importing data...");
        }
    }

    private void saveData(List<Object[]> studentBatch, List<Object[]> scoreBatch) {
        if (!studentBatch.isEmpty()) {
            jdbcTemplate.batchUpdate("INSERT INTO students (sbd, foreign_language_code) VALUES (?, ?)", studentBatch);
            studentBatch.clear();
        }
        if (!scoreBatch.isEmpty()) {
            jdbcTemplate.batchUpdate("INSERT INTO scores (sbd, subject_id, score) VALUES (?, ?, ?)", scoreBatch);
            scoreBatch.clear();
        }
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
