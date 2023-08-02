package edu.kh.io.model.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOService {
	
	// IO
	
	// Input (입력) : 외부 -> 내부로 데이터를 들여오는 것
	// Output(출력) : 내부 -> 외부로 데이터를 내보내는 것
	
	// Stream : 입/출력 통로 역할(데이터가 흘러가는 통로)
	//			기본적으로 Stream은 단방향
	
	// 1) 파일 출력 (내부 == 프로그램, 외부 == 파일)
	public void output1() {
		
		// 바이트 기반 스트림 이용
		FileOutputStream fos = null;
		
		// FileOutputStream 객체 생성 시
		// FileNotFoundException / IOException 예외가 발생할 가능성이 있음 -> 예외처리 필요
		try {
			
			fos = new FileOutputStream("test1.txt");
			// 현재 프로그램에서 test1.txt 파일로 출력하는 통로 객체 생성
			
			// 파일에 "Hello" 내보내기
			String str = "Hello";
			
			for(int i = 0; i < str.length(); i++) {
				
				// "Hello"를 한문자씩 끊어서 파일로 출력하기
				fos.write(str.charAt(i));
				
				// write()는 IOException을 발생시킬 가능성이 있다.
			}
			
			
		} catch(IOException e) { // IOException 이 FileNotFoundException 의 조상 -> 다형성
			System.out.println("예외 발생");
			e.printStackTrace(); // 예외 추적
			
		} finally {
			
			// 예외가 발생 하든 말든 무조건 수행
			
			// 사용한 스트림 자원 반환(통로 없앰) --> 필수 작성!!!
			// 프로그램 메모리 관리 차원에서 항상 다쓰면 끊어줌
			// -> 작성 안하면 문제점으로 꼽을 수 있다!
			try {
			fos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	// 2) 파일 출력
	// 문자 기반 스트림
	public void output2() {
		
		FileWriter fw = null;
		// 프로그램 -> 파일로 쓰는 문자 기반 스트림
		
		try {
			
			fw = new FileWriter("test1.txt"); // 외부 파일과 연결하는 스트림 객체 생성
			
			String str = "안녕하세요. Hello. 1234 !@#";
			
			fw.write(str);
			// 실행 했는데 출력이 안되고 있다.
			// --> 한 줄을 통째로 보내기 위해서 "버퍼"를 이용하는데
			// -> 아직 버퍼에 담겨있음 ! 이걸 강제로 밀어넣어서 버퍼를 비워야함.
			
			// close() 구문을 수행하면 통로에 남아있는 내용을 모두 내보내고
			// 통로를 없앤다!
			
			
		} catch(IOException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				fw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	// 3) 파일 입력 : 외부(파일) -> 내부(프로그램)으로 읽어오기
	public void input1() {
		
		FileInputStream fis = null;
		// 파일 -> 프로그램으로 읽어오는 바이트 기반 스트림
		
		try {
			
			fis = new FileInputStream("test1.txt");
			
			// FileInputStream 은 1byte 씩만 읽어올 수 있다.
			
			while(true) {
				
				int data = fis.read(); // 다음 1byte를 읽어오는데 정수형임.
									   // 다음 내용이 없으면 -1 반환
				
				if(data == -1) { // 다음 내용 없음 -> 종료
					break;
				}
				
				// 반복종료가 안됐으면 char로 강제 형변환하여 문자로 출력
				System.out.print( (char)data );
				
			}
			
		} catch(IOException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				fis.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	// 4) 파일 입력 (문자 기반 스트림)
	public void input2() {
		
		FileReader fr = null;
		
		try {
			
			fr = new FileReader("test1.txt"); // 파일로부터 읽어오는 통로 객체 생성
			
			while(true) {
				
				int data = fr.read(); // 다음 한문자를 읽어옴. 없으면 -1
				
				if(data == -1) {
					break;
				}
				
				System.out.print( (char)data );
			}
			
			
		} catch(IOException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				fr.close();
			} catch(IOException e) {
				e.printStackTrace();
				
			}
			
		}
	}
	

}
