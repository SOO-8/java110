package bitcamp.java110.cms.control.student;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.dao.StudentDao;

@Component
public class StudentDeleteController {

    StudentDao studentDao;
     
    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @RequestMapping("student/delete")
    public void deleteByNo(Scanner keyIn) {
        System.out.print("삭제할 학생의 번호? ");
        int no = Integer.parseInt(keyIn.nextLine());
               
        if(studentDao.delete(no)>0) {
            System.out.println("삭제하였습니다.");
        } else {
            System.out.println("해당 번호의 학생이 존재하지 않습니다.");
        }
    }

    
    
}
