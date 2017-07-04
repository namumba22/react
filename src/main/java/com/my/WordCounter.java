package com.my;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.collection.Seq;
import scala.collection.mutable.StringBuilder;

public class WordCounter extends AbstractActor {


    static public Props props(ActorRef printer) {
        return Props.create(WordCounter.class, () -> new WordCounter(printer));
    }

    ActorRef printer;

    int quantity = 0;

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public WordCounter(ActorRef printer) {
        this.printer = printer;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, greeting -> {
                    quantity+=1;
                    printer.tell(String.format("words quantity = %05d",quantity),getSelf());
                })
                .build();
    }
}

