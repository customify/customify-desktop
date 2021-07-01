package com.customify.server.services.billing;
import com.customify.server.Db.Db;
import com.customify.server.models.billing.Feature;

import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.util.ArrayList;

public class FeatureDao {
    private static Connection con;

    public FeatureDao(){
        if(con == null){
            try {
                con = Db.getConnection();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * search all features in the Featuress whose name contains the @key
     * @param key
     * @return list of feature whose name contains the @key
     */
    public ArrayList<Feature> searchFeature(String key){
        ArrayList<Feature> result = new ArrayList<Feature>();
        String sql = "SELECT * FROM Featuress WHERE name LIKE ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Feature feature = new Feature();
                feature.setId(rs.getInt("id"));
                feature.setName(rs.getString("name"));
                feature.setDescription(rs.getString("description"));
                result.add(feature);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * update the @feature
     * @param feature
     */
    public void editFeature(Feature feature){
        String sql = "UPDATE Featuress SET name=?, description=? WHERE id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, feature.getName());
            ps.setString(2, feature.getDescription());
            ps.setInt(3, feature.getId());

            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * delete the feature whose id is @id
     * @param id
     */
    public void deleteFeature(int id){
        String sql = "DELETE FROM Features WHERE id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}