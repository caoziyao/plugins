package com.zel.market.crawler.request;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
public class FirefoxDriverRequest extends SeleniumRequest {


    @Override
    public void init(boolean headless) {
        String driverpath = this.getClass().getClassLoader().getResource("driver/geckodriver.exe").getPath();
        System.setProperty("webdriver.gecko.driver", driverpath);

        DesiredCapabilities cap = new DesiredCapabilities();
        if (proxy != null) {
            cap.setCapability(CapabilityType.PROXY, proxy);
        }
        cap.setAcceptInsecureCerts(true);

        FirefoxBinary firefoxBinary = new FirefoxBinary();
        if (headless) {
            firefoxBinary.addCommandLineOptions("--headless");
        }
        firefoxBinary.addCommandLineOptions("--disable-web-security");
        //firefoxBinary.addCommandLineOptions("");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        firefoxOptions.addCapabilities(cap);
        synchronized (this) {
            driver = new FirefoxDriver(firefoxOptions);
        }
        //((FirefoxDriver) driver).setLogLevel(Level.INFO);
    }

    @Override
    public  String getHtml(String url)  {
        driver.get(url);
        String html = (String) ((FirefoxDriver) driver).executeScript("return document.documentElement.outerHTML");

        return html;
    }
}
