package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinksImagesPages extends BasePage {

    public BrokenLinksImagesPages(WebDriver driver) {
        super(driver);
    }

    public BrokenLinksImagesPages open() {
        open("/broken");
        return this;
    }

    @FindBy(css = "a")
    List<WebElement> allLinks;
    @FindBy(css = "img")
    List<WebElement> images;

    public List<String> getBrokenLinks() {
        List<String> brokenLinks = new ArrayList<>();
        logger.info("Checking {} links on a page", allLinks.size());

        for (WebElement element : allLinks) {
            String url = element.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                String brokenUrlStatus = verifyLinkStatus(url);
                if (brokenUrlStatus != null) {
                    brokenLinks.add(brokenUrlStatus);
                }
            }
        }
        return brokenLinks;
    }

    public List<String> getBrokenImages() {
        List<String> brokenImages = new ArrayList<>();
        logger.info("Checking {} images on the page", images.size());

        for (WebElement image : images) {
            boolean imageDisplayed = (Boolean) js.executeScript(
                    "return (typeof arguments[0].naturalWidth!=undefined && arguments[0].naturalWidth>0);", image);
            if (!imageDisplayed) {
                String src = image.getAttribute("src");
                brokenImages.add(src != null ? src : "Unknown image source");
            }
        }
        return brokenImages;
    }

    private String verifyLinkStatus(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            int statusCode = connection.getResponseCode();
            if (statusCode >= 400) {
                return linkUrl + " (Status: " + statusCode + ")";
            }
        } catch (IOException e) {
            return linkUrl + " (ERROR: " + e.getMessage() + ")";
        }
        return null;
    }
}