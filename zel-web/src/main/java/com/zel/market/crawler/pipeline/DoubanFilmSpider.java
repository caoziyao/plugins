package com.zel.market.crawler.pipeline;

import com.zel.commonutils.FileUtils;
import com.zel.market.crawler.downloader.FirefoxDriverRequest;
import com.zel.market.crawler.downloader.SipderStringRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
@Component
public class DoubanFilmSpider {

    public static final String DOUBAN_BASE_URL = "https://movie.douban.com";
    protected FirefoxDriverRequest firefoxDriverRequest = new FirefoxDriverRequest();


    public DoubanFilmSpider() {
        //this.url = url;
        //firefoxDriverRequest.init(false);
    }

    public String process() {
        try {
            Thread.sleep(1000);
            System.out.println("douban task!" + DOUBAN_BASE_URL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String filmName = "柯南";
        //String url = "https://search.douban.com/movie/subject_search?search_text=" + filmName;
        String url = "https://search.douban.com/movie/subject_search?search_text=%E6%9F%AF%E5%8D%97&cat=1002";

        String html = new SipderStringRequest().get(url);

        //if (null != firefoxDriverRequest) {
        //    html = firefoxDriverRequest.getHtml(url);
        //    //WebDriverWait wait = new WebDriverWait(firefoxDriverRequest.getDriver(), 10);
        //    //WebElement yodaBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("detail")));
        //    //html = firefoxDriverRequest.getHtml(url);
        //}

        //System.out.println("html: " + html);

        try {
            FileUtils.write("./cache/柯南.html", html);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document document = Jsoup.parse(html);

        Elements detail = document.getElementsByClass("detail");
        if (detail.size() == 0) {
            return "";
        }

        for (Element d : detail) {
            Elements title = d.getElementsByClass("title");
            Elements links = title.select("a[href]");
            for (Element link : links) {
                String href = link.attr("href");
                String name = link.text();
                System.out.println(href + ":" + name);
                if (name.trim().replace(" ", "").equals(filmName.replace(" ", ""))) {

                    System.out.println("href: " + href);
                    return href;
                }
            }
        }

        return "";
    }
}
