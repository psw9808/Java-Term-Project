import java.util.*;
import java.lang.*;
import java.io.*;

class Kingdom{ // 메인 클래스 Kingdom 
	public static void main(String[]args){
		
		Scanner userinput = new Scanner(System.in);
		Systems systems = new Systems();
		Statesbase statesbase = new Statesbase();
		States states = new States();
		Events events = new Events();
		
		while(true){
			states.year=1;
			systems.start();
			
			System.out.println("<나라의 상태를 설정합니다.>");  // 처음 나라의 상태를 설정할때 메세지 출력
			try{
			Thread.sleep(1000);
			}catch(Exception e){} //딜레이
			System.out.println();
			states.setstates();
			
			while(true){
				states.showstates();
				if(states.printstates()==true){
					events.occurevent();
					states.changestates(events.inputs(),events.random1);
				}
				else break;
			}
			
			if(systems.end(states.year)==false){
				System.out.printf("\n <게임을 종료합니다.>\n");
				break;
			}
		}
	}
}

class Systems{ // 시스템 클래스 (시작/끝) 
	
	void start(){ // 시작 (파일 입출력,예외처리)
		try{
			File storyfile = new File("story.txt");
			Scanner story = new Scanner(storyfile);
			
			System.out.println();
			while(story.hasNext()){
				System.out.println(story.nextLine());
			}
			System.out.println();
			story.close();
		}catch(FileNotFoundException e1){
			System.out.println("\n <스토리 생략> (스토리 파일 없음)\n");
		}
	}
	
	boolean end(int year){ // 끝 (예외처리)
		Scanner userinput = new Scanner(System.in);
		int input;
		int loop=0;
		
		System.out.printf("[통치기간 : %d년]\n",year);
		while(loop==0){
			try{
				System.out.print("\n 	다음 왕으로 계속 진행하시겟습니까? ( 1: 네 / 2: 아니오 )  : ");
				input = userinput.nextInt();
				if (input!=1 && input!=2) throw new NotRightNumberException();
				else if(input == 1) {
					
					loop = 1;
				}
				else {
					
					loop = 2;
				}
			}catch(NotRightNumberException e2){
				System.out.println("\n <1과 2중에 하나를 입력하세요.>");
			}catch(InputMismatchException e3){
				System.out.println("\n <입력은 아라비아 숫자 1과 2중 하나로 하여야 합니다.>");
				userinput = new Scanner(System.in);
			}
		}
		if(loop==1) return true;
		else return false;
	}
}

class Statesbase{ // State 클래스의 상속을 위한 Super클래스 
	void ending(){
		System.out.println();
		System.out.println("[엔딩 발생]");
		System.out.println();
	}
}

class States extends Statesbase{ // State 클래스 
	public int year;
	public int army;
	public int money;
	public int people;
	public int church;
	
	
	void setstates(){ //맨 처음 나라의 상태를 랜덤설정
	army = (int)(Math.random()*41)+30;
	money = (int)(Math.random()*41)+30;
	people = (int)(Math.random()*41)+30;
	church = (int)(Math.random()*41)+30;
	}
	
