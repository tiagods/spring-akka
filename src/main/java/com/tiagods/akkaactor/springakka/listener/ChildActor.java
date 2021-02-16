package com.tiagods.akkaactor.springakka.listener;

import akka.actor.UntypedActor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChildActor extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws Exception {
        log.info("Mensagem recebida pelo ator filho: {} {}", msg, getSelf().path().name());
    }
}

