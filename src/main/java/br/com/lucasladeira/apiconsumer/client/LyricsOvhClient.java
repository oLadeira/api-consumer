package br.com.lucasladeira.apiconsumer.client;

import br.com.lucasladeira.apiconsumer.response.Lyrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LyricsOvhClient {

    private final WebClient webClient;

    public LyricsOvhClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://api.lyrics.ovh/v1/").build();
    }


    public Mono<Lyrics> getLyricsByArtistAndMusic(String artist, String musicName){
        log.info("Buscando letra da musica pelo artista e nome da musica");
        return webClient
                .get()
                .uri(artist + "/" + musicName)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados"))
                )
                .bodyToMono(Lyrics.class);
    }
}
