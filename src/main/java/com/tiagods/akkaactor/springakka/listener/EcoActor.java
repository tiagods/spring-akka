package com.tiagods.akkaactor.springakka.listener;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.tiagods.akkaactor.springakka.model.Mensagem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EcoActor extends UntypedActor {

    //Declaramos o ator filho como atributo de EcoActor
    private ActorRef childActor;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        //Na inicialização do ator, instanciamos o ator filho
        childActor = getContext().actorOf(Props.create(ChildActor.class), "childOfEco");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if( msg instanceof Mensagem) {
            log.info("Mensagem recebida pelo ator pai, repassando: {} {}", msg, getSelf().path().name());
            Thread.sleep(3000);
            //Repassamos a mensagem recebida para o ator filho
            childActor.tell(msg, getSelf());
        } else {
            unhandled(msg);
        }
    }
}
