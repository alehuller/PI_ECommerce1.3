package pi.quarto.semestre.codigo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pi.quarto.semestre.codigo.model.CarrinhoAllDto;
import pi.quarto.semestre.codigo.model.EnderecoEntrega;
import pi.quarto.semestre.codigo.model.ProdutoAllDto;

public class CarrinhoDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/pi_quarto_semestre";
    private static final String USER = "root";
    private static final String PASSWORD = "";

	public void inserir(Long codigo, String nome, String valor) throws SQLException{
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement("INSERT INTO carrinho (produtoId, quantidade, nome, valor) VALUES (?, ?, ?, ?)")) {
            ps.setLong(1, codigo);
            ps.setLong(2, 1);
            ps.setString(3, nome);
            ps.setString(4, valor);
            ps.execute();
    }
}

    public boolean varredura(Long codigo) throws SQLException {
    String query = "SELECT COUNT(*) FROM carrinho WHERE produtoId = ?";
    
    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setLong(1, codigo);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Se count for maior que 0, o produto já está no carrinho
            }
        }
    }
    
    return false; // Se ocorrer algum erro, assume-se que o produto não está no carrinho
}

public Long pegarQuantidade(Long codigo) throws SQLException {
    String query = "SELECT quantidade FROM carrinho WHERE produtoId = ?";
    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = con.prepareStatement(query)) {
        ps.setLong(1, codigo);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getLong("quantidade");
            }
        }
    }

    return 0L; // Se não encontrou o produto, retorna 0 ou outro valor padrão, dependendo da sua lógica
}


    public void modificarQuantidade(Long codigo, Long quantidade) throws SQLException{
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement("UPDATE carrinho set quantidade = ? where produtoId = ?")) {
            ps.setLong(1, quantidade + 1);
            ps.setLong(2, codigo);
            ps.execute();
        }
    }

    public List<CarrinhoAllDto> findCarrinhos() throws SQLException {
        List<CarrinhoAllDto> carrinhos = new ArrayList<>();
    
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement("SELECT * FROM carrinho")) {
    
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CarrinhoAllDto carrinho = new CarrinhoAllDto();

                    carrinho.setId(rs.getLong("id"));
                    carrinho.setProdutoId(rs.getString("produtoId"));
                    carrinho.setQuantidade(rs.getLong("quantidade"));
                    carrinho.setNome(rs.getString("nome"));
                    carrinho.setValor(rs.getString("valor"));
    
                    carrinhos.add(carrinho);
                }
            }
        }
    
        return carrinhos;
    }

    
}
