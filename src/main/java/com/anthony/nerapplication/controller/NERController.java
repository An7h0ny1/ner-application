package com.anthony.nerapplication.controller;

import com.anthony.nerapplication.core.Pipeline;
import com.anthony.nerapplication.model.Type;
import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controlador REST para realizar el reconocimiento de entidades nombradas (NER) utilizando Stanford CoreNLP.
 */
@RestController
@RequestMapping(value = "api/v1")
public class NERController {

    private final StanfordCoreNLP stanfordCoreNLP;

    /**
     * Constructor de la clase NERController.
     * @param pipeline Instancia de Pipeline para obtener el StanfordCoreNLP configurado.
     */
    public NERController(Pipeline pipeline) {
        this.stanfordCoreNLP = pipeline.getInstance();
    }
    /**
     * Método REST POST para realizar el reconocimiento de entidades nombradas (NER) en un texto dado.
     * @param input Texto de entrada en el que se realizará el reconocimiento de entidades nombradas.
     * @param type Tipo de entidad nombrada que se desea extraer (por ejemplo, PERSON, CITY, etc.).
     * @return Un conjunto de cadenas que representan las entidades nombradas encontradas en el texto de entrada.
     */
    @PostMapping("/ner")
    public ResponseEntity<?> ner(@RequestBody final String input, @RequestParam final Type type){
        try {
            CoreDocument coreDocument = new CoreDocument(input);
            stanfordCoreNLP.annotate(coreDocument);
            List<CoreLabel> coreLabelList = coreDocument.tokens();

            Set<String> result = new HashSet<>(collectList(coreLabelList, type));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    /**
     * Método privado para filtrar y recopilar las entidades nombradas de una lista de CoreLabel.
     * @param coreLabelList Lista de CoreLabel generada por Stanford CoreNLP.
     * @param type Tipo de entidad nombrada que se desea extraer.
     * @return Una lista de cadenas que representan las entidades nombradas del tipo especificado.
     */
    private List<String> collectList (List<CoreLabel> coreLabelList, final Type type){
        return coreLabelList
                .stream()
                .filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toList());
    }
}
