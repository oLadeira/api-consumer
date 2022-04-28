package br.com.lucasladeira.apiconsumer.controller;

import br.com.lucasladeira.apiconsumer.client.LyricsOvhClient;
import br.com.lucasladeira.apiconsumer.response.Lyrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/webclient")
public class LyricsController {

    @Autowired
    private LyricsOvhClient lyricsOvhClient;


    @GetMapping("/lyrics/{artist}/{musicName}")
    public Mono<Lyrics> getLyricsByArtistAndMusic (@PathVariable(name = "artist") String artist, @PathVariable(name = "musicName") String musicName){
        return lyricsOvhClient.getLyricsByArtistAndMusic(artist, musicName);
    }
}
