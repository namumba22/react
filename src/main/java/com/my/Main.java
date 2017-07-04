package com.my;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by dumin on 7/4/17.
 */
public class Main {


    public static void main(String... s) throws IOException, InterruptedException {
        String fileName = "/tmp/README.txt";
        final ActorSystem system = ActorSystem.create("root-actor");

        try {

//            final ActorRef textProcessor = system.actorOf(TextProcessor.props(), "textProcessor");
            final ActorRef printer = system.actorOf(LongestWord.props(), "printer");
            final ActorRef longestWord = system.actorOf(LongestWord.props(), "longestWord");
            final ActorRef reverseWord = system.actorOf(ReverseWord.props(), "reverseWord");
            final ActorRef wordCounter = system.actorOf(WordCounter.props(printer), "wordCounter");
            final ActorRef splitterObserver = system.actorOf(SplitterObserver.props(longestWord,reverseWord,wordCounter), "splitterObserver");
//            wordCounder.tell(new Line("asdfasd"), ActorRef.noSender());

            new WithoutNIOExample(splitterObserver,fileName).main();

            Thread.sleep(5000);
        } finally {
            system.terminate();
        }
    }


    static class WithoutNIOExample {
        ActorRef actorRef;
        String fileName;

        public WithoutNIOExample(ActorRef actorRef, String fileName) {
            this.actorRef = actorRef;
            this.fileName = fileName;
        }

        public void main() throws IOException {

            //read file into stream, try-with-resources
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

                stream.forEach(line -> actorRef.tell(line, ActorRef.noSender()));

            } catch (IOException e) {
                throw e;
            }
        }

    }
}
