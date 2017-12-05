package jarvis.dictionaryMethod.htmlParser;

import jarvis.dictionaryMethod.entity.Forest;
import jarvis.dictionaryMethod.entity.Meaning;
import jarvis.dictionaryMethod.entity.Tree;
import jarvis.dictionaryMethod.entity.Word;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    private static final String BASE_URL = "https://ukrainskamova.com";

    public Set<String> getAllWords() throws IOException {
        Set<String> res = new HashSet<>();
        try {
            for (int i = 39; i <= 50; i++) {
                Document doc = Jsoup.connect(BASE_URL + "/publ/slovnik_sinonimiv/" + i).get();
                if (!doc.select(".swchItem").isEmpty()) {
                    int pagesNumber = Integer.parseInt(doc.select(".swchItem").get(doc.select(".swchItem").size() - 2).select("span").text());
                    for (int j = 2; j <= pagesNumber; j++) {

                        Document pageDoc = Jsoup.connect(BASE_URL + "/publ/slovnik_sinonimiv/" + i + "-" + j).get();
                        pageDoc.select(".wid_stat").forEach(div -> {
                            String link = div.select("a").attr("href");
                            System.out.println(link);
                            for (int k = 0; k < 100; k++) {
                                int test = 124 * k * k;
                            }
                            res.add(link);
                        });
                    }
                }

                Elements divs = doc.select(".wid_stat");
                divs.forEach(div -> {
                    String link = div.select("a").attr("href");
                    System.out.println(link);
                    res.add(link);
                });
            }
            System.out.println(res);
            return res;
        }catch (HttpStatusException ex) {
            return res;
        }
    }


    public Tree getTreeFromURL(String url) throws IOException {
        Proxy proxy = new Proxy(                                      //
                Proxy.Type.HTTP,                                      //
                InetSocketAddress.createUnresolved("185.13.228.124", 1009) //
        );
        Document doc = Jsoup
                .connect(BASE_URL+url)
                .proxy(proxy)
                .get();
        Element elem = doc.select("#m_text").first();
        elem.select("#uSocial").remove();
        elem.select("#com_none").remove();
        String temp = doc.select("h1").first().text();
        elem.select("b").remove();
        String text = elem
                .text()
                .replaceAll("\\(.*?\\)", "")
                .replaceAll("[+.^:!,;]", "")
                .trim();
        String[] vals = text.split(" ");
        System.out.println(Arrays.toString(vals));
        Set<Meaning> meanings = new HashSet<>();
        Arrays.stream(vals).forEach(val->{
            if(val.length()>=2)
            meanings.add(new Word(val));
        });
        Tree res = new Tree(temp.toLowerCase(),meanings);
        System.out.println(res);
        return res;
    }

    public Forest getForest() throws IOException, InterruptedException {
        CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(Set.class, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Set<String> urls = mapper.readValue(new File("D:/test/1.json"),typeReference);
        urls.addAll(mapper.readValue(new File("D:/test/2.json"),typeReference));
        System.out.println(urls.stream().distinct().collect(Collectors.toSet()).size());
        Forest forest = new Forest();
        forest.setTreeSet(new HashSet<>());
        try {

        for(String url: urls){
            System.out.println(url);
                forest.getTreeSet().add(getTreeFromURL(url));
            System.out.println("Done!");

        }

            return forest;
        }catch (HttpStatusException ex){

            return forest;
        }
    }
    public void saveForestToJson(Forest forest, File jsonFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(jsonFile, forest);
    }
}
