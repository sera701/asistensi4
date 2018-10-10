/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistensi;

/**
 *
 * @author reyna
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Server 
{
    public static final int SERVICE_PORT=8;
    public static final int BUFSIZE=256;
    private DatagramSocket socket;
    private String message, response;
    
    public Server()
    {
        try 
        {
            socket = new DatagramSocket(SERVICE_PORT);
            System.out.println("Server Active on port "+socket.getLocalPort());
        } catch (SocketException ex) 
        {
            System.out.println(ex);
        } 
    }
    
    public void serviceClients(List<Mahasiswa> mhs) throws UnknownHostException
    {
        byte[]buffer=new byte[BUFSIZE];
        byte[]send=new byte[BUFSIZE];
        for(;;)
        {
            try 
            {
                DatagramPacket packet = new DatagramPacket(buffer, BUFSIZE);
                socket.receive(packet);
                System.out.println("Packet received from "+packet.getAddress()+":"+packet.getPort()+" of length "+packet.getLength());
                ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData(),
                        0, packet.getLength());
                BufferedReader reader = new BufferedReader(new InputStreamReader(bin));
                message = reader.readLine();
                String[] data= message.split("-");
                if(data[0].equals("4"))
                {
                    response="exit";
                }else if(data[0].equals("1"))
                {
                    mhs.add(new Mahasiswa(data[1], data[2], data[3], data[4]));
                    response="Data mahasiswa berhasil ditambahkan";
                }else if(data[0].equals("2"))
                {
                    mhs.set(Integer.parseInt(data[5]), new Mahasiswa(data[1], data[2], data[3], data[4]));
                    response="Data mahasiswa berhasil diubah";
                }else if(data[0].equals("3"))
                {
                    mhs.remove(Integer.parseInt(data[1]));
                    response="Data mahasiswa berhasil dihapus";
                }
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                PrintStream pout = new PrintStream(bout);
                pout.print(response);
                byte[] barray = bout.toByteArray();
                DatagramPacket sendpacket = new DatagramPacket(barray, barray.length, packet.getAddress(), packet.getPort());
                socket.send(sendpacket);
                if(response.equals("exit"))
                {
                    return;
                }
            } catch (IOException ex) 
            {
                System.out.println(ex);
            }
        }
    }
    
    public static void main(String[] args) throws UnknownHostException 
    {
        Server s= new Server();
        List<Mahasiswa> mhs = new ArrayList<Mahasiswa>();
        s.serviceClients(mhs);
        Simpan ss = new Simpan();
        ss.serialize(mhs, "DataMahasiswa.ser");
    }    
}
