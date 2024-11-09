package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        TruckService truckService = new TruckService();

        Truck tata = new Truck("TATA","2019",1000,"ramesh");
        Truck volvo = new Truck("VOLVO","2020",1000,"suresh");
        Truck eicher = new Truck("EICHER","2021",1000,"raman");

        //Additng data to DB
        truckService.addTruck(tata);
        truckService.addTruck(volvo);
        truckService.addTruck(eicher);

        //fetch
        Truck truck = truckService.getTruck(1);
        System.out.println("Truck data: "+ truck);

        //update
        truck.setDrivername("Ramesh");
        truckService.updateTruck(truck);
        System.out.println("Updated truck Data: "+ truckService.getTruck(1));

        //all truck
        List<Truck> allTrucks = truckService.getAllTruck();
        System.out.println("All trucks in DB: ");
        for(Truck truckx : allTrucks){
            System.out.println(truckx);
        }

        //Delete
        truckService.deleteTruck(3);
    }
}
