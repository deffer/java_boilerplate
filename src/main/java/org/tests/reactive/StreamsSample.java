package org.tests.reactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Environment;
import reactor.function.Consumer;
import reactor.rx.Stream;
import reactor.rx.Streams;
import reactor.rx.stream.HotStream;

import java.util.List;

/**
 *
 */
public class StreamsSample {
	private static final Logger log = LoggerFactory.getLogger(StreamsSample.class);

	public static void main(String[] args) throws InterruptedException {
		Environment env = new Environment();
		HotStream<String> helloStream = Streams.defer(env);
		Stream<String> coldStream = Streams.just("NZ", "US", "Kiribati", "Fandango");

        HotStream<String> innerStream = Streams.defer();

		Streams.merge(coldStream, helloStream)
			.filter(s -> !"US".equals(s))
			.map(s -> "Hello " + s + "!")
			.observe(s-> {
				System.out.println("Observing " + s);
				innerStream.broadcastNext("(inner) "+s);
			})
			//.<String>merge()
			.consume(log::info);

		innerStream.consume(log::debug);

		helloStream.broadcastNext("World");

		Thread.sleep(1000);
	}
}
