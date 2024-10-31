package com.colak.charsetdetector;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.detect.EncodingDetector;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.txt.UniversalEncodingDetector;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Slf4j
@UtilityClass
public class TikaCharsetDetector {

    public static void main() throws IOException {
        InputStream inputStream = TikaCharsetDetector.class.getClassLoader().getResourceAsStream("test.txt");

        detectCharset(inputStream);
        detectFileType(inputStream);
    }

    private static void detectCharset(InputStream inputStream) throws IOException {
        EncodingDetector encodingDetector = new UniversalEncodingDetector();

        // Create a ByteArrayInputStream from the byte array
        // ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
        Charset detectedCharset = encodingDetector.detect(inputStream, new Metadata());
        log.info("detectedCharset={}", detectedCharset.displayName());
    }

    private void detectFileType(InputStream inputStream) throws IOException {
        // Implement the content type detection logic
        Detector detector = new DefaultDetector();
        Metadata metadata = new Metadata();

        MediaType mediaType = detector.detect(inputStream, metadata);
        log.info("detectFileType={}", mediaType.toString());
    }
}
