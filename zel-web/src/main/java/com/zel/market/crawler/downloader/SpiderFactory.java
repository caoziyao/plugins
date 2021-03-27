package com.zel.market.crawler.downloader;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
public class SpiderFactory {

    public static FirefoxDriverRequest  getWebdriver() {
        FirefoxDriverRequest firefoxDriverRequest = new FirefoxDriverRequest();
        firefoxDriverRequest.setProxy(firefoxDriverRequest.getDefaultProxy());
        firefoxDriverRequest.init(true);

        return firefoxDriverRequest;
    }
}
