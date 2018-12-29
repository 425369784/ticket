/**
 * All rights Reserved, Designed By Zhengyanbo
 * @Title:  DBUtil.java
 * @Package com.hpe.online.util
 * @Description:    TODO
 * @author: Zhengyanbo
 * @date:   2018骞�鏈�鏃�涓嬪崍2:09:03
 * @version V1.0
 */
package util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 * <p>瀹炵幇鏁版嵁搴撹闂殑宸ュ叿绫�/p>
 * @author	Zhengyanbo
 * @time	2018骞�鏈�鏃�涓嬪崍2:09:03
 * @version V1.0
 */
public class DBUtil {
	
	private static Connection getConn() throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");

		// 2. 鍒涘缓杩炴帴瀵硅薄
		String url = "jdbc:mysql://127.0.0.1:3306/ticket?serverTimezone=CTT";
		String user = "root";
		String pwd = "123456";
		System.out.println(url+user+pwd);
		Connection conn = DriverManager.getConnection(url, user, pwd);
		if(conn==null)
			System.out.println("连接数据库失败！");
		return conn;
	}
	
	private static void close(ResultSet rs, Statement stmt, Connection conn){
		
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * <p>Description: </p>
	 * <p>閽堝鏁版嵁琛ㄧ殑insert update delete鎿嶄綔</p>
	 * @param sql	瑕佹墽琛孲QL璇彞 
	 * @param params	闇�缁戝畾鍒癝QL璇彞涓殑鍙傛暟
	 * @return
	 */
	public static int update(String sql, Object[] params) {

		// 杩炴帴鏁版嵁搴� 鏌ヨ鏁版嵁琛�
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConn();

			// 3. 鍒涘缓鎵ц瀵硅薄
			
			stmt = conn.prepareStatement(sql);
			
			if (params != null){
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}

			// 4. 鎵цSQL
			int res = stmt.executeUpdate();

			return res;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, stmt, conn);
		}

		return 0;
	}

	public static List<Map<String, Object>> select(String sql, Object[] params) {
		// 杩炴帴鏁版嵁搴� 鏌ヨ鏁版嵁琛�
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Map<String, Object>> list = new ArrayList<>();

		// 1. 鍔犺浇椹卞姩
		try {
			
			conn = getConn();

			// 3. 鍒涘缓鎵ц瀵硅薄			
			stmt = conn.prepareStatement(sql);
			
			if (params != null){
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			// 4. 鎵цSQL
			rs = stmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int colCount = rsmd.getColumnCount();
			
			while (rs.next()) {
				
				Map<String, Object> map = new HashMap<>();
				
				// 閬嶅巻姣忎竴鏉¤褰曚腑, 姣忎竴涓垪鐨勫悕瀛楀強鍊�
				for (int i=0; i<colCount; i++){
					String colName = rsmd.getColumnName(i+1);
					Object value = rs.getObject(i+1);
					
					map.put(colName, value);
				}
				
				list.add(map);				
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt, conn);
		}

		return list;
	}
	
	public static <T> List<T> select(String sql, Object[] params, Class<T> type){
		
		List<Map<String, Object>> listMap = select(sql, params);
		
		List<T> list = new ArrayList<>();
	
		/*
		 * 浣跨敤Java涓殑鍙嶅皠鍒涘缓瀵硅薄
		 */
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
			
			/*
			 * newInstance()鏂规硶灏嗕細璋冪敤鎸囧畾绫荤殑鏃犲弬鏋勯�鏂规硶鏉ュ垱寤轰竴涓璞�
			 */
			
			for ( Map<String, Object> map: listMap){
				
				T obj = type.newInstance();
				
				for (PropertyDescriptor prop : properties) {
					String proName = prop.getName();
					Method setter = prop.getWriteMethod();
					
					Object value = map.get(proName);
					
					if (value != null){
						try {
							setter.invoke(obj, value);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				list.add(obj);
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return list;
		
	}
	
	public static <T> T getObject(String sql, Object[] params, Class<T> type){
		
		List<T> list = select(sql, params, type);
		
		if (list.size() > 0 ){
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	/**
	 * 
	 * <p>Description: </p>
	 * <p>鍒嗛〉鏌ヨ</p>
	 * @param sql
	 * @param params
	 * @param page
	 * @param type
	 * @return
	 */
	public static <T> Page select(String sql, Object[] params, Page page, Class<T> type){
		
		// 椤甸潰鏁伴噺, 鐢辨�璁板綍鏁�鍗曚釜椤甸潰璁板綍鏁�
		List<T> list = select(sql, params, type);
		
		int recodeCount = list.size();
		int pageCount = (recodeCount + page.getRowCount() - 1) / page.getRowCount() ;
		
		page.setPageCount(pageCount);
		
		// limit 鍦⊿QL鏌ヨ鐨勭粨鏋滀腑, 閫夋嫨鍏朵腑鐨勪竴閮ㄥ垎鏁版嵁   limit 璧峰琛屾暟, 瑕佹煡鍒扮殑璁板綍鏁�
		// select * from 琛�where 瀛楁=? limit ?, 5
		int start = (page.getPageNum() - 1 ) * page.getRowCount();
		String sqlLimit = sql + " limit " + start + ", " + page.getRowCount();
		
		list = select(sqlLimit, params, type);
		
		page.setList(list);
		
		return page;
	}
	
	public static Page select(String sql, Object[] params, Page page){
		// 椤甸潰鏁伴噺, 鐢辨�璁板綍鏁�鍗曚釜椤甸潰璁板綍鏁�
		List<Map<String, Object>> list = select(sql, params);

		int recodeCount = list.size();
		int pageCount = (recodeCount + page.getRowCount() - 1) / page.getRowCount();

		page.setPageCount(pageCount);

		// limit 鍦⊿QL鏌ヨ鐨勭粨鏋滀腑, 閫夋嫨鍏朵腑鐨勪竴閮ㄥ垎鏁版嵁 limit 璧峰琛屾暟, 瑕佹煡鍒扮殑璁板綍鏁�
		// select * from 琛�where 瀛楁=? limit ?, 5
		int start = (page.getPageNum() - 1) * page.getRowCount();
		String sqlLimit = sql + " limit " + start + ", " + page.getRowCount();

		list = select(sqlLimit, params);

		page.setList(list);

		return page;
	}
}








