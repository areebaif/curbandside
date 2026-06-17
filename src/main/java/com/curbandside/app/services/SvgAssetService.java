package com.curbandside.app.services;// Adjust package name to match your project

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class SvgAssetService {

    // Cache to hold: [Filename without extension -> Raw SVG XML string content]
    private final Map<String, String> svgCache = new HashMap<>();

    // Map to translate: [Database Enum Value -> Filename Key]
    private final Map<String, String> categoryToSvgMap = new HashMap<>();

    public SvgAssetService() {
        // Map your database category enums to the matching static filenames
        categoryToSvgMap.put("TYPE_SOFA", "sofa");
        categoryToSvgMap.put("TYPE_TABLE", "table");
        categoryToSvgMap.put("TYPE_BED", "bed");
        categoryToSvgMap.put("TYPE_CHAIR", "chair");
        categoryToSvgMap.put("TYPE_DRESSER", "dresser");
        categoryToSvgMap.put("TYPE_DESK", "desk");
        categoryToSvgMap.put("TYPE_SHELVING", "shelving");
        categoryToSvgMap.put("TYPE_APPLIANCE", "appliance");
        categoryToSvgMap.put("TYPE_OTHER", "other");
    }

    @PostConstruct
    public void loadAllSvgsOnStartup() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            // Locates all files ending in .svg inside src/main/resources/static/
            Resource[] resources = resolver.getResources("classpath:/static/images/*.svg");
            for (Resource resource : resources) {
                String filename = resource.getFilename();
                if (filename != null) {
                    // "sofa.svg" becomes "sofa"
                    String key = filename.replace(".svg", "").toLowerCase().trim();
                    String content = resource.getContentAsString(StandardCharsets.UTF_8);
                    svgCache.put(key, content);
                }
            }
            System.out.println("Successfully cached " + svgCache.size() + " SVG asset file(s) on startup.");
        } catch (IOException e) {
            System.err.println("Fatal Error: Failed to pre-load SVG assets on startup: " + e.getMessage());
        }
    }

    /**
     * Resolves a category string into an instantly renderable Base64 SVG Data URL.
     *
     * @param category The incoming category (e.g., "TYPE_SOFA")
     * @return Formatted string data string for HTML image tag src attribute
     */
    public String getSvgForCategory(String category) {

        if (category == null) {
            return getFallbackDataUrl();
        }




        // 2. Step One Lookup: Translate Enum token to corresponding Filename reference
        String fileNameKey = categoryToSvgMap.get(category);



        // 3. Step Two Lookup: Retrieve the raw XML string from cache using filename reference
        String svgContent = null;
        if (fileNameKey != null) {

            svgContent = svgCache.get(fileNameKey);

        }

        // 4. Fallback Path: If category wasn't mapped or file asset was missing from static directory
        if (svgContent == null) {
            String fallbackFileName = categoryToSvgMap.getOrDefault("TYPE_OTHER", "other");
            svgContent = svgCache.get(fallbackFileName.toLowerCase().trim());
        }

        // 6. Convert raw cached text markup directly into a compiled Base64 Data URL string block
        return convertSvgToDataUrl(svgContent);
    }

    private String getFallbackDataUrl() {
        String fallbackFileName = categoryToSvgMap.getOrDefault("TYPE_OTHER", "other");
        String fallbackSvg = svgCache.get(fallbackFileName.toLowerCase().trim());
        return fallbackSvg != null ? convertSvgToDataUrl(fallbackSvg) : "";
    }

    private String convertSvgToDataUrl(String rawSvgContent) {
        // Standard Base64 translation engine allocation
        String base64Encoded = Base64.getEncoder()
                .encodeToString(rawSvgContent.getBytes(StandardCharsets.UTF_8));

        // Returns string block for standard frontend handling: img.src = dataUrl
        return "data:image/svg+xml;base64," + base64Encoded;
    }
}