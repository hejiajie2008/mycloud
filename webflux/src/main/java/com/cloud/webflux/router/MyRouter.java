package com.cloud.webflux.router;


import com.cloud.api.respostory.UserRespostory;
import com.cloud.api.vo.UserVo;
import com.cloud.webflux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MyRouter {

    @Autowired
    UserHandler userHandler;

    @Autowired
    UserRespostory userRespostory;

    @Bean
    RouterFunction<ServerResponse> testRouter(){


       // Flux.interval(Duration.)
        return route(GET("/router/test"),request ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Flux.interval(Duration.ofSeconds(1)).map(s->{return "---"+new Date().toString()+"---";}),String.class)
        ).andRoute(GET("/router/test2"),
                request ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just("test"),String.class));
    }

    @Bean
    RouterFunction<ServerResponse> userRouter(){
        return route(POST("/router/saveUser"),userHandler::save)
                .andRoute(GET("/router/listUser"),request -> {
                    return ServerResponse.ok().body(Flux.fromIterable(userRespostory.queryList()),UserVo.class);
                });
    }
}
