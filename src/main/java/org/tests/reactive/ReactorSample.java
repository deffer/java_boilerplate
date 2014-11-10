package org.tests.reactive;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;
import reactor.event.selector.Selectors;
import reactor.function.Consumer;

/**
 */
public class ReactorSample {
	public static void main(String[] args) throws InterruptedException {


		Environment env = new Environment();
		Reactor reactor = Reactors.reactor().env(env).get();

		// returns a Registration object that can be used to pause and resume the channel
		reactor.on(Selectors.regex(".*INFO.*"), new Consumer<Event<String>>() {
			@Override
			public void accept(Event<String> event) {
				System.out.println(event.getData());
			}
		});

		String value = "date INFO message";

		reactor.notify(value, Event.wrap(value));
		reactor.notify(value, Event.wrap(value));
		reactor.notify(value, Event.wrap(value));
		reactor.notify(value, Event.wrap(value));

		env.shutdown();

		System.out.println("finished");
	}
}
