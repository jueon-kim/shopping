package com.example.shopping.board;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class Jdbcboard implements BoardRepository {
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

    @Override
    public Board boardsave(Board board) {
        String sql = "insert into board(title, content) values(?, ?)";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // 자동 생성 키 반환 설정
        ) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                board.setId(generatedKeys.getLong(1)); // 생성된 ID를 Board 객체에 설정
            }
            System.out.println("게시글 작성 완료 (ID: " + board.getId() + ")");
            return board;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // 오류 발생 시 null 반환
        }
    }

    @Override
    public List<Board> findboard() {
        String sql = "select id, title, content from board"; // ID도 함께 조회
        ArrayList<Board> boards = new ArrayList<>();
        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                Board board = new Board();
                board.setId(rs.getLong("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                boards.add(board);
                System.out.println("게시글 조회 완료 (ID: " + board.getId() + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boards;
    }

    @Override
    public Board boardupdate(Board board) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setLong(3, board.getId()); // Board 클래스의 id 필드가 Long 타입이라고 가정
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("게시글 수정 완료 (ID: " + board.getId() + ")");
                return board;
            } else {
                System.out.println("게시글 수정 실패 (ID: " + board.getId() + "): 해당 ID의 게시글이 없거나 업데이트 실패");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("게시글 업데이트 중 데이터베이스 오류 발생");
            return null;
        }
    }

    @Override
    public Board findById(Long id) {
        String sql = "SELECT id, title, content FROM board WHERE id = ?";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Board board = new Board();
                board.setId(rs.getLong("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                return board;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}