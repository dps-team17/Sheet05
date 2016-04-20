package team17.sheet05;

import java.rmi.Remote;
import java.rmi.RemoteException;


interface IDeepThoughtCallback extends Remote {

    void answer();
}
