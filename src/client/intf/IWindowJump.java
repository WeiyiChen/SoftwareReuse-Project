package client.intf;

/**
 * client: jump from window to another
 * @author Dai
 *
 */
public interface IWindowJump {
	/**
	 * jump from window to another
	 * @param from
	 * @param to
	 * @return true - succeed to jump, false - fail to jump
	 */
	boolean jump(IClientWindow from, IClientWindow to);
}
