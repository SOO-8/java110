package bitcamp.java110.cms.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.dao.DuplicationDaoException;
import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.dao.MandatoryValueDaoException;
import bitcamp.java110.cms.domain.Manager;

// 자동생성하게
@Component
public class ManagerFile2Dao implements ManagerDao {

    static String defaultFilename = "data/manager2.dat";
    String filename;
    private List<Manager> list = new ArrayList<>();
        
    @SuppressWarnings("unchecked")
    public ManagerFile2Dao(String filename) {
        this.filename = filename;
        
        File dataFile = new File(filename);
        try (
                FileInputStream in0 = new FileInputStream(dataFile);
                BufferedInputStream in1 = new BufferedInputStream(in0);
                ObjectInputStream in = new ObjectInputStream(in1);
                ){
          //readObject . readLine은 더이상읽을게 없을 경우 null을 return하지만, 얘는 별도의 경로로 호출자에게 자세히 알려준다.
            list = (List<Manager>)in.readObject();  
/*            while(true) {
                try {
                        Manager m = (Manager)in.readObject();     
                        list.add(m);
                }catch(Exception e) {
                    e.printStackTrace();
                    break; 
                    //더이상 읽을게 없어서 못읽으면 빠져나감. null을 return하지 않음.
                }
            }

*/        }catch(Exception e) {
            e.printStackTrace();
        }

        
    }

    public ManagerFile2Dao() {
        this(defaultFilename);
    }
    
    private void save() {
        File dataFile = new File(filename);
        try (
                FileOutputStream out0 = new FileOutputStream(dataFile);
                BufferedOutputStream out1 = new BufferedOutputStream(out0);
                ObjectOutputStream out = new ObjectOutputStream(out1);
                
                ){
                out.writeObject(list);
           /* for(Manager m : list) {
                out.writeObject(m);
            }*/
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
 
    
    // method에 던진다고 했기 때문에, 인터페이스에서도 던진다고 선언해줘야함. 그 인터페이스에서 예외받기 try catch
    public int insert(Manager manager) 
            throws MandatoryValueDaoException, DuplicationDaoException {  //Exception도 가능.
        // 필수 입력 항목이 비었을 때
        if(manager.getName().length() == 0 ||
           manager.getEmail().length() == 0 ||
           manager.getPassword().length() == 0) {
            // 호출자에게 예외 정보를 만들어 던진다.
            throw new MandatoryValueDaoException(); 
        }
        for(Manager item : list) {
            if(item.getEmail().equals(manager.getEmail())) {

                // 호출자에게 예외 정보를 만들어 던진다.
                throw new DuplicationDaoException(); 
            }
        }
        list.add(manager);
        save();
        return 1;   //저장
    }
    
    public List<Manager> findAll(){
        return list;
    }
    
    public Manager findByEmail(String email) {
        for(Manager item : list) {
            if(item.getEmail().equals(email)) {
                return item;
            }
        }
        return null;
    }
    
    public int delete(String email) {
        for(Manager item : list) {
            if(item.getEmail().equals(email)) {
                list.remove(item);
                save();
                return 1;
            }
        }
        return 0;
            
    }
    
    
    
    
    
    
}