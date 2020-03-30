package network.utils;

import network.rpcprotocol.AppClientRpcReflectionWorker;
import services.IAppServices;

import java.net.Socket;


public class ChatRpcConcurrentServer extends AbsConcurrentServer {
    private IAppServices chatServer;

    public ChatRpcConcurrentServer(int port, IAppServices chatServer) {
        super(port);
        this.chatServer = chatServer;
        System.out.println("Chat- ChatRpcConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        AppClientRpcReflectionWorker worker = new AppClientRpcReflectionWorker(chatServer, client);

        return new Thread(worker);
    }

    @Override
    public void stop() {
        System.out.println("Stopping services ...");
    }
}
