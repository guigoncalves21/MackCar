package dao;
import banco.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javabeans.Veiculo;


public class VeiculoDAOconcreto implements VeiculoDAO  {
    
    
    private Connection conexao = null;
    
    
    public VeiculoDAOconcreto(){
        conexao = ConnectionFactory.getConnection();
    }
    
        
    @Override
    public List <Veiculo> buscaVeiculo() throws SQLException{
        
        String sql = "select * from veiculo";
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        List <Veiculo> veiculos = new ArrayList<>();
        
        try{
        
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            
            while (rs.next()){
                Veiculo veiculo = new Veiculo();
                veiculo.setCod_veiculo(rs.getInt("cod_veiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setNome_modelo(rs.getString("nome_modelo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getString("ano"));
                veiculo.setQtd_disponivel(rs.getInt("qtd_disponivel"));
                veiculo.setValor_diaria(rs.getInt("valor_diaria"));
                veiculos.add(veiculo);
            }
       
        }
    
        catch (SQLException ex) {
            System.err.println("Erro "+ex);
        }finally{
            ConnectionFactory.closeConection(conexao);
        }        
            
            
        return veiculos;
            
    }
    
    
    @Override
    public Veiculo buscaVeiculoId(int id) throws SQLException{
        
        Veiculo veiculo = null;
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        try{
            
            pst = conexao.prepareStatement("select * from veiculo where cod_veiculo = ?");
        
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                        
                veiculo = new Veiculo();
                
                veiculo.setCod_veiculo(rs.getInt("cod_veiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setNome_modelo(rs.getString("nome_modelo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getString("ano"));
                veiculo.setQtd_disponivel(rs.getInt("qtd_disponivel"));
                veiculo.setValor_diaria(rs.getInt("valor_diaria"));
            }
            
            System.out.println("VEICULO ENCONTRADO");
	    } catch (SQLException e) {
                System.out.println("Erro de SQL:" + e.getMessage());
	    }
            
        return veiculo;
            
    }
    
}
        
     