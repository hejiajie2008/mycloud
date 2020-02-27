package com.cloud.webflux.handler;

import com.cloud.api.respostory.UserRespostory;
import com.cloud.api.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

     @Autowired
     UserRespostory userRespostory;

    public Mono<ServerResponse> save(ServerRequest serverRequest){
        Mono<UserVo> monoUser=serverRequest.bodyToMono(UserVo.class);
        return ServerResponse.ok().body(monoUser.map(userRespostory::save),Boolean.class);
    }

}
