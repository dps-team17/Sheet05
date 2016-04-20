#Distributed Systems Lab Homework 5
##RMI Introduction (8 points)
The aim of this assignment is to become familiar with the basic concepts of RMI by implementing a simple client/server application.

###Assignments:

1. Implement a computation server

Implement a server offering the simple computation services of HW 02 (+, , * and lucas) plus some additional features using RMI. 
    
    a. Implement the Server features from HW 02 and a test Client (2 point)
    
    b. Determine experimentally whether your server is capable of handling multiple client requests simultaneously from 
    the same client / from multiple clients. (1 point)
    
    c. Add an additional computation method with a generic computational task (specified via an interface) as an argument. 
    The task should be send to the server to be computed remotely. The operation should return the result of the computation. 
    Provide 23 example tasks. Also try whether tasks implemented / compiled after the startup of the server can be processed 
    by the server. (2 point)
    
    d. Add an additional operation “Deep Thought” which given any question (as a string) returns (after an eternity) the 
    answer “The answer to your question <question> is probably 42”. Since this operation takes so much time, the client 
    should not be blocked until the server has finished its task. Implement an asynchronous method invocation by adding 
    an additional argument to the new method (within the remote interface) accepting a (remote) object reference for a 
    callback. The server forwards the answer to this callback reference upon completion. The client should handle the 
    result and shut down gracefully (without calling System.exit(…)). (3 points)

*Tip: for part d) the object passed as the callback argument has to be exported by the client; for a graceful shutdown, 
the object has to be unexported;*

*Note: parts b), c) and d) can be processed independently*