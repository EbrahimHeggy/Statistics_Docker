import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Main {
    public static void main(String[] args) throws IOException {
        Path paths = Paths.get("app/data/batch");
        File file = new File(paths+"/database.txt");
        Scanner myReader = new Scanner(file);
        int numberOfUsers = 0;
        HashMap<String,Integer> mp = new HashMap<String, Integer>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            numberOfUsers++;
            int index = 0;
            for (int i = 0 ;i<data.length();i++){
                if(data.charAt(i)==';'){
                    index = i;
                }
            }
            String courses = data.substring(index+1,data.length());
            String course= "";
            for (int i = 0 ;i<courses.length();i++){
                if(courses.charAt(i)==','){
                    if(mp.get(course)==null){
                        mp.put(course,1);
                    }
                    else{
                        mp.put(course,mp.get(course)+1);
                    }
                    course="";
                }
                else{
                    course+=courses.charAt(i);
                }
            }
            if(!course.isEmpty()){
                if(mp.get(course)==null){
                    mp.put(course,1);
                }
                else{
                    mp.put(course,mp.get(course)+1);
                }
            }
        }
        System.out.println("Number of users: "+(numberOfUsers));
        for (String i : mp.keySet()) {
            System.out.println("Number of students registered in " + i + " course: " + mp.get(i));
        }
        myReader.close();


        File[] arr = paths.toFile().listFiles();
        int len  = arr.length -1 ;
        System.out.println("Number of batch files: "+len);
        int cnt = 0;
        for(File i :arr){
            if(i.toString().contains("_verified")){
                cnt++;
            }
        }
        System.out.println("Number of verified batch files: "+cnt);
    }
}
