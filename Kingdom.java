import java.util.*;
import java.lang.*;
import java.io.*;

class Kingdom{ // ���� Ŭ���� Kingdom 
	public static void main(String[]args){
		
		Scanner userinput = new Scanner(System.in);
		Systems systems = new Systems();
		Statesbase statesbase = new Statesbase();
		States states = new States();
		Events events = new Events();
		
		while(true){
			states.year=1;
			systems.start();
			
			System.out.println("<������ ���¸� �����մϴ�.>");  // ó�� ������ ���¸� �����Ҷ� �޼��� ���
			try{
			Thread.sleep(1000);
			}catch(Exception e){} //������
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
				System.out.printf("\n <������ �����մϴ�.>\n");
				break;
			}
		}
	}
}

class Systems{ // �ý��� Ŭ���� (����/��) 
	
	void start(){ // ���� (���� �����,����ó��)
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
			System.out.println("\n <���丮 ����> (���丮 ���� ����)\n");
		}
	}
	
	boolean end(int year){ // �� (����ó��)
		Scanner userinput = new Scanner(System.in);
		int input;
		int loop=0;
		
		System.out.printf("[��ġ�Ⱓ : %d��]\n",year);
		while(loop==0){
			try{
				System.out.print("\n 	���� ������ ��� �����Ͻðٽ��ϱ�? ( 1: �� / 2: �ƴϿ� )  : ");
				input = userinput.nextInt();
				if (input!=1 && input!=2) throw new NotRightNumberException();
				else if(input == 1) {
					
					loop = 1;
				}
				else {
					
					loop = 2;
				}
			}catch(NotRightNumberException e2){
				System.out.println("\n <1�� 2�߿� �ϳ��� �Է��ϼ���.>");
			}catch(InputMismatchException e3){
				System.out.println("\n <�Է��� �ƶ��� ���� 1�� 2�� �ϳ��� �Ͽ��� �մϴ�.>");
				userinput = new Scanner(System.in);
			}
		}
		if(loop==1) return true;
		else return false;
	}
}

class Statesbase{ // State Ŭ������ ����� ���� SuperŬ���� 
	void ending(){
		System.out.println();
		System.out.println("[���� �߻�]");
		System.out.println();
	}
}

class States extends Statesbase{ // State Ŭ���� 
	public int year;
	public int army;
	public int money;
	public int people;
	public int church;
	
	
	void setstates(){ //�� ó�� ������ ���¸� ��������
	army = (int)(Math.random()*41)+30;
	money = (int)(Math.random()*41)+30;
	people = (int)(Math.random()*41)+30;
	church = (int)(Math.random()*41)+30;
	}
	
	void showstates(){ // ������ ���� �� �⵵�� ���
		
		if(army<0) army=0;
		if(army>100) army=100;
		if(money<0) money=0;
		if(money>100) money=100;
		if(people<0) people=0;
		if(people>100) people=100;
		if(church<0) church=0;
		if(church>100) church=100;
		
		System.out.printf("[Year %d]\n",year);
		System.out.printf("<������ ����>\n");
		System.out.printf(" [��] ����  %4d : ",army);	// ������ 4���� ���¸� ��ȣ�� ǥ��
		for(int i=0;i<army/5;i++){						// ������ ���¸� �ð������� ǥ���ϴ� ����� �߰�
			System.out.printf("��");
		}
		for(int j=0;j<20-army/5;j++){
			System.out.printf("��");
		}
		System.out.println();
		System.out.printf(" [��] ����  %4d : ",money);
		for(int i=0;i<money/5;i++){
			System.out.printf("��");
		}
		for(int j=0;j<20-money/5;j++){
			System.out.printf("��");
		}
		System.out.println();
		System.out.printf(" [��] �ν�  %4d : ",people);
		for(int i=0;i<people/5;i++){
			System.out.printf("��");
		}
		for(int j=0;j<20-people/5;j++){
			System.out.printf("��");
		}
		System.out.println();
		System.out.printf(" [��] ����  %4d : ",church);
		for(int i=0;i<church/5;i++){
			System.out.printf("��");
		}
		for(int j=0;j<20-church/5;j++){
			System.out.printf("��");
		}
		System.out.println();
	}
	
	
	boolean printstates(){ // ���� ��ġ�� üũ�ϰ� ��� ���� or ���� ���
		
		boolean result=true;

		if ((army>0&&army<100)&&(money>0&&money<100)&&(people>00&&people<100)&&(church>0&&church<100)){
			year++;
		}
		
		else if(army<=0){
			result=false;
			super.ending();
			System.out.println("���� ��ġ�� 0�� �Ǿ����ϴ�.");
			System.out.println("�屺 : ħ������ �������� �Խ��ϴ�! �츮���Դ� �̹� ������ ���� ���� ���� �����ϴ�!");
			System.out.println("���� ���� �ִ� ���븦 �̲��� ���������� �����Ϸ� ������ ���� �� ��ܿ��� �������� ���ش��ߴ�.");
		}
		
		else if(army>=100){
			result=false;
			super.ending();
			System.out.println("���� ��ġ�� 100�� �Ǿ����ϴ�.");
			System.out.println("�屺 : ��Ÿ�� �Ͼ���ϴ�! ������ ���� �ѱ�ʽÿ�!");
			System.out.println("����� ����Ų ������� ���� �� �ȿ��� ���� ���� ž�� �����Ű�� ����� �� ������ ��ġ�ߴ�.");
		}
		
		else if(money<=0){
			result=false;
			super.ending();
			System.out.println("���� ��ġ�� 0�� �Ǿ����ϴ�.");
			System.out.println("���� ������ ���߽��ϴ�. ���ΰ� ���İ� �����ϰ� �ֽ��ϴ�.");
		}
		
		else if(money>=100){
			result=false;
			super.ending();
			System.out.println("���� ��ġ�� 100�� �Ǿ����ϴ�.");
			System.out.println("��� ����Ḧ �ܶ� ������ ���� ȣȭ�ο� ��ȸ�� ���ٰ� ���� ������ ���߽��ϴ�.");
		}
		
		else if(people<=0){
			result=false;
			super.ending();
			System.out.println("�ν� ��ġ�� 0�� �Ǿ����ϴ�.");
			System.out.println("���ָ��� ó�� �鼺���� ������ �����׽��ϴ�. ���� ���� �� �����ϴ�!");
			System.out.println("���� �������� ���ɴ��� ���ϵ鵵 �Ի��� �������. �տ��� ���� �� ��ѱ� �� ���� ���̾���.");
		}
		
		else if(people>=100){
			result=false;
			super.ending();
			System.out.println("�ν� ��ġ�� 100�� �Ǿ����ϴ�.");
			System.out.println("���ÿ��� ��Ը� ������ �Ͼ �������� ������ �μ��� �ϰ� �ֽ��ϴ�!");
			System.out.println("������ ������ ��ȭ�Ǿ� ��ħ�� ���� ħ���� �ҿ� �۽ο���. ���� â������ �پ�� ����� �Ҿ���.");
		}
		
		else if(church<=0){
			result=false;
			super.ending();
			System.out.println("���� ��ġ�� 0�� �Ǿ����ϴ�.");
			System.out.println("��ȸ�� ������ ��ȭ�Ǿ�, �̹� �鼺�� ������ ������ �� �����ϴ�. ���ϵ� � ����ġ����!");
			System.out.println("���� �������� ������ ���߿� �̱����� �������� ���ݴ��� ����ߴ�.");
		}
		else if(church>=100){
			result=false;
			super.ending();
			System.out.println("���� ��ġ�� 100�� �Ǿ����ϴ�.");
			System.out.println("��Ȳ���� �츮 ������ ��ġ�ϰ� �ִ�. �ٷ� ������ �����̾�!");
			System.out.println("���ο� ������ġ ��ǥ���� ���� ���ΰ��÷� ����� �ߴ�. �׷��� ��ȸ�� ���� �� ä�� ȭ���� ó�ϱ�� �����ߴ�.");
		}
		
		return result;
	}
	
