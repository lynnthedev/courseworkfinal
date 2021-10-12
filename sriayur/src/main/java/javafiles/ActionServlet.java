package javafiles;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private String jdbcURL = "jdbc:mysql://localhost:3306/organicdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	private static final String SELECT_PRODUCT_BY_NAME = "select * from products where productname =?";
	private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
	private static final String DELETE_PRODUCT_SQL = "delete from products where productname = ?;";
	private static final String UPDATE_PRODUCT_SQL = "update products set productname = ?,productprice= ? where productname = ?;";
		
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
	 	}
		return connection;
	}
	
    public ActionServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		System.out.println("Action is: " + action);
		
		try {
			switch(action) {
			case "/ActionServlet/add":
				showAddProduct(request, response);
				break;
			case "/ActionServlet/delete":
				deleteProduct(request, response);
				break;
			case "/ActionServlet/edit":
				showEditCart(request, response);
				break;
			case "/ActionServlet/update":
				updateCart(request, response);
				break;
			default:
				listCart(request,response);
				break;
			}
		 } catch (SQLException ex) {
			 throw new ServletException(ex);
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
	
	private void listCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < Product > products = new ArrayList <Product> ();	
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) 
		{
			System.out.println(preparedStatement);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String productname = rs.getString("productname");
				int productprice = rs.getInt("productprice");
				products.add(new Product(productname, productprice));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
			//List < User > listUser = databaseOperations.selectAllProducts();
			System.out.println("Total : " + products.size());
			
			request.setAttribute("listCart", products);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cartManagement.jsp");
			dispatcher.forward(request, response);
	}
	
	//method to redirect to add page
	private void showAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("addProduct.jsp");
		dispatcher.forward(request, response);
	}
	
	//method to get parameter, query database for existing 
	private void showEditCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		System.out.println("Directing to showEditCart...");
		
		String productname = request.getParameter("productname");
		
		Product existingProduct = new Product();
		
		try (Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);)
		{
			preparedStatement.setString(1, productname);
			
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				productname = rs.getString("productname");
				int productprice = rs.getInt("productprice");
				existingProduct = new Product(productname, productprice);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		//Serve up the product-form.jsp
		request.setAttribute("product", existingProduct);
		request.getRequestDispatcher("/editCart.jsp").forward(request, response);

	}
	//method to update the user data
	private void updateCart(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
		System.out.println("Directing to updateCart...");
		//get values from the request
		String oriProductName = request.getParameter("oriProductName");
		String productname = request.getParameter("productname");
		String productprice = request.getParameter("productprice");
		System.out.println(productname);
		System.out.println(productprice);
		//database operation
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
			statement.setString(1, productname);
			statement.setString(2, productprice);
			statement.setString(3, oriProductName);
			statement.executeUpdate();
		}
		response.sendRedirect("http://localhost:9001/sriayur/ActionServlet");
	}
	
	//method to delete user
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("Deleting Product...");
		String productname = request.getParameter("productname");
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) 
		{
			statement.setString(1, productname);
			statement.executeUpdate();
		}
		response.sendRedirect("http://localhost:9001/sriayur/ActionServlet");
	}
	
}
