package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
	
		OutputStream os;
		OutputStreamWriter osw;
		PrintWriter pw;
		
		String str;
		try {
			ServerSocket ss = new ServerSocket(1234);
			Socket socket = ss.accept();
			System.out.println("요청을 기다리는 중");
			System.out.println("접속한 클라이언트 정보: "+socket.getInetAddress().getHostAddress());
			
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			os = socket.getOutputStream();
			osw = new OutputStreamWriter(os);
			pw = new PrintWriter(osw);
			while (true) {
				str = br.readLine();
				System.out.println("받은메시지: "+str);
				if (str == null) {
					System.out.println("연결 종료");
					ss.close();
					socket.close();
					break;
				}
				
				pw.println(str);
				pw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}