	void showstates(){ // 나라의 상태 및 년도를 출력
		
		if(army<0) army=0;
		if(army>100) army=100;
		if(money<0) money=0;
		if(money>100) money=100;
		if(people<0) people=0;
		if(people>100) people=100;
		if(church<0) church=0;
		if(church>100) church=100;
		
		System.out.printf("[Year %d]\n",year);
		System.out.printf("<나라의 상태>\n");
		System.out.printf(" [↖] 군사  %4d : ",army);	// 나라의 4가지 상태를 기호로 표현
		for(int i=0;i<army/5;i++){						// 나라의 상태를 시각적으로 표현하는 기능을 추가
			System.out.printf("■");
		}
		for(int j=0;j<20-army/5;j++){
			System.out.printf("□");
		}
		System.out.println();
		System.out.printf(" [＄] 경제  %4d : ",money);
		for(int i=0;i<money/5;i++){
			System.out.printf("■");
		}
		for(int j=0;j<20-money/5;j++){
			System.out.printf("□");
		}
		System.out.println();
		System.out.printf(" [웃] 민심  %4d : ",people);
		for(int i=0;i<people/5;i++){
			System.out.printf("■");
		}
		for(int j=0;j<20-people/5;j++){
			System.out.printf("□");
		}
		System.out.println();
		System.out.printf(" [†] 종교  %4d : ",church);
		for(int i=0;i<church/5;i++){
			System.out.printf("■");
		}
		for(int j=0;j<20-church/5;j++){
			System.out.printf("□");
		}
		System.out.println();
	}
	
	
	boolean printstates(){ // 상태 수치를 체크하고 계속 진행 or 엔딩 출력
		
		boolean result=true;

		if ((army>0&&army<100)&&(money>0&&money<100)&&(people>00&&people<100)&&(church>0&&church<100)){
			year++;
		}
		
		else if(army<=0){
			result=false;
			super.ending();
			System.out.println("군사 수치가 0이 되었습니다.");
			System.out.println("장군 : 침략군이 성문까지 왔습니다! 우리에게는 이미 저들을 막을 만한 힘이 없습니다!");
			System.out.println("왕은 남아 있던 군대를 이끌고 마지막으로 저항하려 했지만 왕좌 앞 계단에서 적병에게 살해당했다.");
		}
		
		else if(army>=100){
			result=false;
			super.ending();
			System.out.println("군사 수치가 100이 되었습니다.");
			System.out.println("장군 : 쿠데타가 일어났습니다! 전권을 제게 넘기십시오!");
			System.out.println("모반을 일으킨 병사들은 왕을 성 안에서 가장 높은 탑에 유폐시키고 백골이 될 때까지 방치했다.");
		}
		
		else if(money<=0){
			result=false;
			super.ending();
			System.out.println("경제 수치가 0이 되었습니다.");
			System.out.println("나라가 완전히 망했습니다. 상인과 제후가 지배하고 있습니다.");
		}
		
		else if(money>=100){
			result=false;
			super.ending();
			System.out.println("경제 수치가 100이 되었습니다.");
			System.out.println("고급 식재료를 잔뜩 매입해 매일 호화로운 연회를 열다가 나라가 완전히 망했습니다.");
		}
		
		else if(people<=0){
			result=false;
			super.ending();
			System.out.println("민심 수치가 0이 되었습니다.");
			System.out.println("굶주림에 처한 백성들이 폭동을 일으켰습니다. 더는 막을 수 없습니다!");
			System.out.println("성은 폭도에게 점령당해 신하들도 뿔뿔이 흩어졌다. 왕에게 남은 건 비둘기 몇 마리 뿐이었다.");
		}
		
		else if(people>=100){
			result=false;
			super.ending();
			System.out.println("민심 수치가 100이 되었습니다.");
			System.out.println("도시에서 대규모 폭동이 일어나 폭도들이 성문을 부수려 하고 있습니다!");
			System.out.println("폭동은 나날이 악화되어 마침내 왕의 침실이 불에 휩싸였다. 왕은 창문에서 뛰어내려 목숨을 잃었다.");
		}
		
		else if(church<=0){
			result=false;
			super.ending();
			System.out.println("종교 수치가 0이 되었습니다.");
			System.out.println("교회의 권위가 약화되어, 이미 백성의 폭동을 억제할 수 없습니다. 폐하도 어서 도망치세요!");
			System.out.println("왕은 도망가려 했지만 도중에 이교도인 폭도에게 습격당해 사망했다.");
		}
		else if(church>=100){
			result=false;
			super.ending();
			System.out.println("종교 수치가 100이 되었습니다.");
			System.out.println("교황군이 우리 국가를 통치하고 있다. 바로 지상의 낙원이야!");
			System.out.println("새로운 과두정치 대표들은 왕을 꼭두각시로 남기려 했다. 그러나 교회는 왕을 산 채로 화형에 처하기로 결정했다.");
		}
		
		return result;
	}
	
