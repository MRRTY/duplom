package jarvis.dictionaryMethod.htmlParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jarvis.dictionaryMethod.entity.Forest;
import jarvis.dictionaryMethod.entity.Meaning;
import jarvis.dictionaryMethod.entity.Tree;
import jarvis.dictionaryMethod.entity.Word;
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
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    private static final String BASE_URL = "https://ukrainskamova.com";
    private Set<String> links;
    private List<ProxyImpl> proxies;
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


    public Tree getTreeFromURL(String url, ProxyImpl myProxy) throws IOException {
        System.out.println(url+" "+Thread.currentThread().getName());
        Proxy proxy = new Proxy(                                      //
                Proxy.Type.HTTP,                                      //
                InetSocketAddress.createUnresolved(myProxy.getUrl(), myProxy.getPort()) //
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

    public void getLinks() throws IOException, InterruptedException {
        CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(Set.class, String.class);
        ObjectMapper mapper = new ObjectMapper();
        links = mapper.readValue(new File("c:/test/1.json"),typeReference);
        links.addAll(mapper.readValue(new File("c:/test/2.json"),typeReference));
        System.out.println(links.stream().distinct().collect(Collectors.toSet()).size());

    }

    public Forest getForest(){
        Forest forest = new Forest();
        Set<Tree> res = new HashSet<>() ;
        List<Callable<Tree>> callables = new ArrayList<>();
        for (String link: links){
            callables.add(new Callable<Tree>() {
                @Override
                public Tree call() throws Exception {

                    return getTreeFromURL(link,getRandomProxy());
                }
            });
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            res = executor.invokeAll(callables).stream().map(f -> {
                try {
                    return f.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toSet());
        } catch (InterruptedException  ex) {
            System.out.println(ex.getMessage());
        }
        forest.setTreeSet(res);
        return forest;
    }

    private ProxyImpl getRandomProxy() {
        Random random = new Random();
        int n = random.nextInt(proxies.size());
        return proxies.get(n);

    }


    public void saveForestToJson(Forest forest, File jsonFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(jsonFile, forest);
    }



    public void setLinks(Set<String> links) {
        this.links = links;
    }

    public List<ProxyImpl> getProxies() {
        return proxies;
    }

    public void setProxies(List<ProxyImpl> proxies) {
        this.proxies = proxies;
    }

    public static class ProxyImpl{
        private String url;
        private int port;

        public ProxyImpl(String url, int port) {
            this.url = url;
            this.port = port;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
