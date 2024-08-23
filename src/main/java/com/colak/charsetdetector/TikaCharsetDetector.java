package com.colak.charsetdetector;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.detect.EncodingDetector;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.txt.UniversalEncodingDetector;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
@UtilityClass
public class TikaCharsetDetector {

    public static void main() throws IOException, URISyntaxException {

        URI uri = Objects.requireNonNull(TikaCharsetDetector.class.getClassLoader().getResource("test.txt"))
                .toURI();
        Path filePath = Paths.get(uri);

        EncodingDetector encodingDetector = new UniversalEncodingDetector();
        byte[] fileBytes = Files.readAllBytes(filePath);

        // Create a ByteArrayInputStream from the byte array
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
        Charset detectedCharset = encodingDetector.detect(inputStream, new Metadata());
        log.info("detectedCharset={}", detectedCharset.displayName());
    }
}
