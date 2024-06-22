package DBConfig;
import java.sql.*;
public class DBHelper{
	protected DBConfig db = DBConfig.getConnection();
	protected PreparedStatement pst = DBConfig.getPst();
	protected Statement stmt = DBConfig.getStmt();
	protected ResultSet rs = DBConfig.getRs();
	protected Connection cn = DBConfig.getCon();
	protected CallableStatement clst=DBConfig.getCallst();
}
