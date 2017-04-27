import Enumerators.UbicationResults;
import Exceptions.RecordNotFoundException;
import Models.Unit;
import Models.Location;
import java.net.*;
import java.io.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import Frames.FrameLocation;

public class Main {
    static ServerSocket ss;
    static Socket s;
    static InputStreamReader isr;
    static BufferedReader br;
    static PrintStream ps;
    
    public static void main(String[] args) {
        final FrameLocation fm = new FrameLocation();
        fm.show();

        Thread socketThread = new Thread(new Runnable(){
        @Override
            public void run(){
                try{
                    System.out.println("Openning Socket ...");//status
                    ss = new ServerSocket(9000); // socket port
                    System.out.println("Socket opened ...");// status again
                    //keep reading
                    boolean frame = true;
                    String idUnit;
                    
                    
                    while(true){
                        s = ss.accept(); //read data
                        isr = new InputStreamReader(s.getInputStream());
                        br = new BufferedReader(isr);// reader of buffer
                        String data = br.readLine();
                      
                        
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try
                        {
                            JSONObject object = new JSONObject(data);
                            idUnit = object.getString("IdUnit");
                            JSONObject locations = object.getJSONObject("Locations");
                            String latitude = locations.getString("Latitude");
                            String longitude = locations.getString("Longitude");
                            Date date = new Date();
                            if(frame == true)
                            {
                                frame = false;
                                fm.textUnit.setText(idUnit);
                                fm.showLocationInfo();
                            }
                            fm.addRow(latitude, longitude, dateFormat.format(date));
                            try{
                                //timestamp
                                Unit u = new Unit(idUnit);
                                UbicationResults r = u.add_ubication(latitude, longitude, dateFormat.format(date));
                                System.out.println(r.getMessage());
                                for(Location l : u.getLocations()){
                                    System.out.println(l);
                                }
                               // System.out.println(data + "\n");//show data
                             }
                             catch(RecordNotFoundException ex){
                                 System.out.println(ex.getMessage());}

                        }
                        catch(JSONException ex){
                             System.out.println(ex);
                        }
                        //System.out.println(data + "\n");//show data
                        //send a knowledge to client
                        if (data != null){
                            ps = new PrintStream(s.getOutputStream());
                            ps.println("Data received ...");
                            
                            //if(idUnit == "")
                            //{
                            //    fm.textUnit.setText(idUnit);
                            //}
                            //fm.showLocationInfo();
                        }
                    }
                    
                }
                catch(IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        });
        //start socket
        socketThread.start();
    }
}
