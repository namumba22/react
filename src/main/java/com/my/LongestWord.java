package com.my;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class LongestWord extends AbstractActor {

    static public Props props() {
        return Props.create(LongestWord.class, LongestWord::new);
    }

    static public class Greeting {
        public final String message;

        public Greeting(String message) {
            this.message = message;
        }
    }

    String longestWord = "";


    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public LongestWord() {
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, greeting -> {
                    if(longestWord.length()<greeting.length()){
                        longestWord=greeting;
                        log.info("now the longest is = {}",longestWord);
                    }
                })
                .build();
    }
}

