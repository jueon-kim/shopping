package com.example.shopping.member;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class JdbcmemberTest {

    @Test
    void jdbctest() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 드라이버 로딩 (JDBC 4.0 이상이면 생략 가능)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB 연결
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "kimjueon");

            // 쿼리 실행
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM member");

            // 결과 처리 (예시)
            while (rs.next()) {
                System.out.println("이름: " + rs.getString("name") + " Phone:" + rs.getString("phone") + " id" + rs.getString("id") + " pw:" + rs.getString("pw"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("연결 실패: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
