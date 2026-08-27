package ex1_file;

import java.io.File;

public class Ex1_File {
    public static void main(String[] args) {
        
        //IO(Input / Output)
        //IO는 입출력 스트림을 의미한다
        //스트림이란 데이터를 입출력하기 위한 방법
        //JVM에서 콘솔로 값을 내보내면 Outout이라고 부르고
        //콘솔의 값을 JVM에서 읽을 땐 Input
        //영어는 a -> 1byte
        //한글은 안 -> 2byte
        //String path = "c:\\java\\test.txt"; -> 역슬래시 두개 사용하는 이런 방법도 있다~

        //입출력 스트림의 종류
        //1) byte기반의 스트림 : ...Stream 구조의 클래스들

        //2)char기반의 스트림 : ...Reader, ...Writer 구조의 클래스들

        String path = "c:/java/test.txt";
        //File은 IO가 가능한 형태의 객체이다
        File f = new File(path); //위에 주소까지 접근 권한이 생긴다
        System.out.println(f.length());

        //생성한 file객체가 파일구조라면 if문 안으로 들어간다
        if (f.isFile()) {
            System.out.println("용량 : " + f.length()+ "byte");
        }


    }
}
