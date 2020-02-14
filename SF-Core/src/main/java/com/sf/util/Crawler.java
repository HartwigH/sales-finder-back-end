package com.sf.util;


import com.sf.beans.CrawledProduct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Crawler {

    public static ArrayList<CrawledProduct> getCrawledProducts() {

        ArrayList<CrawledProduct> crawledProducts = new ArrayList<>();

        String url = "https://www.andmorefashion.com/mehed/meeste-roivad.html";

        try {
            Document document = Jsoup.connect(url)
                    //.proxy("", 80)
                    .userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36"
            ).get();

            System.out.println("  " +
                    "/ _ \\\n" +
                    "\\_\\(_)/_/\n" +
                    " _//o\\\\_ initiated..\n" +
                    "  /   \\");

            // get crawl date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();

            System.out.println("[STARTED AT] - " + localDate);


            System.out.println("[CURRENT PAGE] - " + document.title());
            // get first page
            Elements products = document.select("div.product-thumb");

            for (Element element : products) {
                CrawledProduct product = new CrawledProduct();
                product.setName(cleanTitle(element.select("h2 > a").text()));
                product.setStoreId(returnStoreId(element.select("h2 > a").text()));
                product.setUrl(element.select(" h2 > a").attr("href"));
                product.setImgUrl(element.select("p.product-thumb-photo > a > img").attr("src"));
                product.setDataId(element.attr("data-id"));
                product.setPrice(cleanPrice(element.select("p.product-thumb-price > span").text()));
                product.setDate(dtf.format(localDate));
                crawledProducts.add(product);
            }
            crawlNextPages(crawledProducts, document, dtf, localDate);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(crawledProducts.size());

        return crawledProducts;
    }

    private static void crawlNextPages(ArrayList<CrawledProduct> crawledProducts, Document document, DateTimeFormatter dtf, LocalDate localDate) throws IOException {
        //Crab products from all pages
        Elements pagination = document.select(".page-jump.page-next > a");

        boolean toCont = true;
        while (toCont) {

            String urlPages = pagination.attr("href");
            Document documentPages = Jsoup.connect(urlPages)
                    //.proxy("", 80)
                    .userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36"
            ).get();

            // get given page
            Elements productsPages = documentPages.select("div.product-thumb");

            for (Element element : productsPages) {
                CrawledProduct product = new CrawledProduct();
                product.setName(cleanTitle(element.select("h2 > a").text()));
                product.setStoreId(returnStoreId(element.select("h2 > a").text()));
                product.setUrl(element.select(" h2 > a").attr("href"));
                product.setImgUrl(element.select("p.product-thumb-photo > a > img").attr("src"));
                product.setDataId(element.attr("data-id"));
                product.setPrice(cleanPrice(element.select("p.product-thumb-price > span").text()));
                product.setDate(dtf.format(localDate));
                crawledProducts.add(product);
            }

            // end loop if "next" is not found
            if (documentPages.select(".page-jump.page-next > a").isEmpty()) {
                toCont = false;
            } else {
                pagination = documentPages.select(".page-jump.page-next > a");
            }

            //What page am I on
            System.out.println("[CURRENT PAGE] - " + documentPages.title());

        }
    }

    private static String cleanTitle(String title) {
        if (title.contains("Monton") || title.contains("Mosaic")) {
            return title.substring(7);
        } else {
            return title.substring(8);
        }
    }

    private static int returnStoreId(String title) {
        if (title.contains("Monton")) {
            return 1;
        } else if (title.contains("Mosaic")){
            return 2;
        } else if (title.contains("Baltman")) {
            return 3;
        } else {
            return 0;
        }
    }

    private static float cleanPrice(String price) {
        price = price.replace(',', '.');
        price = price.replace(" €", "");
        return Float.parseFloat(price);
    }
}
