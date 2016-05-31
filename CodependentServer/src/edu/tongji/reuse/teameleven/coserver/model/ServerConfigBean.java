package edu.tongji.reuse.teameleven.coserver.model;

/**
 * Created by daidongyang on 5/31/16.
 */
public class ServerConfigBean {
    private int maxMessagesPerLogin;
    private int saveCycle;
    private int maxMessagesPerSecond;
    private int zipCycle;
    private int reZipCycle;
    private String authIP;
    private int authListenPort;
    private int authRegistryPort;
    private String handlersIP;
    private int handlersListenPort;
    private int handlersRegistryPort;
    private int handlersInvokePort;
    private String handlersRegKey;
    private String processorIP;
    private int processorRegistryPort;
    private int processorContactsCtrlInvokePort;
    private String processorContactsCtrlRegKey;
    private int processorProcessMsgInvokePort;
    private String processorProcessMsgRegKey;
    private String holderIP;
    private int holderRegistryPort;
    private int holderGetMissedMsgsInvokePort;
    private String holderGetMissedMsgsRegKey;
    private int holderMissedMsgsInvokePort;
    private String holderMissedMsgsRegKey;
    private String loggerIp;
    private int loggerRegistryPort;
    private int loggerMonitorCtrlInvokePort;
    private String loggerMonitorCtrlRegKey;

    public int getMaxMessagesPerLogin() {
        return maxMessagesPerLogin;
    }

    public void setMaxMessagesPerLogin(int maxMessagesPerLogin) {
        this.maxMessagesPerLogin = maxMessagesPerLogin;
    }

    public int getSaveCycle() {
        return saveCycle;
    }

    public void setSaveCycle(int saveCycle) {
        this.saveCycle = saveCycle;
    }

    public int getMaxMessagesPerSecond() {
        return maxMessagesPerSecond;
    }

    public void setMaxMessagesPerSecond(int maxMessagesPerSecond) {
        this.maxMessagesPerSecond = maxMessagesPerSecond;
    }

    public int getZipCycle() {
        return zipCycle;
    }

    public void setZipCycle(int zipCycle) {
        this.zipCycle = zipCycle;
    }

    public int getReZipCycle() {
        return reZipCycle;
    }

    public void setReZipCycle(int reZipCycle) {
        this.reZipCycle = reZipCycle;
    }

    public String getAuthIP() {
        return authIP;
    }

    public void setAuthIP(String authIP) {
        this.authIP = authIP;
    }

    public int getAuthListenPort() {
        return authListenPort;
    }

    public void setAuthListenPort(int authListenPort) {
        this.authListenPort = authListenPort;
    }

    public int getAuthRegistryPort() {
        return authRegistryPort;
    }

    public void setAuthRegistryPort(int authRegistryPort) {
        this.authRegistryPort = authRegistryPort;
    }

    public String getHandlersIP() {
        return handlersIP;
    }

    public void setHandlersIP(String handlersIP) {
        this.handlersIP = handlersIP;
    }

    public int getHandlersListenPort() {
        return handlersListenPort;
    }

    public void setHandlersListenPort(int handlersListenPort) {
        this.handlersListenPort = handlersListenPort;
    }

    public int getHandlersRegistryPort() {
        return handlersRegistryPort;
    }

    public void setHandlersRegistryPort(int handlersRegistryPort) {
        this.handlersRegistryPort = handlersRegistryPort;
    }

    public int getHandlersInvokePort() {
        return handlersInvokePort;
    }

    public void setHandlersInvokePort(int handlersInvokePort) {
        this.handlersInvokePort = handlersInvokePort;
    }

    public String getHandlersRegKey() {
        return handlersRegKey;
    }

    public void setHandlersRegKey(String handlersRegKey) {
        this.handlersRegKey = handlersRegKey;
    }

    public String getProcessorIP() {
        return processorIP;
    }

    public void setProcessorIP(String processorIP) {
        this.processorIP = processorIP;
    }

    public int getProcessorRegistryPort() {
        return processorRegistryPort;
    }

    public void setProcessorRegistryPort(int processorRegistryPort) {
        this.processorRegistryPort = processorRegistryPort;
    }

    public int getProcessorContactsCtrlInvokePort() {
        return processorContactsCtrlInvokePort;
    }

    public void setProcessorContactsCtrlInvokePort(int processorContactsCtrlInvokePort) {
        this.processorContactsCtrlInvokePort = processorContactsCtrlInvokePort;
    }

    public String getProcessorContactsCtrlRegKey() {
        return processorContactsCtrlRegKey;
    }

    public void setProcessorContactsCtrlRegKey(String processorContactsCtrlRegKey) {
        this.processorContactsCtrlRegKey = processorContactsCtrlRegKey;
    }

    public int getProcessorProcessMsgInvokePort() {
        return processorProcessMsgInvokePort;
    }

    public void setProcessorProcessMsgInvokePort(int processorProcessMsgInvokePort) {
        this.processorProcessMsgInvokePort = processorProcessMsgInvokePort;
    }

