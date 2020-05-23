# Getting Started

### What is build
a server that accepts commands from multiple browser clients. In response, the server will push the response into a queue to which the clients are subscribed.


### STOMP
* Simple Text Oriented Message Protocol is used for message-oriented middleware communications. It provides an interoperable wire format that allows STOMP clients to talk with any message broker supporting the protocol.
* The service will accept messages(ClientCommand.java) that contain a 'command'
* The service will process it by creating a (ServerReply.java) and will publish that reply on a queue to which the client is subscribed. 

### CommandController.java
* This controller receives the client messages and send a reply message back.
* The @MessageMapping annotation ensures that, if a message is sent to the /listen destination, the getUCommand(ClientCommand clientCommand) method is called
* The return value is broadcasted to all subscribers of /topic/communication, as specified in the @SendTo annotation.

### WebSocketConfig.java: Configure Spring for WebSocket and STOMP messaging
The following guides illustrate how to use some features concretely:


## References
* [A Guide to the Java API for WebSocket](https://www.baeldung.com/java-websockets)
* [Creating a WebSocket Server with the Java API for WebSockets](https://www.nexmo.com/blog/2018/10/22/create-websocket-server-java-api-dr)
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)
