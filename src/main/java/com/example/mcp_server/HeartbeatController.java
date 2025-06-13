package com.example.mcp_server;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class HeartbeatController {

    @GetMapping(value = "/heartbeat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> heartbeat() {
        Flux<ServerSentEvent<String>> heartbeatFlux = Flux.interval(java.time.Duration.ofSeconds(15))
                .map(sequence -> ServerSentEvent.builder("Heartbeat " + sequence)
                        .event("heartbeat")
                        .build());

        Flux<ServerSentEvent<String>> eventFlux = getActualEventStream();
        return Flux.merge(heartbeatFlux, eventFlux);
    }

    private Flux<ServerSentEvent<String>> getActualEventStream() {
        return Flux.never();
    }
}
