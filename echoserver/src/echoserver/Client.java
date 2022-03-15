package echoserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket socket;
		InputStreamReader keyboard = new InputStreamReader(System.in);
		BufferedReader input = null;
	
		OutputStream os;
		OutputStreamWriter osw;
		PrintWriter pw;
	
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		
		String str;
		try {
			socket = new Socket("localhost",1234);
			System.out.println("서버와 연결되었습니다.");
			input = new BufferedReader(keyboard);
			
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			os = socket.getOutputStream();
			osw = new OutputStreamWriter(os);
			pw = new PrintWriter(osw);
			
			while (true) {
				System.out.println("메시지 입력(exit 작성 시 종료): ");
				str = input.readLine();
				System.out.println(">>"+str);
				if ("exit".equals(str)) {
					socket.close();
					break;
				}
				
				pw.print(str);
				pw.flush();
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}