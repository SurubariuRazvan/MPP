import network.utils.AbstractServer;
import network.utils.ChatRpcConcurrentServer;
import network.utils.ServerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.*;
import repository.database.*;
import server.AppServicesImpl;
import services.IAppServices;

public class StartRpcServer {
    public static void main(String[] args) {
        int chatServerPort = 55556;

        ApplicationContext context = new ClassPathXmlApplicationContext("App.xml");
        UserRepository userRepo = context.getBean(DatabaseUserRepository.class);
        BookedTripRepository bookedTripRepo = context.getBean(DatabaseBookedTripRepository.class);
        ClientRepository clientRepo = context.getBean(DatabaseClientRepository.class);
        DestinationRepository destinationRepo = context.getBean(DatabaseDestinationRepository.class);
        TripRepository tripRepo = context.getBean(DatabaseTripRepository.class);

        IAppServices chatServerImpl = new AppServicesImpl(userRepo, bookedTripRepo, clientRepo, destinationRepo, tripRepo);
        System.out.println("Starting server on port: " + chatServerPort);
        AbstractServer server = new ChatRpcConcurrentServer(chatServerPort, chatServerImpl);
        try {
            server.start();
        } catch (ServerException e) {
            System.err.println("Error starting the server" + e.getMessage());
        } finally {
            try {
                server.stop();
            } catch (ServerException e) {
                System.err.println("Error stopping server " + e.getMessage());
            }
        }
    }
}
