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
    String conString = "jdbc:mysql://bfdc6au0tcay6gxeuohp-mysql.services.clever-cloud.com:3306/bfdc6au0tcay6gxeuohp";
    String username = "uqq6c1ewt1hkbzwd";
    String password = "jaW3mRUAAwzTAiOTVkZu";
    public FeatureDao(){
        if(con == null){
            try {
                con =  DriverManager.getConnection(conString, username, password);
                System.out.println(con==null);
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
        String sql = "SELECT * FROM Features WHERE featureName LIKE ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Feature feature = new Feature();
                feature.setId(rs.getInt("featureId"));
                feature.setName(rs.getString("featureName"));
                feature.setDescription(rs.getString("featureDescription"));
                result.add(feature);
            }
            System.out.println(result);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * update the @feature
     * @param feature
     */
    public void saveFeature(Feature feature){
        String sql = "INSERT Features SET featureName=?, featureDescription=? ";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, feature.getName());
            ps.setString(2, feature.getDescription());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * update the @feature
     * @param feature
     */
    public void editFeature(Feature feature){
        String sql = "UPDATE Features SET featureName=?, featureDescription=? WHERE featureId=?";
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
        String sql = "DELETE FROM Features WHERE featureId=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}