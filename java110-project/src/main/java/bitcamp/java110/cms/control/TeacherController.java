package bitcamp.java110.cms.control;

import java.util.Scanner;

//import bitcamp.java110.cms.control.TeacherController.Teacher;
import bitcamp.java110.cms.domain.Member;

public class TeacherController {
    
    public static Scanner keyIn = new Scanner(System.in);
    
    static class Teacher extends Member{
        protected String tel;
        protected int pay;
        protected String subjects;
        
        public String getTel() {
            return tel;
        }
        public void setTel(String tel) {
            this.tel = tel;
        }
        public int getPay() {
            return pay;
        }
        public void setPay(int pay) {
            this.pay = pay;
        }
        public String getSubjects() {
            return subjects;
        }
        public void setSubjects(String subjects) {
            this.subjects = subjects;
        }
        
        
    }
    
    static Teacher[] teachers = new Teacher[100];
    
    static int teacherIndex = 0;
    
    
    public static void serviceTeacherMenu() {
        while(true) {
            System.out.println("강사 관리>");
            String command = keyIn.nextLine();              
        
        if(command.equals("list")) {
            printTeachers();
        }else if(command.equals("add")) {
            inputTeachers();
        }else if(command.equals("delete")) {
            deleteTeacher();
        }else if(command.equals("detail")) {
            detailTeacher();
        }else if(command.equals("quit")) {
            break;
        }else {
            System.out.println("유효하지 않은 명령입니다.");
        }
        
        
         }
    }
     
    
    
    private static void printTeachers() {
        int count = 0;
        for(Teacher s : teachers) {
            if(count++ == teacherIndex)
                break;
            int no = count;
        System.out.printf("%d: %s, %s, %s, %s, %d, %s\n",
                --no,
                s.getName(),
                s.getEmail(), 
                s.getPassword(),
                s.getTel(),
                s.getPay(),
                s.getSubjects()); 
        }
    }
    
    
    private static void inputTeachers() {

        while(true)
        {
//            members[index] = new Member();
            Teacher m = new Teacher();
            
            System.out.print("이름? ");
            m.setName(keyIn.nextLine());
//            memgers[index].setName(keyIn.nextLine());
//            names[index] = keyIn.nextLine();   //사용자가 한줄을 입력햇을 때 비로소 nextLine은 string을 return한다.
                                            //호출하자마자 return하는것 X
            System.out.print("이메일? ");
            m.setEmail(keyIn.nextLine());
        
            System.out.print("암호? ");
            m.setPassword(keyIn.nextLine());

            System.out.print("전화? ");
            m.setTel(keyIn.nextLine());

            System.out.print("시급? ");
            m.setPay(Integer.parseInt(keyIn.nextLine()));

            System.out.print("강의과목? (예 : 자바, C, C++) ");
            m.setSubjects(keyIn.nextLine());
            
            if(teacherIndex == teachers.length) {
                increaseStorage();
            }           
            
            teachers[teacherIndex++] = m;   //index에 넣고 , index값을 이후에 증가시킴.
//            index++;
            
            System.out.println("계속 하시겠습니까? (Y/n)"); //둘중 하나가 대문자라면 그 값이 기본값!(enter만해도 Y로 인식)
            String answer = keyIn.nextLine();
            if(answer.toLowerCase().equals("n"))    //return값에 대해 equals메서드 호출
                break;
        }
        
    }

    private static void increaseStorage() {
        Teacher[] newList = new Teacher[teachers.length+3];
        for(int i=0; i<teachers.length; i++) {
            newList[i] = teachers[i];
        }
        teachers = newList;
    }

    
    private static void deleteTeacher()
    {
        System.out.print("삭제할 번호? ");
        int no = Integer.parseInt(keyIn.nextLine());

        if(no < 0 || no >= teacherIndex) {
            System.out.println("무효한 번호 입니다.");
            return;
        }

        for(int i=no; i<teacherIndex-1; i++){
            teachers[i] = teachers[i+1];
        }
        teacherIndex--;
        
        System.out.println("삭제하였습니다.");
        
/*            if(no >= 0 && no < TeacherIndex) {
            for(int i=no; i<TeacherIndex-2; i++){
                Teachers[i] = Teachers[i+1];
            }
            TeacherIndex--;
        }
*/            
    }
    
    private static void detailTeacher() {
        System.out.print("조회할 번호? ");
        int no = Integer.parseInt(keyIn.nextLine());
        
        if(no<0 || no>= teacherIndex) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        
        System.out.printf("이름: %s\n", teachers[no].getName());
        System.out.printf("이메일: %s\n", teachers[no].getEmail());
        System.out.printf("암호: %s\n", teachers[no].getPassword());
        System.out.printf("전화: %s\n", teachers[no].getTel());
        System.out.printf("시급: %d\n", teachers[no].getPay());
        System.out.printf("강의 과목: %s\n", teachers[no].getSubjects());
        
    }
    
    static {    //초기화
        Teacher s = new Teacher();
        s.setName("a");
        teachers[teacherIndex++] = s;
        
        s = new Teacher();
        s.setName("b");
        teachers[teacherIndex++] = s;

        s = new Teacher();
        s.setName("c");
        teachers[teacherIndex++] = s;

        s = new Teacher();
        s.setName("d");
        teachers[teacherIndex++] = s;

        s = new Teacher();
        s.setName("e");
        teachers[teacherIndex++] = s;

    }

    

}
