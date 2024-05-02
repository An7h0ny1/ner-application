package com.anthony.nerapplication.core;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Clase que representa un pipeline para el procesamiento de lenguaje natural utilizando Stanford CoreNLP.
 */
@Service
public class Pipeline {

    private Properties properties;
    private String propertiesName = "tokenize, ssplit, pos, lemma, ner";
    private StanfordCoreNLP stanfordCoreNLP;

    public Pipeline() {
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
        stanfordCoreNLP = new StanfordCoreNLP(properties);
    }

    /**
     * Obtiene una instancia de StanfordCoreNLP para su uso en el procesamiento de lenguaje natural.
     * @return Una instancia de StanfordCoreNLP configurada con los annotators especificados.
     */
    public StanfordCoreNLP getInstance() {
        return stanfordCoreNLP;
    }
}
