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
//			String str = "안녕하세요. Hello. \n1234 !@#";
			
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
//				System.out.print( data );
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









//[ JAVA 총 시험 ]
//
//1. 변수의 정의에 대해 서술하시오.
//
//저장되는 값이 변할 수 있어서 변수라고 한다.
//메모리상에 값을 저장하기 위한 공간
//
//
//2. final 예약어의 작성 위치 별 의미를 작성하시오.
//
//- 필드, 변수 : 상수(값 변경 불가)
//- 메서드 : 오버라이딩 불가
//- 클래스 : 상속 불가
//
//
//★3. 오버라이딩 정의와 성립 조건을 작성하시오.
//
//정의 : 부모로부터 상속받은 메서드를 자식이 재정의하는 것
//성립조건 : 반환형, 메서드명, 매개변수 일치
//
//
//★4. 오버로딩에 대한 정의와 성립 조건을 작성하시오.
//
//정의 : 한 클래스 내에 동일한 이름의 메소드를 여러 개 작성하는 기법
//성립조건 : 메서드명 동일, 매개변수가 '개수, 타입, 순서' 중 하나라도 달라야 함.
//
//
//5. 생성자란 무엇인지, 그리고 생성자의 규칙에 대해 모두 적으시오.
//
//- 정의 : new 연산자를 통해 객체 생성 시 필드 초기화 + 생성시 필요한 기능을 수행하는 일종의 메서드
//
//- 규칙 : 생성자명은 클래스명과 일치, 반환형 없음
//	ex. public Student() {}
//
//
//6. 접근 제한자의 모든 종류와 각각의 범위를 작성하시오.
//
//private : 현재 클래스
//
//(default) : 같은 패키지
//
//protected : 같은 패키지 + 상속 관계 자식 클래스
//
//public : 전체
//
//
//7. 기반 스트림과 보조 스트림의 차이점을 서술하시오.
//
//- 기반 스트림 : 생성된 스트림 '객체를 이용해 직접 입출력을 수행'할 수 있는 스트림.
//FileInputStream, FileOutputStream - 파일에 저장된 데이터를 스트림 형태로 입출력하는 Stream 객체
//
//- 보조 스트림 : 스트림 객체로 생성되어도 직접 입출력 할 수 없고, '기반 스트림의 성능을 향상시키거나
//	       새로운 기능 추가하기 위해 사용'
//BufferedInputStream, BufferedOutputStream - 입출력 속도 향상 때문에 사용함
//ex. BufferedInputStream(FileInputStream)
//
//
//8. 컬렉션의 종류와 각 특징을 서술하시오.
//
//- List : 순서가 유지되고(인덱스), 데이터 중복 허용
//- Set : 순서 유지 X, 중복 허용 X
//- Map : K:V 쌍으로 데이터를 저장 / K는 Set의 특징, V는 List의 특징을 가짐.
//
//
//9. 추상 메서드와 추상 클래스의 정의를 작성하시오.
//
//추상메서드 : abstract method : 몸체가 없는 메서드(메서드가 정의 되지 않은)
//추상클래스 : 추상메서드를 포함하고 있는 클래스(추상메서드 0 개 이상 포함) , 추상메서드 + 일반메서드
//
//* 인터페이스 : 추상메서드만 포함
//
//
//10. 아래 메소드의 기능에 대해 자세히 설명하시오.
//
//(a) String.equals(Object anObject) : boolean
//
//- 두 문자열의 값을 비교하여 같으면 true, 아니면 fasle 리턴
//
//(b) ArrayList.add(int index, E e) : void
//
//- ArrayList 객체에서 지정된 index에 요소 e를 추가(삽입)
//
//(c) HashMap.put(K key, V value) : V
//
//- HashMap 객체에 K:V 한쌍을 추가한 후 V(value)를 반환
//
//(d) ArrayList.isEmpty() : boolean
//
//- ArrayList 객체가 비어있으면 true, 아니면 false 반환
//
//(e) HashSet.size() : int
//
//- 객체에 저장된 데이터의 수(크기)를 반환
//
//
//11. 아래 코드에서 에러가 나는 이유를 서술하시오.
//
//try {
//	// 예외 발생 코드가 작성되어 있다고 가정
//} catch(Exception e) {
//
//} catch(IOException e) { // 코드 에러 발생
//
//}
//
//-> 첫번째 catch 문의 매개변수 타입으로 모든 예외의 최상위 부모인 Exception을 작성하게 되면
//    다형성의 업캐스팅이 적용되어 try 구문 내에서 발생하는 모든 예외를 잡아서 처리하게 됨.
//    그래서 다음 catch문으로 예외가 전달되지 않기 때문에 도달할 수 없다는 에러가 발생함.
//
//
//12. 문자열 배열 strArr을 생성하여 5개의 값을 기본 값이 아닌 임의의 값으로 초기화 하는 코드를 작성하시오.
//     (이 때, 초기화 데이터는 응시자 임의로 작성)
//
//1) 
//String[] strArr = new String[5];
//
//strArr[0] = "가가가";
//strArr[1] = "나나나";
//strArr[2] = "다다다";
//strArr[3] = "마마마";
//strArr[4] = "바바바";
//
//2) 
//★String[] strArr = {"사과", "바나나", ... "딸기"};
//
//
//13. 객체지향 4대 특징에 대해 쓰시오.
//
//- 추상화 : 공통된 부분을 추출하고, 불필요한(구체적인) 부분을 제거하는 것
//- 캡슐화 : 필드와 메서드를 하나로 묶고(클래스), 필드에 대한 직접 접근 방지하여 정보 은닉 효과를 얻음.
//- 상속 : 부모 클래스가 가지고 있는 멤버를 자식 클래스에 새로 작성하지 않고 물려주어 자식이
//          자신의 것처럼 사용
//- 다형성 : 하나의 객체가 다양한 형태를 띄는 것, 부모 타입의 참조변수로 자식객체를 참조
//	 ex. List list = new ArrayList();
//
//
//★14. 추상 클래스 B를 A에 상속하려고 한다, 빈칸에 알맞은 코드를 쓰시오.
//
//public class A 'extends' B {}
//extends : 단일상속
//
//* 인터페이스 implements : 다중상속 가능!
//
//
//15. 다음 빈칸에 알맞은 단어를 쓰시오.
//
//1) FileInputStream : 파일을 '바이트 단위'로 '입력' 받는 기반 스트림
//2) FileOutputStream : 파일을 '바이트 단위'로 '출력' 하는 기반 스트림
//3) ObjectOutputStream : 객체를 바이트 단위로 출력 하는 보조 스트림
//4) ObjectInputStream : 객체를 바이트 단위로 입력 받는 보조 스트림
//5) 객체 출력 스트림을 이용해 출력하려는 객체의 클래스는 반드시 Serializable을 상속 받아 '직렬'화 시켜야 하고,
//   객체 입력 스트림을 이용해 객체를 읽어올 경우 자동으로 '역직렬'화 되어 객체로 인식된다.
//
//** 자바는 메모리에 생성된 객체를 파일 / 네트워크 출력할 수 있다.
//객체는 문자가 아니기 때문에 바이트 기반 스트림으로 출력해야한다.
//객체를 출력하기 위해서 바이트로 변경(객체의 데이터:필드값을 일렬로 늘어선 연속적인 모양)해야하는데
//이것을 객체 직렬화라고 한다.
//
//반대로, 파일에 저장되어 있거나, 네트워크에서 전송된 객체를 읽을 수도 있는데, 입력 스트림으로부터
//읽어들인 연속적인 바이트를 객체로 복원 -> 역직렬화라고 한다.
//
//
//
//16. 다음 빈칸에 알맞은 단어를 쓰시오.
//
//Exception은 반드시 예외 처리를 해야 하는 'CheckedException',
//예외처리를 해주지 않아도 되는 'UncheckedException'로 나뉜다.
//
//
//17. throws와 throw의 차이를 쓰시오.
//
//- throws : 현재 메서드에서 발생한 예외로 호출한 메서드로 던지는 예외 처리 구문
//- throw : 예외 강제 발생 구문
//
//18. 다음 코드를 수행했을 때 cc의 출력 결과를 작성하시오.
//
//String cc = "";
//int dd = 50;
//switch(dd) {
//case 50: cc+= 'x'; (break;가 없어 아래까지 수행)
//case 30: cc+='y'; break;
//default: cc+='z';
//}
//
//System.out.println(cc);
//
//-> 답 : xy
//
//
//19. 아래 코드를 for문, switch문으로 바꾸시오.
//String str = "banana";
//
//int i = 0;
//while(i < str.length()) {
//	char ch = str.charAt(i);
//	if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
//		System.out.println(ch + ": 모음입니다.");
//	} else {
//		System.out.println(ch + ": 자음입니다.");
//	}
//	i++;
//}
//
//
//
//-> 답 : 
//String str = "banana";
//
//for(int i = 0; i < str.length(); i++) {
//	char ch = str.charAt(i);
//
//	switch(ch) {
//	case 'a' : case 'e' : case 'i' : case 'o' : case 'u' :
//		System.out.println(ch + ": 모음입니다."); break;
//	default : System.out.println(ch + ": 자음입니다."); break;
//	}
//}
//
//
//★20. 아래 코드를 while문, if문으로 바꾸시오.
//
//for(int i = 1; i <= 20; i++) {
//	switch(i % 5) {
//	case 0 : System.out.println(i + "5의 배수입니다."); break;
//	default : System.out.println(i + "5의 배수가 아닙니다.");
//	}
//}
//
//-> 답 : 
//int i = 1;
//
//While(i <= 20) {
//
//	if(i % 5 == 0) {
//		System.out.println(i + "5의 배수");
//	} else {
//		System.out.println(i + "5의 배수 아님");
//	}
//	i++;
//}
