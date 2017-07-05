package com.my;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SplitterRoterObserver extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public SplitterRoterObserver(ActorRef actorRefs) {
        this.actor = actorRefs;
    }

    ActorRef actor;

    static public Props props(ActorRef actors) {
        return Props.create(SplitterRoterObserver.class, () -> new SplitterRoterObserver(actors));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, greeting -> {
                    for (String str : split(greeting)) {
                            actor.tell(str, getSelf());
                    }
//                    log.info(greeting.message);

                }).build();
    }

    private String[] split(String str) {
        return str.split("\\s+");
    }
}
