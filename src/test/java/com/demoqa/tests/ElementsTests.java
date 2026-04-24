package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.elements.BrokenLinksImagesPages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ElementsTests extends TestBase {

    BrokenLinksImagesPages broken;

    @BeforeEach
    public void precondition() {
        broken = new BrokenLinksImagesPages(driver);
    }

    @Test
    @DisplayName("Checking for expected broken links")
    public void checkBrokenLinksTest() {
        List<String> brokenUrls = broken.open()
                .getBrokenLinks();

        Assertions.assertEquals(1, brokenUrls.size(),
                "The number of broken links does not match the expected number! Links found:\n" + String.join("\n", brokenUrls));

        logger.info("Test passed: exactly 1 broken link was found, as expected.");
    }

    @Test
    @DisplayName("Checking for expected broken images")
    public void checkBrokenImagesTest() {
        List<String> brokenImages = broken.open()
                .getBrokenImages();

        Assertions.assertEquals(2, brokenImages.size(),
                "The number of broken images does not match the expected number! Found src:\n" + String.join("\n", brokenImages));

        logger.info("Test passed: exactly 2 broken images were detected, as expected..");
    }
}