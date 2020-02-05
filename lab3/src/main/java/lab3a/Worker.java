package lab3a;

import lab3a.IListPol;

import java.io.*;


public class Worker extends Thread {
    int st;
    int finish;
    IListPol list;
    public  Worker(int start, int finish, IListPol listV1){
            this.st=start;
            this.finish=finish;
            this.list=listV1;
    }
    @Override
    public void run(){
        for(int i=st;i<finish;i++) {
            File file = new File("C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab3\\src\\main\\resources\\dir\\file" + i + ".txt");
            BufferedReader reader;
            int[] arrayInt = new int[0];
            String line;
            try {
                reader = new BufferedReader(new FileReader(file));
                line = reader.readLine();
                while (line != null) {
                    String[] array;
                    array = line.split(" ");
                   // System.out.println(array[0] + " " + array[1]);
                    list.insert(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
                    line = reader.readLine();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