	void changestates(boolean input,int random1) { // ���¸� ��ȭ�����ְ� �� ����� ��� 
		
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
					case "��":
						value = event.nextInt() + (int)(Math.random()*20)-10;
						army = army + value;
						System.out.print(symbol +" "+value+"  ");
						break;
					
					case "��":
						value = event.nextInt() + (int)(Math.random()*20)-10;
						money = money + value;
						System.out.print(symbol +" "+value+"  ");
						break;
					
					case "��":
						value = event.nextInt() + (int)(Math.random()*20)-10;
						people = people + value;
						System.out.print(symbol +" "+value+"  ");
						break;
						
					case "��":
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
			System.out.println("\n <���α׷� ����> (�̺�Ʈ ���� ����)\n");
			System.exit(0);
		}
	}
}

class Events{ // �̺�Ʈ Ŭ���� 
	String event,result,icon,temp;
	int start = 0,end = 0,eventnum=0,count=0;
	static int random1=0;
	
	void occurevent(){ // ���� �̺�Ʈ �߻� 
		try{
			Thread.sleep(1000);
			}catch(Exception e){} //������
		System.out.println("\n<�̺�Ʈ �߻�>\n");
		try{
			Thread.sleep(1000);
			}catch(Exception e){} //������
			
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
			
			System.out.printf("[�̺�Ʈ NO.%d] \n",random1+1);  //�̺�Ʈ ��ȣ�� ��Ÿ���� ����� �߰�
			
			
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
			System.out.println("\n <���α׷� ����> (�̺�Ʈ ���� ����)\n");  // event.txt ������ ������ ����
			System.exit(0);
		}
	}
	
	boolean inputs(){ // ���� �Է� 
		Scanner userinput = new Scanner(System.in);
		int input,loop=0;
		
		while(loop==0){
			try{
				System.out.printf("\n\t�� ( 1: �׸� �ϰŶ� / 2: �ƴϵȴ� )  : ");
				input = userinput.nextInt();
				if (input!=1 && input!=2) throw new NotRightNumberException();
				else if(input == 1) {
					loop = 1;
					
				}
				else {
					loop = 2;
					
				}
			}catch(NotRightNumberException e2){
				System.out.println("\n <1�� 2�߿� �ϳ��� �Է��ϼ���.>");
			}catch(InputMismatchException e3){
				System.out.println("\n <�Է��� �ƶ��� ���� 1�� 2�� �ϳ��� �Ͽ��� �մϴ�.>");
				userinput = new Scanner(System.in);
			}
		}
		
		if(loop==1) return true;
		else return false;
	}
	
}

class NotRightNumberException extends Exception{ //����ó�� 
	NotRightNumberException(){
		super("1�Ǵ� 2�� �ƴ� ���� ����");
	}
}