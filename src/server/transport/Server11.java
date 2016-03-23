package server.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Server11 {

	private Selector selector = null;
	static final int port = 4993;
	private Charset charset = Charset.forName("UTF-8");

	public Server11() throws IOException {
		selector = Selector.open();
		ServerSocketChannel server = ServerSocketChannel.open();
		server.bind(new InetSocketAddress(port));
		// �������ķ�ʽ
		server.configureBlocking(false);
		// ע�ᵽѡ�����ϣ�����Ϊ����״̬
		server.register(selector, SelectionKey.OP_ACCEPT);

		System.out.println("Server is listening now...");

		// �˴���������
		while (selector.select() > 0) {

			for (SelectionKey sk : selector.selectedKeys()) {
				selector.selectedKeys().remove(sk);
				// �������Կͻ��˵���������
				if (sk.isAcceptable()) {
					SocketChannel sc = server.accept();
					// ������ģʽ
					sc.configureBlocking(false);
					// ע��ѡ������������Ϊ��ȡģʽ
					sc.register(selector, SelectionKey.OP_READ);
					// ���˶�Ӧ��channel����Ϊ׼�����������ͻ�������
					sk.interestOps(SelectionKey.OP_ACCEPT);
					System.out.println("Server is listening from client :"
							+ sc.getRemoteAddress());
				}
				// �������Կͻ��˵����ݶ�ȡ����
				if (sk.isReadable()) {
					// ���ظ�SelectionKey��Ӧ�� Channel��������������Ҫ��ȡ
					SocketChannel sc = (SocketChannel) sk.channel();
					ByteBuffer buff = ByteBuffer.allocate(1024);
					StringBuilder content = new StringBuilder();
					try {
						while (sc.read(buff) > 0) {
							buff.flip();
							content.append(charset.decode(buff));

						}
						System.out.println("Server is listening from client "
								+ sc.getRemoteAddress() + " data rev is: "
								+ content);
						// ���˶�Ӧ��channel����Ϊ׼����һ�ν�������
						sk.interestOps(SelectionKey.OP_READ);
					} catch (IOException io) {
						sk.cancel();
						if (sk.channel() != null) {
							sk.channel().close();
						}
					}
					if (content.length() > 0) {
						// �㲥���ݵ����е�SocketChannel��
						for (SelectionKey key : selector.keys()) {
							Channel targetchannel = key.channel();
							if (targetchannel instanceof SocketChannel) {
								SocketChannel dest = (SocketChannel) targetchannel;
								dest.write(charset.encode(content.toString()));
							}
						}
					}

				}
			}
		}
	}
}
