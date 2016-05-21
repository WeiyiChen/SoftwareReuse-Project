package edu.tongji.reuse.teameleven.client.intf;

/**
 * prevent database injection
 * @author Dai
 *
 */
public interface IAntiInjection {
	boolean sqlSecurityCheck(String usr, String pwd);
}
