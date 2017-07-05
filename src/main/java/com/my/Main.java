package com.my;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dumin on 7/4/17.
 */
public class Main {




    public static void main(String... s) throws IOException, InterruptedException, URISyntaxException {
        String fileName = s[0];

        final ActorSystem system = ActorSystem.create("root-actor");

        try {
            final ActorRef printer = system.actorOf(Printer.props(), "printer");
            final ActorRef longestWord = system.actorOf(LongestWord.props(), "longestWord");
            final ActorRef reverseWord = system.actorOf(ReverseWord.props(), "reverseWord");
            final ActorRef wordCounter = system.actorOf(WordCounter.props(printer), "wordCounter");
            final ActorRef splitterObserver = system.actorOf(SplitterObserver.props(longestWord, reverseWord, wordCounter), "splitterObserver");
            new WithoutNIOExample(splitterObserver, fileName).main();

//            Alternatively:

            List<String> paths = new ArrayList<>(Arrays.asList("/user/reverseWord", "/user/longestWord", "/user/wordCounter"));
            final ActorRef broadcast= system.actorOf(new akka.routing.BroadcastGroup(paths).props(), "broadcast");
            final ActorRef splitterRouterObserver = system.actorOf(SplitterRoterObserver.props(broadcast), "splitterObserver");
            new WithoutNIOExample(splitterRouterObserver, fileName).main();




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

        public void main() throws IOException, URISyntaxException {

            //read file into stream, try-with-resources
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

                stream.forEach(line -> actorRef.tell(line, ActorRef.noSender()));

            } catch (IOException e) {
                throw e;
            }
        }

    }
}
