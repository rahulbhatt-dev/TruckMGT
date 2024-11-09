package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckService {
    public void addTruck(Truck truck){
        String sql = "insert into truck (name,model,capacity,driver_name) value(?,?,?,?)";
        try {
            Connection connection = ConnectionDetails.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, truck.getName());
            preparedStatement.setString(2, truck.getModel());
            preparedStatement.setInt(3,truck.getCapacity());
            preparedStatement.setString(4, truck.getDrivername());
            int update = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + update );

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public Truck getTruck(int id){
        String sql = "select * from truck where id = ?";
        Truck truck = new Truck();
        try {
            Connection connection = ConnectionDetails.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                truck.setId(resultSet.getInt("id"));
                truck.setName(resultSet.getString("name"));
                truck.setModel(resultSet.getString("model"));
                truck.setCapacity(resultSet.getInt("capacity"));
                truck.setDrivername(resultSet.getString("driver_name"));
            }
        }
        catch(Exception e){
            throw new RuntimeException();
        }
        return truck;
    }
    public void updateTruck(Truck truck){
        String sql = "update truck set name= ?, model = ?, capacity= ?, driver_name = ? where id = ?";

        try {
            Connection connection = ConnectionDetails.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, truck.getName());
            preparedStatement.setString(2, truck.getModel());
            preparedStatement.setInt(3,truck.getCapacity());
            preparedStatement.setString(4, truck.getDrivername());
            preparedStatement.setInt(5, truck.getId());

            int update = preparedStatement.executeUpdate();
            System.out.println("Rows update: " + update);
        }
        catch(SQLException e){
            throw new RuntimeException();
        }
    }

    public List<Truck>  getAllTruck(){
        String sql = "select * from truck";
        List<Truck> trucks = new ArrayList<Truck>();
        try{
            Connection connection = ConnectionDetails.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Truck truck = new Truck();
                truck.setId(resultSet.getInt("id"));
                truck.setName(resultSet.getString("name"));
                truck.setModel(resultSet.getString("model"));
                truck.setCapacity(resultSet.getInt("capacity"));
                truck.setDrivername(resultSet.getString("driver_name"));
                trucks.add(truck);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return trucks;
    }
    public void deleteTruck(int id){
        String sql = "delete from truck where id = ?";
        Truck truck = null;
        try {
            Connection connection = ConnectionDetails.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int update = preparedStatement.executeUpdate();
            System.out.println("Rows Deleted: " + update);
        }
        catch(Exception e){
            throw new RuntimeException();
        }

    }
}