	void changestates(boolean input,int random1) { // 상태를 변화시켜주고 그 결과를 출력 
		
		String symbol,temp;
		int value,count,loop=0;
		
		System.out.println();
		try{
			File eventfile = new File("event.txt");
			Scanner event = new Scanner(eventfile);
				
			for(count=0;count<random1;count++){
				event.nextLine();
			}
			
			if(input==true){
				
				while(true){
					temp=event.next();
					if(temp.endsWith("]")) break;
				}

				while(true){
					temp=event.next();
					System.out.print(temp+" ");
				if(temp.endsWith("]")) break;
				}
			
			
			}
			else{
				while(true){
					temp=event.next();
					if(temp.endsWith(",")) break;
				}
				
				while(true){
					temp=event.next();
					System.out.print(temp+" ");
				if(temp.endsWith("]")) break;
				}
			}
			System.out.println();
			
				
			while(loop==0){
				symbol = event.next();
			
				switch(symbol){
					case "↖":
						value = event.nextInt() + (int)(Math.random()*20)-10;
						army = army + value;
						System.out.print(symbol +" "+value+"  ");
						break;
					
					case "＄":
						value = event.nextInt() + (int)(Math.random()*20)-10;
						money = money + value;
						System.out.print(symbol +" "+value+"  ");
						break;
					
					case "웃":
						value = event.nextInt() + (int)(Math.random()*20)-10;
						people = people + value;
						System.out.print(symbol +" "+value+"  ");
						break;
						
					case "†":
						value = event.nextInt() + (int)(Math.random()*20)-10;
						church = church + value;
						System.out.print(symbol +" "+value+"  ");
						break;
				
					case ",":
						loop=1;
						break;
					
					default:
				}
			}
			System.out.println();
			System.out.println();
			event.close();
		}catch(FileNotFoundException e1){
			System.out.println("\n <프로그램 종료> (이벤트 파일 없음)\n");
			System.exit(0);
		}
	}
}

class Events{ // 이벤트 클래스 
	String event,result,icon,temp;
	int start = 0,end = 0,eventnum=0,count=0;
	static int random1=0;
	
	void occurevent(){ // 랜덤 이벤트 발생 
		try{
			Thread.sleep(1000);
			}catch(Exception e){} //딜레이
		System.out.println("\n<이벤트 발생>\n");
		try{
			Thread.sleep(1000);
			}catch(Exception e){} //딜레이
			
		eventnum=0;
		
		try{
			File eventfile = new File("event.txt");
			Scanner event = new Scanner(eventfile);
			
			while(event.hasNext()){
				if(event.next().equals("*"))
				eventnum++;
			}
			event.close();
			event = new Scanner(eventfile);
			
			random1 = (int)(Math.random()*eventnum);
			
			for(count=0;count<random1;count++){
				event.nextLine();
			}
			
			System.out.printf("[이벤트 NO.%d] \n",random1+1);  //이벤트 번호를 나타내는 기능을 추가
			
			
			while(true){
				temp=event.next();
				if(temp.startsWith("[")) break;
			}
			System.out.print(temp);
			
			while(true){
				temp=event.next();
				if(temp.startsWith("[")) break;
				System.out.print(" "+temp);
			}
			
			System.out.println();
			
		}catch(FileNotFoundException e1){
			System.out.println("\n <프로그램 종료> (이벤트 파일 없음)\n");  // event.txt 파일이 없으면 종료
			System.exit(0);
		}
	}
	
	boolean inputs(){ // 유저 입력 
		Scanner userinput = new Scanner(System.in);
		int input,loop=0;
		
		while(loop==0){
			try{
				System.out.printf("\n\t왕 ( 1: 그리 하거라 / 2: 아니된다 )  : ");
				input = userinput.nextInt();
				if (input!=1 && input!=2) throw new NotRightNumberException();
				else if(input == 1) {
					loop = 1;
					
				}
				else {
					loop = 2;
					
				}
			}catch(NotRightNumberException e2){
				System.out.println("\n <1과 2중에 하나를 입력하세요.>");
			}catch(InputMismatchException e3){
				System.out.println("\n <입력은 아라비아 숫자 1과 2중 하나로 하여야 합니다.>");
				userinput = new Scanner(System.in);
			}
		}
		
		if(loop==1) return true;
		else return false;
	}
	
}

class NotRightNumberException extends Exception{ //예외처리 
	NotRightNumberException(){
		super("1또는 2가 아닌 숫자 예외");
	}
}