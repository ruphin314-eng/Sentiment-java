package apash.coding.sa.controller;


import apash.coding.sa.entites.Sentiment;
import apash.coding.sa.enums.TypeSentiment;
import apash.coding.sa.service.SentimentService;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "sentiment", produces = APPLICATION_JSON_VALUE)
public class SentimentController {
    private SentimentService sentimentService;

    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Sentiment sentiment){

        this.sentimentService.creer(sentiment);
    }


    @GetMapping
    public List<Sentiment> rechercher(@RequestParam(required = false)TypeSentiment type) {
        return this.sentimentService.rechercher(type);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void supprimer(@PathVariable int id){
        this.sentimentService.supprimer(id);
    }

    @GetMapping("/stats")
    public Map<String, Long> statistiques() {
        long positif = sentimentService.rechercher(TypeSentiment.POSITIF).size();
        long negatif = sentimentService.rechercher(TypeSentiment.NEGATIF).size();

        Map<String, Long> stats = new HashMap<>();
        stats.put("positif", positif);
        stats.put("negatif", negatif);

        return stats;
    }
}
