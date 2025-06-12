package com.example.shopping.member;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Properties;

@Repository
public class Jdbcmember implements MemberRepository {

    //final 변경을 금지 오버라이딩을 금지 상속 금지 한다
    private final String userName = "root";
    private final String password = "kimjueon";
    private final String dbms = "mysql";
    private final String dbName = "demo";
    private final String serverName = "localhost";
    private final int portNumber = 3306;

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        if (this.dbms.equals("mysql")) {
            conn = DriverManager.getConnection(
                    "jdbc:" + this.dbms + "://" +
                            this.serverName + ":" +
                            this.portNumber + "/" +
                            this.dbName,
                    connectionProps);
        }

        return conn;
    }

    public List<Member> findbyAll() {
        String sql = "select * from member";
        ArrayList<Member> members = new ArrayList<>();

        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {

                Member member = new Member();
                member.setName(rs.getString("name"));
                member.setPhone(rs.getString("phone"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));

                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public Member save(Member member) {

        String sql = "insert into member (name, phone, id, pw) values(?,?,?,?)";


        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getId());
            pstmt.setString(4, member.getPw());
            pstmt.executeUpdate();

            System.out.println("회원 등록 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.empty();
    }

    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM member WHERE id = ?";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Member> login(String id, String pw) {
        String sql = "SELECT * FROM member WHERE id = ? AND pw = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setPhone(rs.getString("phone"));

                return Optional.of(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
    @Override
    public boolean update(Member member) {

        String sql = "UPDATE member SET name = ?, phone = ?, pw = ? WHERE id = ?"; // 테이블 이름과 WHERE 절 추가

        try (

            Connection  conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ){
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getId());
            pstmt.setString(4, member.getPw());

            pstmt.executeUpdate();
            System.out.println("수정 완료");

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("update 실패");
        }
        return update(member);

    }

}