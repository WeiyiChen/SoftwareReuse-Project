package edu.tongji.reuse.teameleven.authentication.transport;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.holder.stub.GetMissedMsgsIntf;
import edu.tongji.reuse.teameleven.processor.stub.ContactsCtrlIntf;
import edu.tongji.reuse.teameleven.record.stub.MonitorControllerIntf;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by daidongyang on 5/30/16.
 */
public class RefsInAuth {
    // ContactsCtrlIntf
    private static ContactsCtrlIntf contactsCtrl;

    public static synchronized void createContactsCtrl(){
        if(null == contactsCtrl){
            try {
//                Registry registry = LocateRegistry.getRegistry("127.0.0.1", 15820);
//                contactsCtrl = (ContactsCtrlIntf)registry.lookup("contactsCtrl");
                String processorIp = ConfigCtrl.getConfig().getProcessorIP();
                int processorRegPort = ConfigCtrl.getConfig().getProcessorRegistryPort();
                String contactsCtrlRegKey = ConfigCtrl.getConfig().getProcessorContactsCtrlRegKey();
                Registry registry = LocateRegistry.getRegistry(processorIp, processorRegPort);
                contactsCtrl = (ContactsCtrlIntf)registry.lookup(contactsCtrlRegKey);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static ContactsCtrlIntf getContactsCtrl(){
        if(null == contactsCtrl){
            createContactsCtrl();
        }
        return contactsCtrl;
    }

    // GetMissedMsgsIntf
    private static GetMissedMsgsIntf getMissedMsgs;

    public static synchronized void createGetMissedMsgs(){
        if(null == getMissedMsgs){
            try{
//                Registry registry = LocateRegistry.getRegistry("127.0.0.1", 15840);
//                getMissedMsgs = (GetMissedMsgsIntf)registry.lookup("getMissedMsgsIntf");
//                System.out.println(getMissedMsgs);
                String holderIp = ConfigCtrl.getConfig().getHolderIP();
                int holderRegPort = ConfigCtrl.getConfig().getHolderRegistryPort();
                String getMissedMsgsRegKey = ConfigCtrl.getConfig().getHolderGetMissedMsgsRegKey();
                Registry registry = LocateRegistry.getRegistry(holderIp, holderRegPort);
                getMissedMsgs = (GetMissedMsgsIntf) registry.lookup(getMissedMsgsRegKey);
            }catch(RemoteException e){
                //todo remove System.out after debug
                e.printStackTrace(System.out);
            }catch (NotBoundException e){
                e.printStackTrace(System.out);
            }
        }
    }

    public static GetMissedMsgsIntf getGetMissedMsgs(){
        if(null == getMissedMsgs){
            createGetMissedMsgs();
        }
        return getMissedMsgs;
    }

    private static MonitorControllerIntf monitorControllerRef;

    public synchronized static void createMonitorControllerRef(){
        if(null == monitorControllerRef){

            try {
                String loggerIp = ConfigCtrl.getConfig().getLoggerIp();
                int loggerRegPort = ConfigCtrl.getConfig().getLoggerRegistryPort();
                Registry registry = LocateRegistry.getRegistry(loggerIp, loggerRegPort);

                String monotoCtrlrRegKey = ConfigCtrl.getConfig().getLoggerMonitorCtrlRegKey();
                monitorControllerRef = (MonitorControllerIntf)registry.lookup(monotoCtrlrRegKey);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static MonitorControllerIntf getMonitorControllerRef(){
        if(null == monitorControllerRef){
            createMonitorControllerRef();
        }
        return monitorControllerRef;
    }
}
