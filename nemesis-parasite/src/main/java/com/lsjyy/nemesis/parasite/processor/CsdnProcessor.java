package com.lsjyy.nemesis.parasite.processor;

import com.lsjyy.nemesis.parasite.vo.CsdnBlog;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-05 20:28
 * @Description: csdn页面解析
 */
public class CsdnProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private static final String URL_POST = "(http://s\\.dydytt\\.net/html/gndy/(\\w+)/(\\d+)/(\\d+)\\.html)";

    private static final String URL_POST2 = "(http://www\\.ygdy8\\.com/html/(\\w+)/(\\w+)/(\\d+)/(\\d+)\\.html)";

    @Override
    public void process(Page page) {
        Selectable content = page.getHtml().xpath("//div[@class=\"co_area2\"]/div[@class=\"co_content8\"]/ul/table").links();

        System.out.println(content.toString());

    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("【爬虫开始】...");
        startTime = System.currentTimeMillis();
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new CsdnProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.ygdy8.net/html/gndy/dyzz/index.html")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】耗时约" + ((endTime - startTime) / 1000) + "秒");
    }
}
