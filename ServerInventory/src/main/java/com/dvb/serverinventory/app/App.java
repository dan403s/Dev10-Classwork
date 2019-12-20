/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.serverinventory.app;

import com.dvb.serverinventory.dao.ServerInventoryDao;
import com.dvb.serverinventory.dao.ServerInventoryDaoInMemImpl;
import com.dvb.serverinventory.dto.Server;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class App {

    public static void main(String[] args) {

        ServerInventoryDao dao = new ServerInventoryDaoInMemImpl();

        // create several Server objects to manipulate
        Server myServer = new Server("web01");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("Dell");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("01/01/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addServer(myServer);

        myServer = new Server("web02");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("Apple - haha");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("01/01/2013", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addServer(myServer);

        myServer = new Server("web03");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("HP");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("01/01/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addServer(myServer);

        myServer = new Server("web04");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("IBM");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("01/01/2019", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addServer(myServer);

        myServer = new Server("web05");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("Dell");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("01/01/2019", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addServer(myServer);

        myServer = new Server("web06");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("HP");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("01/01/2019", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addServer(myServer);

        myServer = new Server("web07");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("Apple - haha");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("01/01/2019", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addServer(myServer);

        myServer = new Server("web08");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("Apple - haha");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addServer(myServer);

        // --------------
        List<Server> dells = dao.getServersByManufacturer("Dell");

        for (Server currentServer : dells) {
            System.out.println(currentServer.getName());
        }

        dells.stream().forEach(s -> System.out.println(s.getName()));

        // --------------
        Map<String, List<Server>> serverMap = dao.getAllServersGroupByManufacturer();
        
        Set<String> serverSet = serverMap.keySet();
        
        for(String key : serverSet) {
            for(Server server : serverMap.get(key)) {
                System.out.println(key + " : " + server.getName() + " : " + server.getPurchaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " : " + server.getServerAge());
            }
        }
        
        serverSet.stream()
                .forEach(name -> {
                    System.out.println("==========================");
                    System.out.println("Manuracturer: " + name);
                    serverMap.get(name).stream()
                            .forEach(s -> System.out.println(s.getName()));
                });
        
    }

}
