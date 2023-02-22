/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modbuspal.slave;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modbuspal.main.AddSlaveDialog;
import modbuspal.main.ModbusConst;

/**
 *
 * @author JMC15
 */
public class ModbusSlaveAddress
{

    public static ModbusSlaveAddress parse(String slaveAddress) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    private int rtuAddress = -1;
    private InetAddress ipAddress = null;
    
    
    private static int[] parseIpv4(String s)
    {
        Pattern ipv4Pattern = Pattern.compile("([\\d]{1,3})\\.([\\d]{1,3})\\.([\\d]{1,3})\\.([\\d]{1,3})");
        Matcher m = ipv4Pattern.matcher(s);
        if(m.find()==true)
        {
            String a = m.group(1);
            String b = m.group(2);
            String c = m.group(3);
            String d = m.group(4);
            
            int[] output = new int[4];
            
            output[0] = Integer.parseInt(a);
            output[1] = Integer.parseInt(b);
            output[2] = Integer.parseInt(c);
            output[3] = Integer.parseInt(d);
            
            if( (output[0]>255) || (output[1]>255) || (output[2]>255) || (output[3]>255) )
            {
                return null;
            }
            
            return output;
        }
        return null;
    }
     
    public static List<ModbusSlaveAddress> tryParseIpAddress_1(String s)
    {
        StringBuilder sb = new StringBuilder();
        
        // part of the pattern that finds an ip v4 address
        // (group 1)
        sb.append("([\\d\\.]+)"); 
        
        // part of the pattern that finds the optionnal second ip v4 address.
        // that second ip address defines a range for multiple slave creation.
        // (group 2)
        sb.append("(?:[\\s]*-[\\s]*([\\d\\.]+))?");
        
        // part of the pattern that finds the optionnal rtu address
        // associated with the ip
        // (group 3&4)
        sb.append("(?:[\\s]*\\([\\s]*([\\d]+)[\\s]*\\))?");
        
        //Pattern p = Pattern.compile("([\\d\\.]+)(?:[\\s]*-[\\s]*([\\d\\.]+))?");
        Pattern p = Pattern.compile(sb.toString());
        Matcher m = p.matcher(s.trim());
        if( m.find() )
        {
            int count = m.groupCount();
            String firstIp = m.group(1);
            String lastIp = m.group(2);
            String rtuAddr = m.group(3);
            int slaveAddress = -1;
            
            if( lastIp==null )
            {
                lastIp=firstIp;
            }
            
            if(rtuAddr!=null)
            {
                try
                {
                    slaveAddress = Integer.parseInt(rtuAddr);
                }
                catch(NumberFormatException ex)
                {
                    slaveAddress=-1;
                }
            }
            
            int[] startIp = parseIpv4(firstIp);
            int[] endIp = parseIpv4(lastIp);
            ArrayList<ModbusSlaveAddress> output = new ArrayList<ModbusSlaveAddress>(count);
            for(int a = startIp[0]; a<= endIp[0]; a++ )
            {
                for(int b = startIp[1]; b<= endIp[1]; b++ )
                {
                    for(int c = startIp[2]; c<= endIp[2]; c++ )
                    {
                        for(int d = startIp[3]; d<= endIp[3]; d++ )
                        {   
                            try 
                            {
                                byte[] ip = new byte[]{ (byte)a, (byte)b, (byte)c, (byte)d };
                                InetAddress addr = Inet4Address.getByAddress(ip);
                                ModbusSlaveAddress msa = new ModbusSlaveAddress(addr, slaveAddress);
                                output.add(msa);
                            } 
                            catch (UnknownHostException ex) 
                            {
                                Logger.getLogger(AddSlaveDialog.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }   
                    }   
                } 
            }
            return output;
        }
        return null;
    }
    
    public ModbusSlaveAddress(InetAddress a, int n)
    {
        ipAddress = a;
        
        if( (n<ModbusConst.FIRST_MODBUS_SLAVE) || (n>ModbusConst.LAST_MODBUS_SLAVE) )
        {
            n= -1;
        }
        rtuAddress = n;
    }
    
    public ModbusSlaveAddress(int n)
    {
        if( (n<ModbusConst.FIRST_MODBUS_SLAVE) || (n>ModbusConst.LAST_MODBUS_SLAVE) )
        {
            n= -1;
        }
        
        rtuAddress = n;
        ipAddress = null;
    }
    
    public ModbusSlaveAddress(InetAddress a)
    {
        ipAddress = a;
        rtuAddress = -1;        
    }
    
    public InetAddress getIpAddress()
    {
        return ipAddress;
    }
    
    public void setIpAddress(InetAddress ip)
    {
        ipAddress = ip;
    }
    
    public int getRtuAddress()
    {
        return rtuAddress;
    }
    
    public void setRtuAddress(int n)
    {
        if( (n<ModbusConst.FIRST_MODBUS_SLAVE) || (n>ModbusConst.LAST_MODBUS_SLAVE) )
        {
            n= -1;
        }        
        rtuAddress = n;
    }

    public String toBaseString()
    {
        return String.format("{ rtuAddress : %d, ipAddress : %s }", 
                rtuAddress,
                (ipAddress==null)?"null":ipAddress.toString());
    }
    
    @Override
    public String toString() 
    {
        if( ipAddress!=null )
        {
            if( rtuAddress != -1 )
            {
                return String.format("%s(%d)", ipAddress.getHostAddress(), rtuAddress);
            }
            else
            {
                return String.format("%s", ipAddress.getHostAddress());
            }
        }
        else if( rtuAddress != -1 )
        {
            return String.format("%d", rtuAddress);
        }
        else
        {
            return super.toString();
        }
    }

    @Override
    public int hashCode() 
    {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o) 
    {
        if(o instanceof ModbusSlaveAddress)
        {
            ModbusSlaveAddress other = (ModbusSlaveAddress)o;
            return toString().compareTo(other.toString())==0;
        }
        return false;
    }
    
    
    
    
}
