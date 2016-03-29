package client.intf;

/**
 * prevent database injection
 * @author Dai
 *
 */
public interface IAntiInjection {
	boolean sqlSecurityCheck(String usr, String pwd);
}
