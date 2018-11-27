import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.rmi.*;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.PrintWriter;

public class MediaServer {
    public static void main(String args[]) {

        int RMIPortNum = 7777;
        int portNum = 7777;

        int mediaKey;


        try {
            // code for port number value to be supplied

            System.out.println("SERVER READY");
            PrintWriter writer = new PrintWriter("ContentFile.txt", "UTF-8");

            MediaImpl exportedObj = new MediaImpl();
            startRegistry(RMIPortNum);
            // register the object under the name “some”
            String registryURL = "rmi://localhost:" + portNum + "/some";
            Naming.rebind(registryURL, exportedObj);

            mediaKey = exportedObj.hashCode();

            writer.println("File: " + mediaKey);
            writer.close();


        }// end try

        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    } // end main

    // This method starts a RMI registry on the local host, if it
// does not already exists at the specified port number.
    private static void startRegistry(int RMIPortNum) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();
            // The above call will throw an exception
            // if the registry does not already exist
        } catch (RemoteException ex) {
            // No valid registry at that port.
            System.out.println("RMI registry cannot be located at port " + RMIPortNum);
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
            System.out.println("RMI registry created at port " + RMIPortNum);
        }
    } // end startRegistry
}