package com.me;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.my.WordCounter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//import com.lightbend.akka.sample.Greeter.*;
//import com.lightbend.akka.sample.Printer.*;

public class AkkaQuickstartTest {
    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testGreeterActorSendingOfGreeting() throws InterruptedException {
        final TestKit testProbe = new TestKit(system);
        final ActorRef wordCounter = system.actorOf(WordCounter.props(testProbe.getRef()));
        wordCounter.tell("a", ActorRef.noSender());
        wordCounter.tell("b", ActorRef.noSender());
        wordCounter.tell("c", ActorRef.noSender());
//        helloGreeter.tell(new Greet(), ActorRef.noSender());
//        Thread.sleep(5000);
//        String greeting = testProbe.expectMsgClass(String.class);
//        assertEquals("2", greeting);
    }
}
