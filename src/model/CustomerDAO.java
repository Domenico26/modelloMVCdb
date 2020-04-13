package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A small table of banking customers for testing.
 */

public class CustomerDAO {


    /**
     * Finds the customer with the given ID.
     * Returns null if there is no match.
     */

    public List<Integer> doRetrievePrefs() {
        List<Integer> pref = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("SELECT id FROM customer WHERE bookmarked=true");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               pref.add(rs.getInt(1));
            }
            return pref;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void azzera()
    {
        int i;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE customer SET bookmarked='0'");
            if ((i=ps.executeUpdate()) == 0) {
                throw new RuntimeException("Update error"+i);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdatePrefsById(int cust){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE customer SET bookmarked=true WHERE id=?"
            );
            ps.setInt(1,cust);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }


    public void doUpdate(Customer customer){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE customer SET firstname=?, lastname=?, balance=? WHERE id=?"
            );                                                            // in questo caso restituiamo  void per cui è inutile
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setDouble(3, customer.getBalance());
            ps.setInt(4, customer.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }



    public List<Customer> doRetrieveAll(){
        ArrayList<Customer> temp = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {

                PreparedStatement ps =
                        con.prepareStatement("SELECT id, firstName, lastName, balance FROM customer");
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    Customer p = new Customer();
                    p.setId(rs.getInt(1));
                    p.setFirstName(rs.getString(2));
                    p.setLastName(rs.getString(3));
                    p.setBalance(rs.getDouble(4));

                    temp.add(p);
                }
                return temp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Customer doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, firstName, lastName, balance FROM customer WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer p = new Customer();
                p.setId(rs.getInt(1));
                p.setFirstName(rs.getString(2));
                p.setLastName(rs.getString(3));
                p.setBalance(rs.getDouble(4));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(Customer customer) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO customer (firstName, lastName, balance) VALUES(?,?,?)"
        //            , Statement.RETURN_GENERATED_KEYS                   // questo codice è utile solo se si vuole restituire il bean customer completo di id
            );                                                            // in questo caso restituiamo  void per cui è inutile
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setDouble(3, customer.getBalance());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            //ResultSet rs = ps.getGeneratedKeys();     // questo codice è utile solo se si vuole restituire il bean customer completo di id
           // rs.next();                                // in questo caso restituiamo  void per cui è inutile
           // int id = rs.getInt(1);
           // customer.setId(id);                      // qui viene completato il bean con l'id generato dalll'INSERT

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}


