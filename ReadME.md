# Getting Started

### What is build
A server that accepts commands from multiple browser clients. In response, the server will push the response into a common queue backed by the websocket broker topic to which the clients are subscribed.
The application also handles sessionId and user info through localstorage

## Phase 1
### Handling Session ID and User's Info and active session time
Since the info for session is specific and should not be broadcasted to the subscriber topic, all info is stored in the browser's localstorage
* On 'Connect' click, a random UUID is generated and stored as JSESSION in localstorage, localstorage also stores the start as timestamp
* On 'Disconnect' click the localstorage is cleared and message is printed for active time.
* A separate javascript class Store is used to handle the localstorage.
* The 'Hi, I am <name>' will be also treated same as 'I am <name>' or 'Hola, I am <name>' using regex
* Look for phase1.png for sample run screen for phase 1

## Phase 2,3,4,5
### Handling commands entered by the customer.
Since all these phases are based on the client server communication, and need bidirectional communications so we have used
The WebSocket API through STOMP
The STOMP protocol is commonly used inside a web socket when a web app needs to support bidirectional communication with a web server.

#### STOMP
* Simple Text Oriented Message Protocol is used for message-oriented middleware communications. It provides an interoperable wire format that allows STOMP clients to talk with any message broker supporting the protocol.
* The service will accept messages(ClientCommand.java) that contain a 'command'
* The service will process it by creating a (ServerReply.java) and will publish that reply on a topic to which the client is subscribed. 
##### CommandController.java
* This controller receives the client messages and send a reply message back.
* The @MessageMapping annotation ensures that, if a message is sent to the /listen destination, the getUCommand(ClientCommand clientCommand) method is called
* The return value is broadcasted to all subscribers of /topic/communication, as specified in the @SendTo annotation.
##### WebSocketConfig
Configured Spring for WebSocket and STOMP messaging
##### How all commands are handled
For every command a CommandStrategy is created, depending on the command entered by the client, system identifies the entered strategy and execute the method accordingly
It will be easy to add more commands in future by using that strategy.

## Phase 2
### ADD/REMOVE NODE EDGE: These commands are for building a directed grap
Node Tag are case insensitive, X and x are considered same node
* Look for phase2.png for sample run screen for phase 2

## Phase 3
### Finds the shortest (weighted) path from node <X> to node <Y>.
* Look for phase3.png for sample run screen for phase 3

## Phase 4
### NOT IMPLEMENTED BECAUSE OF TIME

## Phases 5 & 6
### open multiple sessions at the same time, all adding and removing nodes and edges.
* Look for phase5.png for sample run screen for phase 5
* different UUIDs for two clients

### HighLights
All info messages are configurable
All commands are case insensitive and also support multiple whitespaces between the command (If by mistake someone enters the extra space)

### what is not implemented
phase 4 for closer than is not implemented
The automatic inactivity check of 30 seconds is not implemented

## References
* [A Guide to the Java API for WebSocket](https://www.baeldung.com/java-websockets)
* [Creating a WebSocket Server with the Java API for WebSockets](https://www.nexmo.com/blog/2018/10/22/create-websocket-server-java-api-dr)
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)
