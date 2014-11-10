package org.tests.reactive;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Environment;
import reactor.function.Consumer;
import reactor.io.encoding.StandardCodecs;
import reactor.net.NetChannel;
import reactor.net.netty.tcp.NettyTcpServer;
import reactor.net.tcp.TcpServer;
import reactor.net.tcp.spec.TcpServerSpec;

/**
 *
 */
class TcpServerSample {
	public static void main(String[] args) {
		Environment env = new Environment();

        // create a spec using the Netty-based server
		TcpServer<String, String> server = new TcpServerSpec<String, String>(NettyTcpServer.class)
			.env(env)
			.codec(StandardCodecs.LINE_FEED_CODEC)
			.consume(conn -> {
                // for each connection, process incoming data
                conn.in().consume(new Consumer<String>() {
                    public void accept(String line) {
                        System.out.print(line);
                        conn.send("Hello "+line); // echo this line back
                    }
                });
            })
			.get();

		server.start();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
