package com.zel.market.crawler.downloader;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public abstract class SeleniumRequest {
    protected WebDriver driver;
    protected Proxy proxy = null;

    public WebDriver getDriver() {
        return driver;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public Proxy getDefaultProxy() {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8080");
        proxy.setSslProxy("localhost:8080");

        return proxy;
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void close() {
        if (driver != null) {
            driver.close();
        }
    }

    public abstract void init(boolean headless);

    public abstract String getHtml(String url) throws Exception;
}
