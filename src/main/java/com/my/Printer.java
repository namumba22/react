package com.my;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Printer extends AbstractActor {

    static public Props props() {
        return Props.create(Printer.class, Printer::new);
    }


    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Printer() {
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, greeting -> {
                    log.info(greeting);
                })
                .build();
    }
}