    public String getProcessorProcessMsgRegKey() {
        return processorProcessMsgRegKey;
    }

    public void setProcessorProcessMsgRegKey(String processorProcessMsgRegKey) {
        this.processorProcessMsgRegKey = processorProcessMsgRegKey;
    }

    public String getHolderIP() {
        return holderIP;
    }

    public void setHolderIP(String holderIP) {
        this.holderIP = holderIP;
    }

    public int getHolderRegistryPort() {
        return holderRegistryPort;
    }

    public void setHolderRegistryPort(int holderRegistryPort) {
        this.holderRegistryPort = holderRegistryPort;
    }

    public int getHolderGetMissedMsgsInvokePort() {
        return holderGetMissedMsgsInvokePort;
    }

    public void setHolderGetMissedMsgsInvokePort(int holderGetMissedMsgsInvokePort) {
        this.holderGetMissedMsgsInvokePort = holderGetMissedMsgsInvokePort;
    }

    public String getHolderGetMissedMsgsRegKey() {
        return holderGetMissedMsgsRegKey;
    }

    public void setHolderGetMissedMsgsRegKey(String holderGetMissedMsgsRegKey) {
        this.holderGetMissedMsgsRegKey = holderGetMissedMsgsRegKey;
    }

    public int getHolderMissedMsgsInvokePort() {
        return holderMissedMsgsInvokePort;
    }

    public void setHolderMissedMsgsInvokePort(int holderMissedMsgsInvokePort) {
        this.holderMissedMsgsInvokePort = holderMissedMsgsInvokePort;
    }

    public String getHolderMissedMsgsRegKey() {
        return holderMissedMsgsRegKey;
    }

    public void setHolderMissedMsgsRegKey(String holderMissedMsgsRegKey) {
        this.holderMissedMsgsRegKey = holderMissedMsgsRegKey;
    }

    public String getLoggerIp() {
        return loggerIp;
    }

    public void setLoggerIp(String loggerIp) {
        this.loggerIp = loggerIp;
    }

    public int getLoggerRegistryPort() {
        return loggerRegistryPort;
    }

    public void setLoggerRegistryPort(int loggerRegistryPort) {
        this.loggerRegistryPort = loggerRegistryPort;
    }

    public int getLoggerMonitorCtrlInvokePort() {
        return loggerMonitorCtrlInvokePort;
    }

    public void setLoggerMonitorCtrlInvokePort(int loggerMonitorCtrlInvokePort) {
        this.loggerMonitorCtrlInvokePort = loggerMonitorCtrlInvokePort;
    }

    public String getLoggerMonitorCtrlRegKey() {
        return loggerMonitorCtrlRegKey;
    }

    public void setLoggerMonitorCtrlRegKey(String loggerMonitorCtrlRegKey) {
        this.loggerMonitorCtrlRegKey = loggerMonitorCtrlRegKey;
    }

    @Override
    public String toString() {
        return "ServerConfigBean{" +
                "maxMessagesPerLogin=" + maxMessagesPerLogin +
                ", saveCycle=" + saveCycle +
                ", maxMessagesPerSecond=" + maxMessagesPerSecond +
                ", zipCycle=" + zipCycle +
                ", reZipCycle=" + reZipCycle +
                ", authIP='" + authIP + '\'' +
                ", authListenPort=" + authListenPort +
                ", authRegistryPort=" + authRegistryPort +
                ", handlersIP='" + handlersIP + '\'' +
                ", handlersListenPort=" + handlersListenPort +
                ", handlersRegistryPort=" + handlersRegistryPort +
                ", handlersInvokePort=" + handlersInvokePort +
                ", handlersRegKey='" + handlersRegKey + '\'' +
                ", processorIP='" + processorIP + '\'' +
                ", processorRegistryPort=" + processorRegistryPort +
                ", processorContactsCtrlInvokePort=" + processorContactsCtrlInvokePort +
                ", processorContactsCtrlRegKey='" + processorContactsCtrlRegKey + '\'' +
                ", processorProcessMsgInvokePort=" + processorProcessMsgInvokePort +
                ", processorProcessMsgRegKey='" + processorProcessMsgRegKey + '\'' +
                ", holderIP='" + holderIP + '\'' +
                ", holderRegistryPort=" + holderRegistryPort +
                ", holderGetMissedMsgsInvokePort=" + holderGetMissedMsgsInvokePort +
                ", holderGetMissedMsgsRegKey='" + holderGetMissedMsgsRegKey + '\'' +
                ", holderMissedMsgsInvokePort=" + holderMissedMsgsInvokePort +
                ", holderMissedMsgsRegKey='" + holderMissedMsgsRegKey + '\'' +
                ", loggerIp='" + loggerIp + '\'' +
                ", loggerRegistryPort=" + loggerRegistryPort +
                ", loggerMonitorCtrlInvokePort=" + loggerMonitorCtrlInvokePort +
                ", loggerMonitorCtrlRegKey='" + loggerMonitorCtrlRegKey + '\'' +
                '}';
    }
}
