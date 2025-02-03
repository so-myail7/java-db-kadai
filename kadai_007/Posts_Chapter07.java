package kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Posts_Chapter07 {
	
	
public static void main(String[] args) {
		
	Connection con = null;
    PreparedStatement statement = null;
	    
	    
	try {
		//接続
		con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "Java-Protecter3306&Sql"
	    );    
		
		System.out.println("データベースに接続成功:" + con);
		
		//投稿データの追加
		String sql = """
				     INSERT INTO posts (user_id,posted_at,post_content,likes)VALUE
				     (1003,'2023-02-08',"昨日の夜は徹夜でした・・",13),
				     (1002,'2023-02-08',"お疲れ様です！",12),
				     (1003,'2023-02-09',"今日も頑張ります！",18),
				     (1001,'2023-02-09',"無理は禁物ですよ！",17),
				     (1002,'2023-02-10',"明日から連休ですね！",20);
				""";
		statement = con.prepareStatement(sql);
		
		//追加SQLの実行
		System.out.println("レコード追加を実行します");
        int rowCnt = statement.executeUpdate();
        System.out.println( rowCnt + "件のレコードが追加されました");
        
        
        //投稿データの検索
        String sql2 = "SELECT * FROM posts WHERE user_id = 1002;";
        ResultSet result = statement.executeQuery(sql2);
        
        System.out.println("ユーザーIDが1002のレコードを検索しました");
        
        
        while(result.next()) {
        	Date postat = result.getDate("posted_at");
        	String content = result.getString("post_content");
        	int likes = result.getInt("likes");
        	
        	System.out.println(result.getRow() + "件目: 投稿日時=" + postat + 
        			           "/投稿内容=" + content + "/いいね数=" + likes);
        }
        
        
        
        
        
        
	} catch(SQLException e) {
        System.out.println("エラー発生：" + e.getMessage());
    } finally {
        // 使用したオブジェクトを解放
        if( statement != null ) {
            try { statement.close(); } catch(SQLException ignore) {}
        }
        if( con != null ) {
            try { con.close(); } catch(SQLException ignore) {}
        }
	}

  }

}