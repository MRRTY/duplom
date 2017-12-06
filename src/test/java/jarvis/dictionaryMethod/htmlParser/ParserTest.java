package jarvis.dictionaryMethod.htmlParser;

import jarvis.dictionaryMethod.entity.Tree;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void saveForestToJson() throws Exception {
        File file = new File("c:/test/db.json");
        Parser parser = new Parser();
        List<Parser.ProxyImpl> proxies = new ArrayList<>();
        proxies.add(new Parser.ProxyImpl("193.34.172.140",3128));
        proxies.add(new Parser.ProxyImpl("109.254.211.233",3128));
        proxies.add(new Parser.ProxyImpl("178.136.11.7",54214	));
        proxies.add(new Parser.ProxyImpl("194.126.182.30",53281));
        proxies.add(new Parser.ProxyImpl("193.178.187.136",64444));
        proxies.add(new Parser.ProxyImpl("195.80.140.212",8081));
        proxies.add(new Parser.ProxyImpl("188.0.147.43",80));
        proxies.add(new Parser.ProxyImpl("93.188.161.64",3128));
        proxies.add(new Parser.ProxyImpl("188.0.138.147",8080));
        proxies.add(new Parser.ProxyImpl("62.122.65.60",53281));
        proxies.add(new Parser.ProxyImpl("193.178.187.136",53281));
        proxies.add(new Parser.ProxyImpl("46.164.133.250",3128));
        parser.setProxies(proxies);
        parser.getLinks();
        parser.saveForestToJson(parser.getForest(),file);
    }

    @Test
    @Ignore
    public void getTreeFromURL() throws Exception {
        Parser parser = new Parser();
       // Tree tree = parser.getTreeFromURL("publ/slovnik_sinonimiv/ja/jachati/50-1-0-5005");
        //System.out.println(tree);
    }

    @Test
    public void getPageContent() throws Exception {
        Parser parser = new Parser();
        parser.getAllWords();
    }

    @Test
    public void getWordsSet() throws Exception {
        Parser parser = new Parser();
        ObjectMapper mapper = new ObjectMapper();
        CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(Set.class, String.class);
        Set<String> res = parser.getAllWords();
    }

}