package Domain.Storage;

import Domain.Game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager {

    public void save(Object o, String storageName) throws Exception{
        try{
            FileOutputStream fileOut = new FileOutputStream(new File(storageName));
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(o);
            objOut.close();
            fileOut.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public Object load(Object o, String storageName) throws Exception{
        try{
            FileInputStream fileIn = new FileInputStream(new File(storageName));
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            o = objIn.readObject();
            objIn.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return o;
    }
}
