package com.dphuong.go.gscores.config;

import com.dphuong.go.gscores.entity.ExamResult;
import com.dphuong.go.gscores.repository.ExamResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final ExamResultRepository examResultRepository;

    @Value("${data.file-path}")
    private String DATA_PATH;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading data from {}", DATA_PATH);
        if (examResultRepository.count() > 0) return;

        InputStream inputStream = getInputStream(DATA_PATH);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean firstLine = true;
            List<ExamResult> examResults = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] arr = line.split(",", -1);
                ExamResult examResult = ExamResult.builder()
                        .sbd(arr[0])
                        .math(parseBigDecimal(arr[1]))
                        .literature(parseBigDecimal(arr[2]))
                        .foreignLanguage(parseBigDecimal(arr[3]))
                        .physics(parseBigDecimal(arr[4]))
                        .chemistry(parseBigDecimal(arr[5]))
                        .biology(parseBigDecimal(arr[6]))
                        .history(parseBigDecimal(arr[7]))
                        .geography(parseBigDecimal(arr[8]))
                        .civicEducation(parseBigDecimal(arr[9]))
                        .foreignLanguageCode(arr.length>10 ? arr[10] : null)
                        .build();

                examResults.add(examResult);
            }
            log.info("Loading {} exam results", examResults.size());
            examResultRepository.saveAll(examResults);
            log.info("Loaded {} exam results", examResults.size());
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
