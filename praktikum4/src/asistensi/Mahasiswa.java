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
import java.io.Serializable;

public class Mahasiswa implements Serializable
{
    private String nim;
    private String nama;
    private String asal;
    private String kelas;

    public Mahasiswa(String nim, String nama, String asal, String kelas) 
    {
        this.nim = nim;
        this.nama = nama;
        this.asal = asal;
        this.kelas = kelas;
    }

    public String getNim() 
    {
        return nim;
    }

    public void setNim(String nim) 
    {
        this.nim = nim;
    }

    public String getNama() 
    {
        return nama;
    }

    public void setNama(String nama) 
    {
        this.nama = nama;
    }

    public String getAsal() 
    {
        return asal;
    }

    public void setAsal(String asal) 
    {
        this.asal = asal;
    }

    public String getKelas() 
    {
        return kelas;
    }

    public void setKelas(String kelas) 
    {
        this.kelas = kelas;
    }
    
    @Override
    public String toString()
    {
        return nim + " " + nama + " " +asal + " " + kelas;
    }
}
