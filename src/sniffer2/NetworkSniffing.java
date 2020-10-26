package sniffer2;

import java.io.IOException;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UdpPacket;
import org.pcap4j.util.NifSelector;


public class NetworkSniffing {
    static int ethernetCounter=0,ipv4Counter=0,ipv6Counter=0,tcpCounter=0,udpCounter=0,otherCounter=0;
    static int[] result = new int[6];

    public static int[] getCounters() {
        return result;
    }

    public static void setCounters() {
        
        result[0] = tcpCounter;
        result[1] = udpCounter;
        result[2] = ipv4Counter;
        result[3] = ipv6Counter;
        result[4] = ethernetCounter;
        result[5] = otherCounter;
    }

            

  public static void run() throws PcapNativeException, IOException, NotOpenException, InterruptedException {
    
    PcapNetworkInterface nif = null ;
    
    nif = new NifSelector().selectNetworkInterface();
    
    final PcapHandle handle = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 10000);
    
    

    PacketListener listener = new PacketListener() {
      @Override
      public void gotPacket(Packet packet) {
          
          findPacket(packet);
      }
    };
    System.out.println("Va rugam aveti rabdare,procesam!");
    handle.loop(1000, listener);
    setCounters();
  }

  private static void findPacket(Packet packet) {
    System.out.println("Am capturat un pachet!");
    if(packet.get(TcpPacket.class) != null){
        tcpCounter++;
    }
    else if(packet.get(UdpPacket.class) != null){
        udpCounter++;
    }
    else if(packet.get(IpV4Packet.class) != null){
        ipv4Counter++;
    }
    else if(packet.get(IpV6Packet.class) != null){
        ipv6Counter++;
    }
    else if(packet.get(EthernetPacket.class) != null){
        ethernetCounter++;
    }
    else{
        otherCounter++;
    }
  }
  
}

