package jarvis.dictionaryMethod.htmlParser;

import jarvis.dictionaryMethod.entity.Tree;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.Set;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void saveForestToJson() throws Exception {
        File file = new File("d:/test/db.json");
        Parser parser = new Parser();
        parser.saveForestToJson(parser.getForest(),file);
    }

    @Test
    @Ignore
    public void getTreeFromURL() throws Exception {
        Parser parser = new Parser();
        Tree tree = parser.getTreeFromURL("publ/slovnik_sinonimiv/ja/jachati/50-1-0-5005");
        System.out.println(tree);
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