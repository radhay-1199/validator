package com.gl.validator;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ValidatorApplication {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/ceirconfig?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "";
	   
	public static void main(String[] args){
			SpringApplication.run(ValidatorApplication.class, args);
			StockMgmt stockMgmt = new StockMgmt();
			stockMgmt.setSuplierName("abc");
			stockMgmt.setSupplierId("123");
			stockMgmt.setDeviceQuantity(01234);
			stockMgmt.setQuantity(0123);
			stockMgmt.setInvoiceNumber("012345678");
			stockMgmt.setUserType("Importer");
			stockMgmt.setTxnId("1231231231");
			stockMgmt.setRemarks("abc");
			stockMgmt.setUser(new User());
			System.out.println("Before calling function: "+stockMgmt);
			System.out.println("email: "+stockMgmt.getUser().getUserProfile().getEmail());
			if("End User".equalsIgnoreCase(stockMgmt.getUserType())){
				validateFieldsByObject(stockMgmt,stockMgmt.getUserType(),stockMgmt.getUser().getUserProfile().getEmail(),1);
			}else {
				validateFieldsByObject(stockMgmt,stockMgmt.getUserType(),1);
			}
	}
	public static ValidationOutput validateFieldsByObject(Object object,String userType,String email,Integer featureId){
		try {
			String value = null;
			ValidationOutput validationOutput = new ValidationOutput(Boolean.TRUE,new HashMap<String, String>());
			Class<? extends Object> c = object.getClass();
			  Field[] fields = c.getDeclaredFields();
			  for (Field field : fields) {
			      String name = field.getName();
			      System.out.println("Field Name: "+name);
			      field.setAccessible(true);          
			      try {
			    	  FieldValidation fieldValidation = findByFieldName(name,userType,featureId);
			    	  System.out.println("Field Validation: "+fieldValidation+" nonNull:"+Objects.nonNull(fieldValidation.getFieldName()));
			    	  if(Objects.nonNull(fieldValidation.getFieldName())) {
			    		  Pattern regex = Pattern.compile(fieldValidation.getRegex());
			    		  System.out.println("Regex: "+regex);
					      if("userType".equalsIgnoreCase(field.getName())) {
					    	  value = email;
					      }else {
					    	  value = field.get(object).toString();
					      }
			    		  System.out.println("Value: "+value);
			    		  if(("Y".equalsIgnoreCase(fieldValidation.getMandatory()) && value.isEmpty())) {
			    			  	validationOutput.setIsValid(Boolean.FALSE);
			    			  	validationOutput.getInvalidFields().put(name, "Validation Failed with regex ["+regex+"]");
			    			  	System.out.println("Validation Result for "+name+": "+validationOutput.getInvalidFields().get(name));
			    			  	break;
			    		  }else if("N".equalsIgnoreCase(fieldValidation.getMandatory()) && value.isEmpty()) {
			    			  System.out.println("Everything is fine and isValid: "+validationOutput.getIsValid());
			    			  continue;
			    		  }else {
			    			  if(!regex.matcher(value).matches()) {
			    				  validationOutput.setIsValid(Boolean.FALSE);
			    				  validationOutput.getInvalidFields().put(name, "Validation Failed with regex ["+regex+"]");
			    				  System.out.println("Validation Result for "+name+": "+validationOutput.getInvalidFields().get(name));
			    				  break;
			    			  }else 
			    				  System.out.println("Everything is fine and isValid: "+validationOutput.getIsValid());
			    	     }
			    	  }else {
			    		  System.out.println("Everything is fine and isValid: "+validationOutput.getIsValid());
			    		  continue;
			    		  //validationOutput.setIsValid(Boolean.FALSE);
			    		 // validationOutput.getInvalidFields().put(name, "Could not found in table [ field_validation ]");
			    	  }
			      } catch (IllegalArgumentException e) {
			    	  System.out.println("IllegalArgumentException while validating ["+object+"] Exception = ["+e+"]");
			        e.printStackTrace();
			      } catch (IllegalAccessException e) {
			    	  System.out.println("IllegalAccessException while validating ["+object+"] Exception = ["+e+"]");
					e.printStackTrace();
				}
			  }
			  System.out.println("Validation done for object ["+object+"] isValid = "+validationOutput.getIsValid());
			  return validationOutput;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in validateFieldsByObject"+e);
			e.printStackTrace();
		}
		return null; 
		
	}
	public static ValidationOutput validateFieldsByObject(Object object,String userType,Integer featureId){
		try {
			ValidationOutput validationOutput = new ValidationOutput(Boolean.TRUE,new HashMap<String, String>());
			Class<? extends Object> c = object.getClass();
			  Field[] fields = c.getDeclaredFields();
			  for (Field field : fields) {
			      String name = field.getName();
			      System.out.println("Field Name: "+name);
			      field.setAccessible(true);          
			      try {
			    	  FieldValidation fieldValidation = findByFieldName(name,userType,featureId);
			    	  System.out.println("Field Validation: "+fieldValidation+" nonNull:"+Objects.nonNull(fieldValidation.getFieldName()));
			    	  if(Objects.nonNull(fieldValidation.getFieldName())) {
			    		  Pattern regex = Pattern.compile(fieldValidation.getRegex());
			    		  System.out.println("Regex: "+regex);
					      String value = field.get(object).toString();
			    		  System.out.println("Value: "+value);
			    		  if(("Y".equalsIgnoreCase(fieldValidation.getMandatory()) && value.isEmpty())) {
			    			  validationOutput.setIsValid(Boolean.FALSE);
			    			  validationOutput.getInvalidFields().put(name, "Validation Failed with regex ["+regex+"]");
				    		  System.out.println("Validation Result for "+name+": "+validationOutput.getInvalidFields().get(name));
			    		  }else if("N".equalsIgnoreCase(fieldValidation.getMandatory()) && value.isEmpty()) {
			    			  System.out.println("Everything is fine and isValid: "+validationOutput.getIsValid());
			    			  continue;
			    		  }else {
			    			  if(!regex.matcher(value).matches()) {
			    				  validationOutput.setIsValid(Boolean.FALSE);
			    				  validationOutput.getInvalidFields().put(name, "Validation Failed with regex ["+regex+"]");
			    				  System.out.println("Validation Result for "+name+": "+validationOutput.getInvalidFields().get(name));
			    			  }else 
			    				  System.out.println("Everything is fine and isValid: "+validationOutput.getIsValid());
			    		  }
			    	  }else {
			    		  System.out.println("Everything is fine and isValid: "+validationOutput.getIsValid());
			    		  continue;
			    		  //validationOutput.setIsValid(Boolean.FALSE);
			    		 // validationOutput.getInvalidFields().put(name, "Could not found in table [ field_validation ]");
			    	  }
			      } catch (IllegalArgumentException e) {
			    	  System.out.println("IllegalArgumentException while validating ["+object+"] Exception = ["+e+"]");
			        e.printStackTrace();
			      } catch (IllegalAccessException e) {
			    	  System.out.println("IllegalAccessException while validating ["+object+"] Exception = ["+e+"]");
					e.printStackTrace();
				}
			  }
			  System.out.println("Validation done for object ["+object+"] isValid = "+validationOutput.getIsValid());
			  return validationOutput;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in validateFieldsByObject"+e);
			e.printStackTrace();
		}
		return null; 
	}
	
		public static FieldValidation findByFieldName(String fieldName, String userType,Integer featureId) {
			   
			FieldValidation fieldValidation = new FieldValidation();
			   Connection conn = null;
			   Statement stmt = null;
			   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			      System.out.println("Connected database successfully...");
			      
			      //STEP 4: Execute a query
			      System.out.println("Creating statement...");
			      stmt = conn.createStatement();

			      String sql = "SELECT * FROM field_validation where field_name='"+fieldName+"' and usertype='"+userType+"' and feature_id="+featureId;
			      System.out.println("query: "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      //STEP 5: Extract data from result set
			    	  while(rs.next()){
			         //Retrieve by column name
			    		  fieldValidation.setFieldName(rs.getString("field_name"));
			    		  fieldValidation.setFieldDescription(rs.getString("field_description"));
			    		  fieldValidation.setMaxLength(rs.getInt("max_length"));
			    		  fieldValidation.setMinLength(rs.getInt("min_length"));
			    		  fieldValidation.setType(rs.getString("type"));
			    		  fieldValidation.setAllowedSpace(rs.getString("allowed_space"));
			    		  fieldValidation.setAllowedCharacterset(rs.getString("allowed_characterset"));
			    		  fieldValidation.setRegex(rs.getString("regex"));
			    		  fieldValidation.setUsertype(rs.getString("usertype"));
			    		  fieldValidation.setMandatory(rs.getString("mandatory"));
			    		  fieldValidation.setUserId(rs.getInt("user_id"));
			    		  fieldValidation.setFeatureId(rs.getInt("feature_id"));
			    		  fieldValidation.setFeatureName(rs.getString("feature_name"));
			    		  System.out.println("Fetched from field_validation: "+fieldValidation);
			    	  }
			      rs.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   return fieldValidation;
		}
}
