import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.*;
import repository.database.*;
import server.AppServicesImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

public class StartRpcServer {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("App.xml");
            UserRepository userRepo = context.getBean(DatabaseUserRepository.class);
            BookedTripRepository bookedTripRepo = context.getBean(DatabaseBookedTripRepository.class);
            ClientRepository clientRepo = context.getBean(DatabaseClientRepository.class);
            DestinationRepository destinationRepo = context.getBean(DatabaseDestinationRepository.class);
            TripRepository tripRepo = context.getBean(DatabaseTripRepository.class);

            var serverImpl = new AppServicesImpl(userRepo, bookedTripRepo, clientRepo, destinationRepo, tripRepo);

            Server server = NettyServerBuilder.forAddress(new InetSocketAddress("localhost", 50050))
                    .executor(MoreExecutors.directExecutor())
                    .addService(serverImpl).build()
                    .start();
            System.out.println("Server has started");
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
