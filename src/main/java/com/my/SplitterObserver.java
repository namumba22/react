package com.my;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

//#greeter-messages
public class SplitterObserver extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public SplitterObserver(ActorRef ... actorRefs) {
        this.actors = actorRefs;
    }

    ActorRef[] actors;

    static public Props props(ActorRef ... actors) {
        return Props.create(SplitterObserver.class, () -> new SplitterObserver(actors));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, greeting -> {
                    for (String str : split(greeting)) {
                        for (ActorRef actor:actors){
                            actor.tell(str, getSelf());
                        }
                    }
//                    log.info(greeting.message);

                }).build();
    }

    private String[] split(String str) {
        return str.split("\\s+");
    }
//#greeter-messages
}
//#greeter-messages
