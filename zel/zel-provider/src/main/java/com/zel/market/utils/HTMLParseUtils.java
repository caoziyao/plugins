package com.zel.market.utils;

import com.zel.dbmanager.entity.SSAccount;
import com.zel.dbmanager.service.SSAccountService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/12
 */
@Service
public class HTMLParseUtils {

    @Autowired
    private SSAccountService ssAccountService;

    public void parse() {
        String url = "https://github.com/Alvin9999/new-pac/wiki/ss%E5%85%8D%E8%B4%B9%E8%B4%A6%E5%8F%B7";
        System.out.println(ssAccountService);
        try {
            Document document = Jsoup.parse(new URL(url), 30000);
            Elements elements = document.select("table[role=table]");
            for (Element element : elements) {
                Elements tbody_tr = element.select("tbody tr");
                for (Element tr : tbody_tr) {
                    Elements td = tr.select("td");
                    String location = td.get(0).text();
                    String ip = td.get(1).text();
                    String port = td.get(2).text();
                    String password = td.get(3).text();
                    String rc = td.get(4).text();
                    String proto = td.get(5).text();
                    String plian = td.get(6).text();

                    SSAccount ssAccount = new SSAccount();
                    ssAccount.setLocation(location);
                    ssAccount.setIp(ip);
                    ssAccount.setPort(port);
                    ssAccount.setPassword(password);
                    ssAccount.setEncry(rc);
                    ssAccount.setProtocol(proto);
                    ssAccount.setPlian(plian);

                    ssAccountService.save2(ssAccount);
                    //  ssAccountService.save(ssAccount);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
