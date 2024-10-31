package com.colak.charsetdetector;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Slf4j
@UtilityClass
public class IcuCharsetDetector {

    public static void main() throws IOException {

        InputStream inputStream = TikaCharsetDetector.class.getClassLoader().getResourceAsStream("test.txt");

        CharsetDetector detector = new CharsetDetector();

        detector.setText(inputStream);

        CharsetMatch charsetMatch = detector.detect();
        log.info("charsetMatch={}", charsetMatch.getName());

        Arrays.stream(detector.detectAll())
                .forEach(match -> log.info("{} - Confidence: {}", match.getName(), match.getConfidence()));
    }
}
