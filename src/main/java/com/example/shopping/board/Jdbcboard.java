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

    // 게시글 저장
    @Override
    public Board boardsave(Board board) {
        String sql = "INSERT INTO board (title, content, writer) VALUES (?, ?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getWriter()); // ✅ writer 저장

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                board.setId(generatedKeys.getLong(1));
            }
            System.out.println("게시글 저장 완료 (ID: " + board.getId() + ")");
            return board;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 전체 게시글 조회
    @Override
    public List<Board> findboard() {
        String sql = "SELECT id, title, content, writer FROM board";
        List<Board> boards = new ArrayList<>();

        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Board board = new Board();
                board.setId(rs.getLong("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                boards.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boards;
    }

    // ID로 게시글 조회
    @Override
    public Board findById(Long id) {
        String sql = "SELECT id, title, content, writer FROM board WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Board board = new Board();
                board.setId(rs.getLong("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                return board;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 게시글 수정
    @Override
    public boolean update(Board board) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setLong(3, board.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 작성자(writer)로 게시글 검색
    @Override
    public List<Board> findByWriter(String writer) {
        String sql = "SELECT id, title, content, writer FROM board WHERE writer = ?";
        List<Board> boards = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, writer);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Board board = new Board();
                board.setId(rs.getLong("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                boards.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boards;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from board where id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("게시글 삭제 중 오류 발생", e);
        }
    }
}