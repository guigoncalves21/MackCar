package dao;
import banco.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javabeans.Multa;

public class MultaDAOconcreto implements MultaDAO{
    
    
    @Override
    public List <Multa> buscaMultaId(int id) throws SQLException{
        
        Multa multa = null;
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        List <Multa> multas = new ArrayList<>();
        
        try{
            
            pst = conexao.prepareStatement("select * from multa where cod_cliente = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
                       
            while (rs.next()) {
                multa = new Multa();
                multa.setCod_multa(rs.getInt("cod_multa"));
                multa.setDescricao(rs.getString("descricao"));
                multa.setCod_cliente(rs.getInt("cod_cliente"));
                multa.setValor(rs.getDouble("valor"));
                multas.add(multa);
            }
        }
        
            catch (SQLException ex) {
                System.err.println("Erro "+ex);
            }finally{
                ConnectionFactory.closeConection(conexao);
            }        
            
        return multas;
    }
    
    @Override
    public List <Multa> buscaMultas() throws SQLException{
        
        String sql = "select * from multa";
        
        Multa multa = null;
        PreparedStatement pst = null;
        Connection conexao = ConnectionFactory.getConnection();
        ResultSet rs = null;
        
        List <Multa> multas = new ArrayList<>();
        
        try{
            
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
                       
            while (rs.next()) {
                multa = new Multa();
                multa.setCod_multa(rs.getInt("cod_multa"));
                multa.setDescricao(rs.getString("descricao"));
                multa.setCod_cliente(rs.getInt("cod_cliente"));
                multa.setValor(rs.getInt("valor"));
                multas.add(multa);
            }
        }
        
            catch (SQLException ex) {
                System.err.println("Erro "+ex);
            }finally{
                ConnectionFactory.closeConection(conexao);
            }        
            
        return multas;
    }
   
}
   
    

