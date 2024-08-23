package com.colak.charsetdetector;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@UtilityClass
public class IcuCharsetDetector {

    public static void main() throws IOException, URISyntaxException {

        URI uri = Objects.requireNonNull(IcuCharsetDetector.class.getClassLoader().getResource("test.txt"))
                .toURI();
        Path filePath = Paths.get(uri);

        CharsetDetector detector = new CharsetDetector();
        // Read all bytes from the file into a byte array
        byte[] fileBytes = Files.readAllBytes(filePath);
        detector.setText(fileBytes);

        CharsetMatch charsetMatch = detector.detect();
        log.info("charsetMatch={}", charsetMatch.getName());

        Arrays.stream(detector.detectAll())
                .forEach(match -> log.info("{} - Confidence: {}", match.getName(), match.getConfidence()));
    }
}
