package jarvis.dictionaryMethod.entity;

import org.codehaus.jackson.annotate.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Phrase.class, name = "phrase"),

        @JsonSubTypes.Type(value = Word.class, name = "word") }
)
public abstract class Meaning {
    Meaning(){
    }

}
