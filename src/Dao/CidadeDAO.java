/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Cidade;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 631910142
 */
public class CidadeDAO {
    public static void inserir( Cidade cid){
        String query = "INSERT INTO cidades(nome) VALUES ("
                + "'" + cid.getNome() + "');";
        conexao.executar(query);
    }
    public static void editar( Cidade cid){
        String query = "UPDATE cidades SET nome = " 
                + "'" + cid.getNome() + "' WHERE id = " + cid.getId() + ";";
        conexao.executar(query);
    }
    public static void excluir( Cidade cid){
        String query = "DELETE cidades WHERE id = " + cid.getId() + ";";
        conexao.executar(query);
    }
    public static Cidade getCidadeById(int idCidade){
        String query = "SELECT * FROM cidades WHERE ID = "+ idCidade + ";";
        ResultSet rs =  conexao.consultar(query);
        
        if ( rs != null){
            try{
                rs.next();
                    Cidade cid = new Cidade();
                    cid.setId(rs.getInt(1));
                    cid.setNome(rs.getString(2));
                    return cid;
            }catch(Exception e){
                return null;
            }
    
        }else{
            return null;
        }
    }
    
    public static List<Cidade> getCidades(){
        List<Cidade> lista = new ArrayList<>();
        String query = "SELECT * FROM cidades ORDER BY nome;";
        ResultSet rs =  conexao.consultar(query);
        if ( rs != null){
            try{
                while ( rs.next()){
                    Cidade cid = new Cidade();
                    cid.setId(rs.getInt(1));
                    cid.setNome(rs.getString(2));
                    lista.add(cid);
                }
            }catch(Exception e){
                return lista;
            }
        }
        return lista;
    }
}
