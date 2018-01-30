
import com.google.fora.day.component.core.URLContentReader;
import com.google.fora.day.component.parser.impl.EditorKitHtmlParserImpl;
import com.google.fora.day.component.parser.HtmlParser;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class HTMLTagValidator {

    private static HtmlParser htmlParser = new EditorKitHtmlParserImpl(new URLContentReader());

    static String html2text(String urlString) throws Exception {

        URL url = new URL(urlString);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 3128));
        URLConnection urlConnection = url.openConnection(proxy);

        InputStream is = urlConnection.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuilder content = new StringBuilder();

        String read;
        while ((read = br.readLine()) != null) {
            content.append(read);
        }

        return Jsoup.parse(content.toString()).text();
    }

    /*
    private static void indexPage(String urlPage, int depth_level) {

        if (depth_level == 0) {
            return;
        }

        HtmlPage htmlPage = parseUrl(urlPage);

        if (htmlPage != null) {

            showPageContent(htmlPage, urlPage);

            final List<String> links = htmlPage.getLinks();

            for (String link :
                    links) {

                if (depth_level != 2 || !link.equals(urlPage)) {
                    indexPage(link, depth_level - 1);
                }
            }
        }

    }

    private static HtmlPage parseUrl(String link) {

        try {
            return htmlParser.parseUrl(link);
        } catch (OpenUrlException | MalformedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void showPageContent(HtmlPage htmlPage, String urlPage) {

        System.out.println("*************************************************");
        System.out.println("Root Page Name: " + urlPage);
        System.out.println("Root Page Title: " + htmlPage.getTitle());
        System.out.println("Root Page Links: " + htmlPage.getLinks());
        System.out.println("*************************************************");
        System.out.println();
    }

    private static void indexPage(String urlPage) {

        indexPage(urlPage, 1);
    }*/

    public static void main(String[] args) throws Exception {

        //System.out.println(html2text("https://correo.uci.cu"));

        String urlPage = "http://www.google.com.cu";

        if (urlPage.contains("https://www.google.com")) {
            System.out.println("Asunto resuelto en 1");

            return;
        }
        if (urlPage.contains("http://www.google.com")) {
            System.out.println("Asunto resuelto en 2");
        }


        //html2text("http://www.facebook.com");
        //indexPage("https://correo.uci.cu");

        /*HtmlPage page = htmlParser.parseUrl("https://internos.uci.cu/");
        System.out.println("Title: " + page.getTitle());
        System.out.println("Links: " + page.getLinks());
        System.out.println();

        page = htmlParser.parseUrl("http://periodico.uci.cu/");
        System.out.println("Title: " + page.getTitle());
        System.out.println("Links: " + page.getLinks());
        System.out.println();*/


        /*URL url = new URL("https://internos.uci.cu/");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 3128));
        URLConnection urlConnection = url.openConnection(proxy);

        InputStream is = urlConnection.getInputStream();

        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder builder = new StringBuilder("");
        String cad;
        while ((cad = br.readLine()) != null){
            builder.append(cad);
        }

        String content = builder.toString();
        System.out.println(content);
        System.out.println(content = content.replaceAll("(\\s+|\n)", " "));

        Pattern p = Pattern.compile("<head>.*?<title>(.*?)</title>.*?</head>", Pattern.DOTALL);
        Matcher m = p.matcher(content);
        while (m.find()) {
            content = m.group(1).trim();
        }

        System.out.println("Title: " + content);*/

    }
}
