package com.my;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ReverseWord extends AbstractActor {

    static public Props props() {
        return Props.create(ReverseWord.class, ReverseWord::new);
    }

    static public class Greeting {
        public final String message;

        public Greeting(String message) {
            this.message = message;
        }
    }


    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public ReverseWord() {
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, greeting -> {
                    log.info("reverse for -={}=-  is -={}=-", greeting, new StringBuffer(greeting).reverse());
                })
                .build();
    }

}

