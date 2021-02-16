package com.tiagods.akkaactor.springakka.services;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinRouter;
import com.tiagods.akkaactor.springakka.listener.EcoActor;
import com.tiagods.akkaactor.springakka.model.Mensagem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AkkaService {

    // Criação de um Actor System, que é o container Akka.
    ActorSystem actorSystem;

    public void envioSimples(){
        var ecoActor = "eco";
        var actorRef = criarEcoActor(ecoActor);

        actorRef.tell(new Mensagem(ecoActor), ActorRef.noSender());
        actorRef.tell(new Mensagem(ecoActor), ActorRef.noSender());
    }

    public void envioComRotas() {
        obterActorSystem();
        ActorRef router = actorSystem.actorOf(
                Props.create(EcoActor.class).withRouter(new RoundRobinRouter(2)));

        int i = 0;
        while(i < 10) {
            router.tell(new Mensagem(String.format("Enviando mensagem %s...", i)), ActorRef.noSender());
            i++;
        }
        log.info("Envio de mensagens concluidos");
    }

    private ActorSystem obterActorSystem() {
        actorSystem = (actorSystem==null) ? ActorSystem.create("HelloSystem") : actorSystem;
        return actorSystem;
    }

    private ActorRef criarEcoActor(String ecoActor) {
        obterActorSystem();
        return actorSystem.actorOf(Props.create(EcoActor.class), ecoActor);
    }

    private void enviarMensagem(ActorRef actorRef, String mensagem) {
        // Enviando a mensagem ao ator

    }
}
