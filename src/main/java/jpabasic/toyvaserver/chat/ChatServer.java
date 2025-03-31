//package jpabasic.toyvaserver.chat;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class ChatServer {
//    public static void main(String[] args) throws Exception {
//        ServerSocket serverSocket = new ServerSocket(9999);
//        // 9999번 포트에서 기다리는 서버소켓
//
//        List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());
//        //공유 객체에서 스레드에 안전한 리스트
//
//        while (true) {
//            Socket socket = serverSocket.accept();
//            // 여러개의 클라이언트에게 받아서 여러 클라이언트와 통신하기위한 소켓
//            System.out.println(socket + " 접속");
//
//            ChatThread chatThread = new ChatThread(socket, outList);
//            chatThread.start();
//        }
//    }
//}
//
//class ChatThread extends Thread{
//
//    private Socket socket;
//    private List<PrintWriter> outList;
//    private PrintWriter out;
//    private BufferedReader in;
//
//    public ChatThread(Socket socket, List<PrintWriter> outList) {
//        this.socket = socket;
//        this.outList = outList;
//
//        try {
//            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            outList.add(out);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        // 1. socket 으로 부터 읽어들일 수 있는 객체를 얻는다.
//        // 2. socket 에 쓰기 위한 객체를 얻는다. (현재 연결된 클라이언트에게 쓰는 객체)
//    }
//
//    public void run(){
//        // 3. 클라이언트가 보낸 메시지를 읽는다.
//        // 4. 접속한 모든 클라이언트에게 메시지 보낸다. (현재 접속된 모든 클라이언트에게 쓸수있는 객체 필요)
//        String line = null;
//
//        try {
//            while ((line = in.readLine()) != null){
//                for (int i = 0; i < outList.size(); i++){
//                    PrintWriter pr = outList.get(i);
//                    pr.println(line);
//                    pr.flush();
//                }
//
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        } finally {
//            // 만약 연결이 끊어진다면 / while 벗어나면, 공유객체인 outList 에서 out 제거
//            try {
//                outList.remove(out);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//            // 연결이 끊어졌다는 메시지 뿌리기
//            for (int i = 0; i < outList.size(); i++){
//                PrintWriter pr = outList.get(i);
//                pr.println(socket + " 의 접속이 끊어졌습니다.");
//                pr.flush();
//            }
//
//            try {
//                socket.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }
//}
