package com.tiagods.akkaactor.springakka.listener;

import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.japi.Function;
import lombok.extern.slf4j.Slf4j;
import scala.concurrent.duration.Duration;

@Slf4j
public class ChildActor extends UntypedActor {

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(-1, Duration.Inf(), new Function<Throwable, SupervisorStrategy.Directive>() {
            @Override
            public SupervisorStrategy.Directive apply(Throwable throwable) throws Exception {
                return OneForOneStrategy.resume();
            }
        });
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        log.info("Mensagem recebida pelo ator filho: {} {}", msg, getSelf().path().name());
    }
}

